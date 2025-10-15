package com.napier.sem;

import com.napier.sem.service.ReportService;
import com.napier.sem.repository.ReportRepository;
import com.napier.sem.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the ReportService class.
 * These tests check that the service works correctly with reports.
 */
@DisplayName("ReportService Tests")
class ReportServiceTest {

    @Mock
    private Connection con;
    
    @Mock
    private ReportRepository repo;
    
    @Mock
    private Statement stmt;
    
    @Mock
    private ResultSet rs;
    
    private ReportService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ReportService(con, repo);
    }

    @Test
    @DisplayName("Should run report")
    void testRunReport() throws SQLException {
        // Test running a report successfully
        int reportId = 1;
        Report report = new Report();
        report.id = reportId;
        report.title = "Test Report";
        report.sql = "SELECT * FROM test";
        report.parameterName = "";
        report.parameterPrompt = "";
        
        when(repo.getReportById(reportId)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);

        service.runReport(reportId);

        verify(repo).getReportById(reportId);
        verify(con).createStatement();
        verify(stmt).executeQuery(report.sql);
    }

    @Test
    @DisplayName("Should handle invalid report ID")
    void testRunReportInvalidId() {
        // Test what happens when we try to run a report that doesn't exist
        int invalidReportId = 999;
        when(repo.getReportById(invalidReportId)).thenReturn(null);

        service.runReport(invalidReportId);

        verify(repo).getReportById(invalidReportId);
        verifyNoInteractions(con);
    }

    @Test
    @DisplayName("Should get all reports")
    void testGetAllReports() {
        // Test getting all reports through the service
        List<Report> expectedReports = new ArrayList<>();
        Report report1 = new Report();
        report1.id = 1;
        report1.title = "Report 1";
        expectedReports.add(report1);
        
        Report report2 = new Report();
        report2.id = 2;
        report2.title = "Report 2";
        expectedReports.add(report2);
        
        when(repo.getAllReports()).thenReturn(expectedReports);

        List<Report> result = service.getAllReports();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).title);
        assertEquals("Report 2", result.get(1).title);
        verify(repo).getAllReports();
    }

    @Test
    @DisplayName("Should get report by ID")
    void testGetReportById() {
        // Test getting a specific report through the service
        int reportId = 1;
        Report expectedReport = new Report();
        expectedReport.id = reportId;
        expectedReport.title = "Test Report";
        
        when(repo.getReportById(reportId)).thenReturn(expectedReport);

        Report result = service.getReportById(reportId);

        assertNotNull(result);
        assertEquals(reportId, result.id);
        assertEquals("Test Report", result.title);
        verify(repo).getReportById(reportId);
    }

    @Test
    @DisplayName("Should handle report with parameters")
    void testRunReportWithParameters() throws SQLException {
        // Test reports that need user input (like asking for a continent)
        Report report = new Report();
        report.id = 1;
        report.title = "Test Report";
        report.sql = "SELECT * FROM test WHERE region = '%region%'";
        report.parameterName = "region";
        report.parameterPrompt = "Please enter region";
        
        when(repo.getReportById(1)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);

        service.runReport(1);

        verify(repo).getReportById(1);
        verify(con).createStatement();
        verify(stmt).executeQuery(anyString());
    }

    @Test
    @DisplayName("Should handle database errors")
    void testRunReportError() throws SQLException {
        // Test what happens if there's a database error
        Report report = new Report();
        report.id = 1;
        report.title = "Test Report";
        report.sql = "SELECT * FROM test";
        report.parameterName = "";
        report.parameterPrompt = "";
        
        when(repo.getReportById(1)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenThrow(new SQLException("Database error"));

        service.runReport(1);

        verify(repo).getReportById(1);
        verify(con).createStatement();
        verify(stmt).executeQuery(anyString());
    }
}