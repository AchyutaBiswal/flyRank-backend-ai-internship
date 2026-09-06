package com.flyrank.feedback.model;

import java.time.LocalDateTime;

public class Report {

    private Long id;
    private String reportType;
    private String filePath;
    private Integer totalFeedback;
    private Double averageRating;
    private Integer positiveCount;
    private Integer neutralCount;
    private Integer negativeCount;
    private LocalDateTime generatedAt;

    public Report() {
    }

    public Report(Long id, String reportType, String filePath, Integer totalFeedback, Double averageRating,
                   Integer positiveCount, Integer neutralCount, Integer negativeCount, LocalDateTime generatedAt) {
        this.id = id;
        this.reportType = reportType;
        this.filePath = filePath;
        this.totalFeedback = totalFeedback;
        this.averageRating = averageRating;
        this.positiveCount = positiveCount;
        this.neutralCount = neutralCount;
        this.negativeCount = negativeCount;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(Integer totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getPositiveCount() {
        return positiveCount;
    }

    public void setPositiveCount(Integer positiveCount) {
        this.positiveCount = positiveCount;
    }

    public Integer getNeutralCount() {
        return neutralCount;
    }

    public void setNeutralCount(Integer neutralCount) {
        this.neutralCount = neutralCount;
    }

    public Integer getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(Integer negativeCount) {
        this.negativeCount = negativeCount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
