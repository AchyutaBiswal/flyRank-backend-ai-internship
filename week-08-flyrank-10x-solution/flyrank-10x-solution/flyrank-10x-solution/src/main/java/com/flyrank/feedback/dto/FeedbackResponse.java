package com.flyrank.feedback.dto;

import java.time.LocalDateTime;

public class FeedbackResponse {

    private Long id;
    private String customerName;
    private String message;
    private Integer rating;
    private String category;
    private LocalDateTime createdAt;
    private AnalysisResponse analysis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AnalysisResponse getAnalysis() {
        return analysis;
    }

    public void setAnalysis(AnalysisResponse analysis) {
        this.analysis = analysis;
    }
}
