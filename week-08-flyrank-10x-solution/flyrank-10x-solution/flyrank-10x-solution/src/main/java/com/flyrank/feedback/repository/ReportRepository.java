package com.flyrank.feedback.repository;

import com.flyrank.feedback.model.Report;
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
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Report mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Report report = new Report();
        report.setId(rs.getLong("id"));
        report.setReportType(rs.getString("report_type"));
        report.setFilePath(rs.getString("file_path"));
        report.setTotalFeedback(rs.getInt("total_feedback"));
        report.setAverageRating(rs.getDouble("average_rating"));
        report.setPositiveCount(rs.getInt("positive_count"));
        report.setNeutralCount(rs.getInt("neutral_count"));
        report.setNegativeCount(rs.getInt("negative_count"));
        Timestamp ts = rs.getTimestamp("generated_at");
        report.setGeneratedAt(ts != null ? ts.toLocalDateTime() : null);
        return report;
    }

    public Report save(Report report) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO reports (report_type, file_path, total_feedback, average_rating, " +
                            "positive_count, neutral_count, negative_count, generated_at) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, report.getReportType());
            ps.setString(2, report.getFilePath());
            ps.setInt(3, report.getTotalFeedback());
            ps.setDouble(4, report.getAverageRating());
            ps.setInt(5, report.getPositiveCount());
            ps.setInt(6, report.getNeutralCount());
            ps.setInt(7, report.getNegativeCount());
            ps.setTimestamp(8, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        report.setId(key != null ? key.longValue() : null);
        report.setGeneratedAt(now);
        return report;
    }

    public Optional<Report> findById(Long id) {
        try {
            Report report = jdbcTemplate.queryForObject(
                    "SELECT * FROM reports WHERE id = ?", ReportRepository::mapRow, id);
            return Optional.ofNullable(report);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Report> findAll() {
        return jdbcTemplate.query("SELECT * FROM reports ORDER BY generated_at DESC", ReportRepository::mapRow);
    }
}
