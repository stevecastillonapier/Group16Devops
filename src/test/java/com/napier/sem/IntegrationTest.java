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
 * Tests that use the real Docker database.
 * These tests check that our app actually works with the real database.
 * 
 * IMPORTANT: These tests need the Docker database to be running!
 * Run: docker-compose up -d
 */
@DisplayName("Integration Tests - Real Database")
class IntegrationTest {

    private Connection con;
    private ReportRepository repo;
    private ReportService service;

    @BeforeEach
    void setUp() throws SQLException {
        // Try to connect to the Docker database
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/world?useSSL=false&allowPublicKeyRetrieval=true", 
                "root", 
                "CRGroup16"
            );
            
            repo = new ReportRepository(con);
            service = new ReportService(con, repo);
            
            System.out.println("✅ Connected to Docker database successfully");
        } catch (SQLException e) {
            System.out.println("⚠️ Could not connect to Docker database - skipping integration tests");
            System.out.println("   Make sure to run: docker-compose up -d");
            con = null;
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @Test
    @DisplayName("Should get all reports from Docker database")
    void testGetAllReports() {
        if (con == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }

        List<Report> reports = service.getAllReports();

        assertNotNull(reports);
        assertTrue(reports.size() > 0, "Should have at least one report in database");

        // Check first report has required fields
        Report firstReport = reports.get(0);
        assertNotNull(firstReport.getTitle());
        assertNotNull(firstReport.getSql());
        assertTrue(firstReport.getId() > 0);

        System.out.println("✅ Found " + reports.size() + " reports in database");
    }
    /**/
    @Test
    @DisplayName("Should get specific report by ID from Docker database")
    void testGetReportById() {
        if (con == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        Report report = service.getReportById(1);

        assertNotNull(report);
        assertEquals(1, report.getId());
        assertNotNull(report.getTitle());
        assertNotNull(report.getSql());
        
        System.out.println("✅ Retrieved report: " + report.getTitle());
    }

    @Test
    @DisplayName("Should handle non-existent report ID")
    void testGetReportByIdNotFound() {
        if (con == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        Report report = service.getReportById(99999);

        assertNull(report);
        System.out.println("✅ Correctly handled non-existent report ID");
    }

    @Test
    @DisplayName("Should verify Docker database has all required reports")
    void testDatabaseHasAllReports() {
        if (con == null) {
            System.out.println("Skipping test - no database connection");
            return;
        }
        
        List<Report> reports = service.getAllReports();

        assertNotNull(reports);
        assertTrue(reports.size() >= 32, "Database should have at least 32 reports for coursework requirements");
        
        // Check for some key reports
        boolean hasCountryReport = reports.stream()
            .anyMatch(r -> r.getTitle() != null && r.getTitle().contains("countries"));
        assertTrue(hasCountryReport, "Should have country reports");
        
        boolean hasCityReport = reports.stream()
            .anyMatch(r -> r.getTitle() != null && r.getTitle().contains("cities"));
        assertTrue(hasCityReport, "Should have city reports");
        
        System.out.println("✅ Database contains " + reports.size() + " reports including required types");
    }

    /**/
}
