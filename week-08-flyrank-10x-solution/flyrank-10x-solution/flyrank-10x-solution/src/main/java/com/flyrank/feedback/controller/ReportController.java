package com.flyrank.feedback.controller;

import com.flyrank.feedback.dto.ReportResponse;
import com.flyrank.feedback.exception.ResourceNotFoundException;
import com.flyrank.feedback.model.Report;
import com.flyrank.feedback.repository.ReportRepository;
import com.flyrank.feedback.service.PdfReportService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final PdfReportService pdfReportService;
    private final ReportRepository reportRepository;

    public ReportController(PdfReportService pdfReportService, ReportRepository reportRepository) {
        this.pdfReportService = pdfReportService;
        this.reportRepository = reportRepository;
    }

    @PostMapping("/generate")
    public ResponseEntity<ReportResponse> generate() throws IOException {
        Report report = pdfReportService.generateFeedbackReport();
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(report));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> list() {
        List<ReportResponse> reports = reportRepository.findAll().stream().map(this::toResponse).toList();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));

        Path path = Path.of(report.getFilePath());
        if (!Files.exists(path)) {
            throw new ResourceNotFoundException("Report file is missing on disk for id: " + id);
        }

        Resource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"")
                .body(resource);
    }

    private ReportResponse toResponse(Report report) {
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setReportType(report.getReportType());
        response.setTotalFeedback(report.getTotalFeedback());
        response.setAverageRating(report.getAverageRating());
        response.setPositiveCount(report.getPositiveCount());
        response.setNeutralCount(report.getNeutralCount());
        response.setNegativeCount(report.getNegativeCount());
        response.setGeneratedAt(report.getGeneratedAt());
        response.setDownloadUrl("/api/reports/" + report.getId() + "/download");
        return response;
    }
}
