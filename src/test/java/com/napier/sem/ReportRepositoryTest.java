package com.napier.sem;

import com.napier.sem.model.Report;
import com.napier.sem.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the ReportRepository class.
 * These tests check that the repository works correctly with the database.
 */
@DisplayName("ReportRepository Tests")
class ReportRepositoryTest {

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
            assertEquals(reportId, result.getId());
            assertEquals(expectedTitle, result.getTitle());
            assertEquals(expectedSql, result.getSql());

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
        // Mock objects
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);

        // Simulate two rows in the result set
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("id")).thenReturn(1, 2);
        when(rs.getString("title")).thenReturn("Report 1", "Report 2");
        when(rs.getString("sql_query")).thenReturn("SELECT * FROM test1", "SELECT * FROM test2");
        when(rs.getString("parameter_name")).thenReturn("", "");
        when(rs.getString("parameter_prompt")).thenReturn("", "");

        // Execute the method
        List<Report> result = repo.getAllReports();

        // Verify
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).getTitle());
        assertEquals("Report 2", result.get(1).getTitle());
    }


    @Test
    @DisplayName("Should return empty list when no reports")
    void testGetAllReportsEmpty() throws SQLException {
        Statement stmt = mock(Statement.class);  // Mock a Statement
        ResultSet rs = mock(ResultSet.class);    // Mock a ResultSet

        // Mock behavior
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(false); // No rows

        // Call the repository method
        List<Report> result = repo.getAllReports();

        // Assertions
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    /**/
}