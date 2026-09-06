package com.flyrank.feedback.controller;

import com.flyrank.feedback.dto.AnalysisResponse;
import com.flyrank.feedback.dto.FeedbackRequest;
import com.flyrank.feedback.dto.FeedbackResponse;
import com.flyrank.feedback.security.AppUserPrincipal;
import com.flyrank.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> submit(@AuthenticationPrincipal AppUserPrincipal principal,
                                                     @Valid @RequestBody FeedbackRequest request) {
        FeedbackResponse response = feedbackService.submitFeedback(principal.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> list(@AuthenticationPrincipal AppUserPrincipal principal) {
        boolean isAdmin = principal.getUser().getRole().equals("ADMIN");
        return ResponseEntity.ok(feedbackService.listFeedback(principal.getId(), isAdmin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> get(@AuthenticationPrincipal AppUserPrincipal principal,
                                                 @PathVariable Long id) {
        boolean isAdmin = principal.getUser().getRole().equals("ADMIN");
        return ResponseEntity.ok(feedbackService.getFeedback(id, principal.getId(), isAdmin));
    }

    @PostMapping("/{id}/analyze")
    public ResponseEntity<AnalysisResponse> analyze(@AuthenticationPrincipal AppUserPrincipal principal,
                                                      @PathVariable Long id) {
        boolean isAdmin = principal.getUser().getRole().equals("ADMIN");
        return ResponseEntity.ok(feedbackService.analyzeFeedback(id, principal.getId(), isAdmin));
    }
}
