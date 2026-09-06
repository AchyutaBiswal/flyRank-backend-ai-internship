package com.flyrank.feedback.service;

import com.flyrank.feedback.config.LlmProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LlmAnalysisServiceTest {

    private LlmAnalysisService llmAnalysisService;

    @BeforeEach
    void setUp() {
        LlmProperties properties = new LlmProperties();
        properties.setEnabled(false); // force rule-based fallback for deterministic unit tests
        properties.setApiKey("");
        properties.setApiUrl("https://api.openai.com/v1/chat/completions");
        properties.setModel("gpt-4o-mini");

        llmAnalysisService = new LlmAnalysisService(new RestTemplate(), properties);
    }

    @Test
    void analyzeShouldReturnNegativeSentimentForLowRatingAndNegativeKeywords() {
        LlmAnalysisService.AnalysisResult result = llmAnalysisService.analyze(
                1L, "The app keeps crashing, this is a terrible bug.", 1);

        assertEquals("NEGATIVE", result.getSentiment());
        assertNotNull(result.getSummary());
    }

    @Test
    void analyzeShouldReturnPositiveSentimentForHighRatingAndPositiveKeywords() {
        LlmAnalysisService.AnalysisResult result = llmAnalysisService.analyze(
                2L, "This is great, I love the new clean dashboard.", 5);

        assertEquals("POSITIVE", result.getSentiment());
    }

    @Test
    void analyzeShouldReturnNeutralSentimentWhenNoStrongSignal() {
        LlmAnalysisService.AnalysisResult result = llmAnalysisService.analyze(
                3L, "The product works as expected, nothing special to note.", 3);

        assertEquals("NEUTRAL", result.getSentiment());
    }
}
