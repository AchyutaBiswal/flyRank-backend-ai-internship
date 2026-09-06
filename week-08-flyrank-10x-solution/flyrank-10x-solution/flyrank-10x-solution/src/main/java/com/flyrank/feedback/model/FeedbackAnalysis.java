package com.flyrank.feedback.model;

import java.time.LocalDateTime;

public class FeedbackAnalysis {

    private Long id;
    private Long feedbackId;
    private String sentiment;
    private String summary;
    private String keywords;
    private LocalDateTime analyzedAt;

    public FeedbackAnalysis() {
    }

    public FeedbackAnalysis(Long id, Long feedbackId, String sentiment, String summary,
                             String keywords, LocalDateTime analyzedAt) {
        this.id = id;
        this.feedbackId = feedbackId;
        this.sentiment = sentiment;
        this.summary = summary;
        this.keywords = keywords;
        this.analyzedAt = analyzedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}
