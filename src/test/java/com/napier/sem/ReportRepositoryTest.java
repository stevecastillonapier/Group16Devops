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
 * Tests for the ReportRepository class.
 * These tests check that the repository works correctly with the database.
 */
@DisplayName("ReportRepository Tests")
class ReportRepositoryTest {
/*
    @Mock
    private Connection con;
    
    @Mock
    private PreparedStatement ps;
    
    @Mock
    private ResultSet rs;
    
    private ReportRepository repo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repo = new ReportRepository(con);
    }

    @Test
    @DisplayName("Should get report by ID")
    void testGetReportById() throws SQLException {
        // Test getting a report by its ID
        int reportId = 1;
        String expectedTitle = "Test Report";
        String expectedSql = "SELECT * FROM test";
        
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenReturn(reportId);
        when(rs.getString("title")).thenReturn(expectedTitle);
        when(rs.getString("sql_query")).thenReturn(expectedSql);
        when(rs.getString("parameter_name")).thenReturn("");
        when(rs.getString("parameter_prompt")).thenReturn("");

        Report result = repo.getReportById(reportId);

        assertNotNull(result);
        assertEquals(reportId, result.id);
        assertEquals(expectedTitle, result.title);
        assertEquals(expectedSql, result.sql);
        
        verify(ps).setInt(1, reportId);
        verify(ps).executeQuery();
    }

    @Test
    @DisplayName("Should return null when report not found")
    void testGetReportByIdNotFound() throws SQLException {
        // Test what happens when we ask for a report that doesn't exist
        int reportId = 999;
        
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        Report result = repo.getReportById(reportId);

        assertNull(result);
        verify(ps).setInt(1, reportId);
    }

    @Test
    @DisplayName("Should handle database errors")
    void testGetReportByIdError() throws SQLException {
        // Test what happens if the database has problems
        int reportId = 1;
        
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenThrow(new SQLException("Database error"));

        Report result = repo.getReportById(reportId);

        assertNull(result);
    }

    @Test
    @DisplayName("Should get all reports")
    void testGetAllReports() throws SQLException {
        // Test getting all reports from the database
        when(con.createStatement()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, false);
        
        when(rs.getInt("id")).thenReturn(1, 2);
        when(rs.getString("title")).thenReturn("Report 1", "Report 2");
        when(rs.getString("sql_query")).thenReturn("SELECT * FROM test1", "SELECT * FROM test2");
        when(rs.getString("parameter_name")).thenReturn("", "");
        when(rs.getString("parameter_prompt")).thenReturn("", "");

        List<Report> result = repo.getAllReports();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).title);
        assertEquals("Report 2", result.get(1).title);
    }

    @Test
    @DisplayName("Should return empty list when no reports")
    void testGetAllReportsEmpty() throws SQLException {
        // Test what happens when there are no reports
        when(con.createStatement()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        List<Report> result = repo.getAllReports();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

 */
}