package com.flyrank.feedback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyrank.feedback.dto.FeedbackRequest;
import com.flyrank.feedback.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FeedbackControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String loginAndGetToken() throws Exception {
        LoginRequest login = new LoginRequest();
        login.setEmail("john@flyrank.com");
        login.setPassword("password123");

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        return objectMapper.readTree(body).get("token").asText();
    }

    @Test
    void submittingFeedbackWithoutTokenShouldBeUnauthorized() throws Exception {
        FeedbackRequest request = new FeedbackRequest();
        request.setCustomerName("Anon");
        request.setMessage("Testing without auth");
        request.setRating(3);

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void submitAndFetchFeedbackShouldSucceedForAuthenticatedUser() throws Exception {
        String token = loginAndGetToken();

        FeedbackRequest request = new FeedbackRequest();
        request.setCustomerName("Integration Tester");
        request.setMessage("The reporting feature is excellent and very helpful.");
        request.setRating(5);
        request.setCategory("REPORTING");

        MvcResult createResult = mockMvc.perform(post("/api/feedback")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String body = createResult.getResponse().getContentAsString();
        long feedbackId = objectMapper.readTree(body).get("id").asLong();

        mockMvc.perform(get("/api/feedback/" + feedbackId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Integration Tester"));
    }

    @Test
    void submittingFeedbackWithInvalidRatingShouldFailValidation() throws Exception {
        String token = loginAndGetToken();

        FeedbackRequest request = new FeedbackRequest();
        request.setCustomerName("Bad Rating User");
        request.setMessage("Rating out of allowed range");
        request.setRating(9);

        mockMvc.perform(post("/api/feedback")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void analyzeFeedbackShouldReturnSentimentAndSummary() throws Exception {
        String token = loginAndGetToken();

        FeedbackRequest request = new FeedbackRequest();
        request.setCustomerName("Analyze Tester");
        request.setMessage("The app keeps crashing and it is very frustrating.");
        request.setRating(1);

        MvcResult createResult = mockMvc.perform(post("/api/feedback")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String body = createResult.getResponse().getContentAsString();
        long feedbackId = objectMapper.readTree(body).get("id").asLong();

        mockMvc.perform(post("/api/feedback/" + feedbackId + "/analyze")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("NEGATIVE"));
    }
}
