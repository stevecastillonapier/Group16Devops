package com.napier.sem;

import com.napier.sem.model.Report;
import com.napier.sem.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ReportRepository class.
 * These tests verify that the repository correctly handles database operations
 * for retrieving report definitions.
 */
@DisplayName("ReportRepository Tests")
class ReportRepositoryTest {

    @Mock
    private Connection mockConnection;
    
    @Mock
    private PreparedStatement mockPreparedStatement;
    
    @Mock
    private ResultSet mockResultSet;
    
    private ReportRepository reportRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportRepository = new ReportRepository(mockConnection);
    }

    @Test
    @DisplayName("Should retrieve report by ID successfully")
    void testGetReportById_Success() throws SQLException {
        // Arrange
        int reportId = 1;
        String expectedTitle = "Test Report";
        String expectedSql = "SELECT * FROM test";
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(reportId);
        when(mockResultSet.getString("title")).thenReturn(expectedTitle);
        when(mockResultSet.getString("sql_query")).thenReturn(expectedSql);
        when(mockResultSet.getString("parameter_name")).thenReturn("");
        when(mockResultSet.getString("parameter_prompt")).thenReturn("");

        // Act
        Report result = reportRepository.getReportById(reportId);

        // Assert
        assertNotNull(result);
        assertEquals(reportId, result.id);
        assertEquals(expectedTitle, result.title);
        assertEquals(expectedSql, result.sql);
        
        verify(mockPreparedStatement).setInt(1, reportId);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    @DisplayName("Should return null when report not found")
    void testGetReportById_NotFound() throws SQLException {
        // Arrange
        int reportId = 999;
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        Report result = reportRepository.getReportById(reportId);

        // Assert
        assertNull(result);
        verify(mockPreparedStatement).setInt(1, reportId);
    }

    @Test
    @DisplayName("Should handle SQL exception gracefully")
    void testGetReportById_SQLException() throws SQLException {
        // Arrange
        int reportId = 1;
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Database error"));

        // Act
        Report result = reportRepository.getReportById(reportId);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Should retrieve all reports successfully")
    void testGetAllReports_Success() throws SQLException {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        
        // First call to next() returns report1
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("title")).thenReturn("Report 1", "Report 2");
        when(mockResultSet.getString("sql_query")).thenReturn("SELECT * FROM test1", "SELECT * FROM test2");
        when(mockResultSet.getString("parameter_name")).thenReturn("", "");
        when(mockResultSet.getString("parameter_prompt")).thenReturn("", "");

        // Act
        List<Report> result = reportRepository.getAllReports();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).title);
        assertEquals("Report 2", result.get(1).title);
    }

    @Test
    @DisplayName("Should return empty list when no reports found")
    void testGetAllReports_EmptyResult() throws SQLException {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<Report> result = reportRepository.getAllReports();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}