package com.flyrank.feedback.repository;

import com.flyrank.feedback.model.Feedback;
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
public class FeedbackRepository {

    private final JdbcTemplate jdbcTemplate;

    public FeedbackRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Feedback mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getLong("id"));
        feedback.setUserId(rs.getLong("user_id"));
        feedback.setCustomerName(rs.getString("customer_name"));
        feedback.setMessage(rs.getString("message"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setCategory(rs.getString("category"));
        Timestamp ts = rs.getTimestamp("created_at");
        feedback.setCreatedAt(ts != null ? ts.toLocalDateTime() : null);
        return feedback;
    }

    public Feedback save(Feedback feedback) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO feedback (user_id, customer_name, message, rating, category, created_at) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, feedback.getUserId());
            ps.setString(2, feedback.getCustomerName());
            ps.setString(3, feedback.getMessage());
            ps.setInt(4, feedback.getRating());
            ps.setString(5, feedback.getCategory());
            ps.setTimestamp(6, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        feedback.setId(key != null ? key.longValue() : null);
        feedback.setCreatedAt(now);
        return feedback;
    }

    public Optional<Feedback> findById(Long id) {
        try {
            Feedback feedback = jdbcTemplate.queryForObject(
                    "SELECT * FROM feedback WHERE id = ?", FeedbackRepository::mapRow, id);
            return Optional.ofNullable(feedback);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Feedback> findByUserId(Long userId) {
        return jdbcTemplate.query(
                "SELECT * FROM feedback WHERE user_id = ? ORDER BY created_at DESC",
                FeedbackRepository::mapRow, userId);
    }

    public List<Feedback> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM feedback ORDER BY created_at DESC", FeedbackRepository::mapRow);
    }

    public long count() {
        Long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM feedback", Long.class);
        return total != null ? total : 0L;
    }

    public Double averageRating() {
        Double avg = jdbcTemplate.queryForObject("SELECT AVG(rating) FROM feedback", Double.class);
        return avg != null ? avg : 0.0;
    }
}
