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
 * Unit tests for the ReportService class.
 * These tests verify that the service layer correctly handles business logic
 * for running reports and managing user interactions.
 */
@DisplayName("ReportService Tests")
class ReportServiceTest {

    @Mock
    private Connection mockConnection;
    
    @Mock
    private ReportRepository mockRepository;
    
    @Mock
    private Statement mockStatement;
    
    @Mock
    private ResultSet mockResultSet;
    
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportService = new ReportService(mockConnection, mockRepository);
    }

    @Test
    @DisplayName("Should run report successfully when report exists")
    void testRunReport_Success() throws SQLException {
        // Arrange
        int reportId = 1;
        Report mockReport = new Report();
        mockReport.id = reportId;
        mockReport.title = "Test Report";
        mockReport.sql = "SELECT * FROM test";
        mockReport.parameterName = "";
        mockReport.parameterPrompt = "";
        
        when(mockRepository.getReportById(reportId)).thenReturn(mockReport);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Act
        reportService.runReport(reportId);

        // Assert
        verify(mockRepository).getReportById(reportId);
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery(mockReport.sql);
    }

    @Test
    @DisplayName("Should handle invalid report ID gracefully")
    void testRunReport_InvalidId() {
        // Arrange
        int invalidReportId = 999;
        when(mockRepository.getReportById(invalidReportId)).thenReturn(null);

        // Act
        reportService.runReport(invalidReportId);

        // Assert
        verify(mockRepository).getReportById(invalidReportId);
        verifyNoInteractions(mockConnection);
    }

    @Test
    @DisplayName("Should retrieve all reports successfully")
    void testGetAllReports_Success() {
        // Arrange
        List<Report> expectedReports = new ArrayList<>();
        Report report1 = new Report();
        report1.id = 1;
        report1.title = "Report 1";
        expectedReports.add(report1);
        
        Report report2 = new Report();
        report2.id = 2;
        report2.title = "Report 2";
        expectedReports.add(report2);
        
        when(mockRepository.getAllReports()).thenReturn(expectedReports);

        // Act
        List<Report> result = reportService.getAllReports();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).title);
        assertEquals("Report 2", result.get(1).title);
        verify(mockRepository).getAllReports();
    }

    @Test
    @DisplayName("Should retrieve report by ID successfully")
    void testGetReportById_Success() {
        // Arrange
        int reportId = 1;
        Report expectedReport = new Report();
        expectedReport.id = reportId;
        expectedReport.title = "Test Report";
        
        when(mockRepository.getReportById(reportId)).thenReturn(expectedReport);

        // Act
        Report result = reportService.getReportById(reportId);

        // Assert
        assertNotNull(result);
        assertEquals(reportId, result.id);
        assertEquals("Test Report", result.title);
        verify(mockRepository).getReportById(reportId);
    }

    @Test
    @DisplayName("Should handle report with parameters")
    void testRunReport_WithParameters() throws SQLException {
        // Arrange
        Report mockReport = new Report();
        mockReport.id = 1;
        mockReport.title = "Test Report";
        mockReport.sql = "SELECT * FROM test WHERE region = '%region%'";
        mockReport.parameterName = "region";
        mockReport.parameterPrompt = "Please enter region";
        
        when(mockRepository.getReportById(1)).thenReturn(mockReport);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Act
        reportService.runReport(1);

        // Assert
        verify(mockRepository).getReportById(1);
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery(anyString());
    }

    @Test
    @DisplayName("Should handle SQL exception during report execution")
    void testRunReport_SQLException() throws SQLException {
        // Arrange
        Report mockReport = new Report();
        mockReport.id = 1;
        mockReport.title = "Test Report";
        mockReport.sql = "SELECT * FROM test";
        mockReport.parameterName = "";
        mockReport.parameterPrompt = "";
        
        when(mockRepository.getReportById(1)).thenReturn(mockReport);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        reportService.runReport(1);

        // Assert
        verify(mockRepository).getReportById(1);
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery(anyString());
    }
}