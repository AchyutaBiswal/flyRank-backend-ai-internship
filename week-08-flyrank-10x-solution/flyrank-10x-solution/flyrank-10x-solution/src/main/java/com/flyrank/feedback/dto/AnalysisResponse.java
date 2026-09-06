package com.flyrank.feedback.dto;

import java.time.LocalDateTime;

public class AnalysisResponse {

    private String sentiment;
    private String summary;
    private String keywords;
    private LocalDateTime analyzedAt;

    public AnalysisResponse() {
    }

    public AnalysisResponse(String sentiment, String summary, String keywords, LocalDateTime analyzedAt) {
        this.sentiment = sentiment;
        this.summary = summary;
        this.keywords = keywords;
        this.analyzedAt = analyzedAt;
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
