package com.flyrank.feedback.model;

import java.time.LocalDateTime;

public class Feedback {

    private Long id;
    private Long userId;
    private String customerName;
    private String message;
    private Integer rating;
    private String category;
    private LocalDateTime createdAt;

    public Feedback() {
    }

    public Feedback(Long id, Long userId, String customerName, String message, Integer rating,
                     String category, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.customerName = customerName;
        this.message = message;
        this.rating = rating;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
