package com.flyrank.feedback.repository;

import com.flyrank.feedback.model.FeedbackAnalysis;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackAnalysisRepository {

    private final JdbcTemplate jdbcTemplate;

    public FeedbackAnalysisRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static FeedbackAnalysis mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        FeedbackAnalysis analysis = new FeedbackAnalysis();
        analysis.setId(rs.getLong("id"));
        analysis.setFeedbackId(rs.getLong("feedback_id"));
        analysis.setSentiment(rs.getString("sentiment"));
        analysis.setSummary(rs.getString("summary"));
        analysis.setKeywords(rs.getString("keywords"));
        Timestamp ts = rs.getTimestamp("analyzed_at");
        analysis.setAnalyzedAt(ts != null ? ts.toLocalDateTime() : null);
        return analysis;
    }

    public Optional<FeedbackAnalysis> findByFeedbackId(Long feedbackId) {
        try {
            FeedbackAnalysis analysis = jdbcTemplate.queryForObject(
                    "SELECT * FROM feedback_analysis WHERE feedback_id = ?", mapRowRef(), feedbackId);
            return Optional.ofNullable(analysis);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private org.springframework.jdbc.core.RowMapper<FeedbackAnalysis> mapRowRef() {
        return FeedbackAnalysisRepository::mapRow;
    }

    public List<FeedbackAnalysis> findAll() {
        return jdbcTemplate.query("SELECT * FROM feedback_analysis", mapRowRef());
    }

    public long countBySentiment(String sentiment) {
        Long count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM feedback_analysis WHERE sentiment = ?", Long.class, sentiment);
        return count != null ? count : 0L;
    }

    public FeedbackAnalysis save(FeedbackAnalysis analysis) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO feedback_analysis (feedback_id, sentiment, summary, keywords, analyzed_at) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, analysis.getFeedbackId());
            ps.setString(2, analysis.getSentiment());
            ps.setString(3, analysis.getSummary());
            ps.setString(4, analysis.getKeywords());
            ps.setTimestamp(5, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        analysis.setId(key != null ? key.longValue() : null);
        analysis.setAnalyzedAt(now);
        return analysis;
    }
}
