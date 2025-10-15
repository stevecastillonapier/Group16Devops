package com.napier.sem;

import com.napier.sem.repository.ReportRepository;
import com.napier.sem.service.ReportService;
import com.napier.sem.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the ReportService and ReportRepository.
 * These tests verify that the components work together correctly
 * with a real database connection using Docker.
 * 
 * IMPORTANT: These tests require the Docker database to be running!
 * Run: docker-compose up -d
 */
@DisplayName("Integration Tests - Real Database")
class IntegrationTest {

    private Connection connection;
    private ReportRepository reportRepository;
    private ReportService reportService;

    @BeforeEach
    void setUp() throws SQLException {
        // Try to connect to the Docker database
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/world?useSSL=false&allowPublicKeyRetrieval=true", 
                "root", 
                "example"
            );
            
            reportRepository = new ReportRepository(connection);
            reportService = new ReportService(connection, reportRepository);
            
            System.out.println("✅ Connected to Docker database successfully");
        } catch (SQLException e) {
            System.out.println("⚠️ Could not connect to Docker database - skipping integration tests");
            System.out.println("   Make sure to run: docker-compose up -d");
            connection = null;
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    @DisplayName("Should retrieve all reports from Docker database")
    void testGetAllReports_Integration() {
        if (connection == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        // Act
        List<Report> reports = reportService.getAllReports();

        // Assert
        assertNotNull(reports);
        assertTrue(reports.size() > 0, "Should have at least one report in database");
        
        // Verify first report has required fields
        Report firstReport = reports.get(0);
        assertNotNull(firstReport.title);
        assertNotNull(firstReport.sql);
        assertTrue(firstReport.id > 0);
        
        System.out.println("✅ Found " + reports.size() + " reports in database");
    }

    @Test
    @DisplayName("Should retrieve specific report by ID from Docker database")
    void testGetReportById_Integration() {
        if (connection == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        // Act
        Report report = reportService.getReportById(1);

        // Assert
        assertNotNull(report);
        assertEquals(1, report.id);
        assertNotNull(report.title);
        assertNotNull(report.sql);
        
        System.out.println("✅ Retrieved report: " + report.title);
    }

    @Test
    @DisplayName("Should handle non-existent report ID gracefully")
    void testGetReportById_NonExistent() {
        if (connection == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        // Act
        Report report = reportService.getReportById(99999);

        // Assert
        assertNull(report);
        System.out.println("✅ Correctly handled non-existent report ID");
    }

    @Test
    @DisplayName("Should verify Docker database has all required reports")
    void testDatabaseHasAllRequiredReports() {
        if (connection == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        // Act
        List<Report> reports = reportService.getAllReports();

        // Assert
        assertNotNull(reports);
        assertTrue(reports.size() >= 32, "Database should have at least 32 reports for coursework requirements");
        
        // Check for some key reports
        boolean hasCountryReport = reports.stream()
            .anyMatch(r -> r.title != null && r.title.contains("countries"));
        assertTrue(hasCountryReport, "Should have country reports");
        
        boolean hasCityReport = reports.stream()
            .anyMatch(r -> r.title != null && r.title.contains("cities"));
        assertTrue(hasCityReport, "Should have city reports");
        
        System.out.println("✅ Database contains " + reports.size() + " reports including required types");
    }
}
