package com.flyrank.feedback.service;

import com.flyrank.feedback.dto.AnalysisResponse;
import com.flyrank.feedback.dto.FeedbackRequest;
import com.flyrank.feedback.dto.FeedbackResponse;
import com.flyrank.feedback.exception.ResourceNotFoundException;
import com.flyrank.feedback.model.Feedback;
import com.flyrank.feedback.model.FeedbackAnalysis;
import com.flyrank.feedback.repository.FeedbackAnalysisRepository;
import com.flyrank.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackAnalysisRepository analysisRepository;
    private final LlmAnalysisService llmAnalysisService;

    public FeedbackService(FeedbackRepository feedbackRepository,
                            FeedbackAnalysisRepository analysisRepository,
                            LlmAnalysisService llmAnalysisService) {
        this.feedbackRepository = feedbackRepository;
        this.analysisRepository = analysisRepository;
        this.llmAnalysisService = llmAnalysisService;
    }

    public FeedbackResponse submitFeedback(Long userId, FeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setCustomerName(request.getCustomerName());
        feedback.setMessage(request.getMessage());
        feedback.setRating(request.getRating());
        feedback.setCategory(request.getCategory());

        Feedback saved = feedbackRepository.save(feedback);
        return toResponse(saved, null);
    }

    public FeedbackResponse getFeedback(Long id, Long requesterId, boolean isAdmin) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));

        if (!isAdmin && !feedback.getUserId().equals(requesterId)) {
            throw new ResourceNotFoundException("Feedback not found with id: " + id);
        }

        Optional<FeedbackAnalysis> analysis = analysisRepository.findByFeedbackId(id);
        return toResponse(feedback, analysis.orElse(null));
    }

    public List<FeedbackResponse> listFeedback(Long requesterId, boolean isAdmin) {
        List<Feedback> feedbackList = isAdmin
                ? feedbackRepository.findAll()
                : feedbackRepository.findByUserId(requesterId);

        return feedbackList.stream()
                .map(f -> toResponse(f, analysisRepository.findByFeedbackId(f.getId()).orElse(null)))
                .collect(Collectors.toList());
    }

    public AnalysisResponse analyzeFeedback(Long id, Long requesterId, boolean isAdmin) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));

        if (!isAdmin && !feedback.getUserId().equals(requesterId)) {
            throw new ResourceNotFoundException("Feedback not found with id: " + id);
        }

        Optional<FeedbackAnalysis> existing = analysisRepository.findByFeedbackId(id);
        if (existing.isPresent()) {
            return toAnalysisResponse(existing.get());
        }

        LlmAnalysisService.AnalysisResult result = llmAnalysisService.analyze(
                feedback.getId(), feedback.getMessage(), feedback.getRating());

        FeedbackAnalysis analysis = new FeedbackAnalysis();
        analysis.setFeedbackId(feedback.getId());
        analysis.setSentiment(result.getSentiment());
        analysis.setSummary(result.getSummary());
        analysis.setKeywords(result.getKeywords());

        FeedbackAnalysis saved = analysisRepository.save(analysis);
        return toAnalysisResponse(saved);
    }

    private FeedbackResponse toResponse(Feedback feedback, FeedbackAnalysis analysis) {
        FeedbackResponse response = new FeedbackResponse();
        response.setId(feedback.getId());
        response.setCustomerName(feedback.getCustomerName());
        response.setMessage(feedback.getMessage());
        response.setRating(feedback.getRating());
        response.setCategory(feedback.getCategory());
        response.setCreatedAt(feedback.getCreatedAt());
        if (analysis != null) {
            response.setAnalysis(toAnalysisResponse(analysis));
        }
        return response;
    }

    private AnalysisResponse toAnalysisResponse(FeedbackAnalysis analysis) {
        return new AnalysisResponse(analysis.getSentiment(), analysis.getSummary(),
                analysis.getKeywords(), analysis.getAnalyzedAt());
    }
}
