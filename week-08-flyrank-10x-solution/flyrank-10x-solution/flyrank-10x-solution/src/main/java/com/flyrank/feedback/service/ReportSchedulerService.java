package com.flyrank.feedback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Background job that automatically regenerates the feedback analytics PDF report
 * on a schedule (default: once a day at 01:00, configurable via app.reports.scheduler-cron).
 */
@Service
public class ReportSchedulerService {

    private static final Logger log = LoggerFactory.getLogger(ReportSchedulerService.class);

    private final PdfReportService pdfReportService;

    public ReportSchedulerService(PdfReportService pdfReportService) {
        this.pdfReportService = pdfReportService;
    }

    @Scheduled(cron = "${app.reports.scheduler-cron}")
    public void generateScheduledReport() {
        try {
            var report = pdfReportService.generateFeedbackReport();
            log.info("Scheduled report generated successfully: id={}, file={}", report.getId(), report.getFilePath());
        } catch (IOException e) {
            log.error("Failed to generate scheduled feedback report", e);
        }
    }
}
