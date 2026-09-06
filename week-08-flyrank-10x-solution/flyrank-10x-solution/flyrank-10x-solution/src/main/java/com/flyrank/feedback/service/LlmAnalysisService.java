package com.flyrank.feedback.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyrank.feedback.config.CacheConfig;
import com.flyrank.feedback.config.LlmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Uses an external LLM (OpenAI-compatible chat completions endpoint) to analyze
 * customer feedback and produce a sentiment label, a short summary and keywords.
 *
 * If no LLM API key is configured (or the call fails for any reason), the service
 * automatically falls back to a lightweight rule-based analyzer so that the rest of
 * the application keeps working end-to-end during local development/demo.
 */
@Service
public class LlmAnalysisService {

    private static final Logger log = LoggerFactory.getLogger(LlmAnalysisService.class);

    private final RestTemplate restTemplate;
    private final LlmProperties llmProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LlmAnalysisService(RestTemplate restTemplate, LlmProperties llmProperties) {
        this.restTemplate = restTemplate;
        this.llmProperties = llmProperties;
    }

    public static class AnalysisResult {
        private final String sentiment;
        private final String summary;
        private final String keywords;

        public AnalysisResult(String sentiment, String summary, String keywords) {
            this.sentiment = sentiment;
            this.summary = summary;
            this.keywords = keywords;
        }

        public String getSentiment() {
            return sentiment;
        }

        public String getSummary() {
            return summary;
        }

        public String getKeywords() {
            return keywords;
        }
    }

    /**
     * Cached by feedback id: repeated analysis requests for the same feedback item
     * will not re-invoke the LLM API.
     */
    @Cacheable(cacheNames = CacheConfig.FEEDBACK_ANALYSIS_CACHE, key = "#root.args[0]")
    public AnalysisResult analyze(Long feedbackId, String message, Integer rating) {
        if (llmProperties.isEnabled() && llmProperties.getApiKey() != null && !llmProperties.getApiKey().isBlank()) {
            try {
                return callLlm(message, rating);
            } catch (Exception ex) {
                log.warn("LLM call failed, falling back to rule-based analysis: {}", ex.getMessage());
            }
        }
        return ruleBasedAnalysis(message, rating);
    }

    private AnalysisResult callLlm(String message, Integer rating) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(llmProperties.getApiKey());

        String prompt = "You are a customer feedback analyst. Given the feedback below, respond ONLY with a "
                + "compact JSON object of the form {\"sentiment\":\"POSITIVE|NEUTRAL|NEGATIVE\","
                + "\"summary\":\"one sentence summary\",\"keywords\":\"comma,separated,keywords\"}. "
                + "Feedback rating: " + rating + " out of 5. Feedback message: " + message;

        Map<String, Object> body = Map.of(
                "model", llmProperties.getModel(),
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "temperature", 0.2
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        Map<?, ?> response = restTemplate.postForObject(llmProperties.getApiUrl(), entity, Map.class);

        String content = extractContent(response);
        JsonNode json = parseJsonLeniently(content);

        String sentiment = json.path("sentiment").asText("NEUTRAL").toUpperCase();
        String summary = json.path("summary").asText("No summary available.");
        String keywords = json.path("keywords").asText("");

        return new AnalysisResult(normalizeSentiment(sentiment), summary, keywords);
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<?, ?> response) {
        if (response == null) {
            throw new IllegalStateException("Empty response from LLM API");
        }
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices == null || choices.isEmpty()) {
            throw new IllegalStateException("No choices returned by LLM API");
        }
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return String.valueOf(message.get("content"));
    }

    private JsonNode parseJsonLeniently(String content) {
        try {
            int start = content.indexOf('{');
            int end = content.lastIndexOf('}');
            String jsonPart = (start >= 0 && end >= start) ? content.substring(start, end + 1) : content;
            return objectMapper.readTree(jsonPart);
        } catch (Exception e) {
            return objectMapper.createObjectNode();
        }
    }

    private String normalizeSentiment(String sentiment) {
        if (sentiment.contains("POS")) return "POSITIVE";
        if (sentiment.contains("NEG")) return "NEGATIVE";
        return "NEUTRAL";
    }

    /**
     * Simple, deterministic fallback analyzer used when no LLM key is configured.
     * Combines the numeric rating with basic keyword spotting so the demo works offline.
     */
    private AnalysisResult ruleBasedAnalysis(String message, Integer rating) {
        String lower = message == null ? "" : message.toLowerCase();

        List<String> negativeWords = List.of("crash", "bug", "bad", "frustrat", "slow", "expensive", "issue", "problem", "broken");
        List<String> positiveWords = List.of("great", "love", "excellent", "fast", "clean", "good", "resolved", "amazing", "helpful");

        long negativeHits = negativeWords.stream().filter(lower::contains).count();
        long positiveHits = positiveWords.stream().filter(lower::contains).count();

        String sentiment;
        if (rating != null && rating <= 2 && negativeHits >= positiveHits) {
            sentiment = "NEGATIVE";
        } else if (rating != null && rating >= 4 && positiveHits >= negativeHits) {
            sentiment = "POSITIVE";
        } else if (negativeHits > positiveHits) {
            sentiment = "NEGATIVE";
        } else if (positiveHits > negativeHits) {
            sentiment = "POSITIVE";
        } else {
            sentiment = "NEUTRAL";
        }

        String summary = summarize(message);
        String keywords = extractKeywords(lower, negativeWords, positiveWords);

        return new AnalysisResult(sentiment, summary, keywords);
    }

    private String summarize(String message) {
        if (message == null || message.isBlank()) {
            return "No feedback message provided.";
        }
        String trimmed = message.trim();
        String firstSentence = trimmed.split("(?<=[.!?])\\s+")[0];
        if (firstSentence.length() > 160) {
            firstSentence = firstSentence.substring(0, 157) + "...";
        }
        return firstSentence;
    }

    private String extractKeywords(String lower, List<String> negativeWords, List<String> positiveWords) {
        List<String> found = new java.util.ArrayList<>();
        for (String w : negativeWords) {
            if (lower.contains(w)) found.add(w);
        }
        for (String w : positiveWords) {
            if (lower.contains(w)) found.add(w);
        }
        return found.stream().distinct().collect(Collectors.joining(", "));
    }
}
