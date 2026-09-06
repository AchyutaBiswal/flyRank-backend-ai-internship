package com.flyrank.feedback.service;

import com.flyrank.feedback.config.ReportProperties;
import com.flyrank.feedback.model.Feedback;
import com.flyrank.feedback.model.FeedbackAnalysis;
import com.flyrank.feedback.model.Report;
import com.flyrank.feedback.repository.FeedbackAnalysisRepository;
import com.flyrank.feedback.repository.FeedbackRepository;
import com.flyrank.feedback.repository.ReportRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Generates a simple PDF analytics report summarizing all feedback collected so far,
 * using Apache PDFBox (no external services required).
 */
@Service
public class PdfReportService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackAnalysisRepository analysisRepository;
    private final ReportRepository reportRepository;
    private final ReportProperties reportProperties;

    public PdfReportService(FeedbackRepository feedbackRepository,
                             FeedbackAnalysisRepository analysisRepository,
                             ReportRepository reportRepository,
                             ReportProperties reportProperties) {
        this.feedbackRepository = feedbackRepository;
        this.analysisRepository = analysisRepository;
        this.reportRepository = reportRepository;
        this.reportProperties = reportProperties;
    }

    public Report generateFeedbackReport() throws IOException {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        long total = feedbackList.size();
        double averageRating = feedbackRepository.averageRating();

        int positive = 0;
        int neutral = 0;
        int negative = 0;

        for (Feedback f : feedbackList) {
            Optional<FeedbackAnalysis> analysis = analysisRepository.findByFeedbackId(f.getId());
            String sentiment = analysis.map(FeedbackAnalysis::getSentiment).orElse("NEUTRAL");
            switch (sentiment) {
                case "POSITIVE" -> positive++;
                case "NEGATIVE" -> negative++;
                default -> neutral++;
            }
        }

        Path outputDir = Path.of(reportProperties.getOutputDir());
        Files.createDirectories(outputDir);

        String fileName = "feedback-report-" + System.currentTimeMillis() + ".pdf";
        Path outputPath = outputDir.resolve(fileName);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                float margin = 50;
                float y = page.getMediaBox().getHeight() - margin;
                PDType1Font titleFont = PDType1Font.HELVETICA_BOLD;
                PDType1Font bodyFont = PDType1Font.HELVETICA;

                content.beginText();
                content.setFont(titleFont, 18);
                content.newLineAtOffset(margin, y);
                content.showText("FlyRank Feedback Intelligence Report");
                content.endText();
                y -= 30;

                content.beginText();
                content.setFont(bodyFont, 11);
                content.newLineAtOffset(margin, y);
                content.showText("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                content.endText();
                y -= 30;

                content.beginText();
                content.setFont(titleFont, 13);
                content.newLineAtOffset(margin, y);
                content.showText("Summary");
                content.endText();
                y -= 20;

                String[] summaryLines = new String[] {
                        "Total feedback entries: " + total,
                        String.format("Average rating: %.2f / 5", averageRating),
                        "Positive: " + positive + "   Neutral: " + neutral + "   Negative: " + negative
                };

                for (String line : summaryLines) {
                    content.beginText();
                    content.setFont(bodyFont, 11);
                    content.newLineAtOffset(margin, y);
                    content.showText(line);
                    content.endText();
                    y -= 18;
                }

                y -= 15;
                content.beginText();
                content.setFont(titleFont, 13);
                content.newLineAtOffset(margin, y);
                content.showText("Recent Feedback");
                content.endText();
                y -= 20;

                int shown = 0;
                for (Feedback f : feedbackList) {
                    if (shown >= 10 || y < 60) break;
                    String line = "#" + f.getId() + " [" + f.getRating() + "/5] " + truncate(f.getMessage(), 90);
                    content.beginText();
                    content.setFont(bodyFont, 10);
                    content.newLineAtOffset(margin, y);
                    content.showText(line);
                    content.endText();
                    y -= 15;
                    shown++;
                }
            }

            document.save(new File(outputPath.toString()));
        }

        Report report = new Report();
        report.setReportType("FEEDBACK_SUMMARY");
        report.setFilePath(outputPath.toString());
        report.setTotalFeedback((int) total);
        report.setAverageRating(averageRating);
        report.setPositiveCount(positive);
        report.setNeutralCount(neutral);
        report.setNegativeCount(negative);

        return reportRepository.save(report);
    }

    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        String cleaned = text.replaceAll("\\s+", " ").trim();
        return cleaned.length() <= maxLength ? cleaned : cleaned.substring(0, maxLength - 3) + "...";
    }
}
