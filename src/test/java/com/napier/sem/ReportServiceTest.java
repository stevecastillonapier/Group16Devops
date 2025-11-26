package com.napier.sem;

import com.napier.sem.service.ReportService;
import com.napier.sem.repository.ReportRepository;
import com.napier.sem.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    @DisplayName("Should run report and print realistic table")
    void testRunReport() throws SQLException {
        int reportId = 1;
        Report report = new Report();
        report.setId(reportId);
        report.setTitle("Test Report");
        report.setSql("SELECT * FROM test");
        report.setParameterName("");
        report.setParameterPrompt("");

        // --- Mock ResultSetMetaData (2 columns: id, name)
        ResultSetMetaData md = mock(ResultSetMetaData.class);
        when(md.getColumnCount()).thenReturn(2);
        when(md.getColumnLabel(1)).thenReturn("id");
        when(md.getColumnLabel(2)).thenReturn("Title");

        // --- Mock ResultSet with realistic data
        when(rs.getMetaData()).thenReturn(md);
        when(rs.next()).thenReturn(true, false); // only one row
        when(rs.getObject(1)).thenReturn(1);
        when(rs.getObject(2)).thenReturn("Report 1");

        // --- Mock repository, connection, and statement
        when(repo.getReportById(reportId)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);

        // --- Run the method
        service.runReport(reportId);

        // --- Verify interactions
        verify(repo).getReportById(reportId);
        verify(con).createStatement();
        verify(stmt).executeQuery(report.getSql());
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
        report1.setId(1);// = 1;
        report1.setTitle("Report 1");// = ;
        expectedReports.add(report1);
        
        Report report2 = new Report();
        report2.setId(2); //= 2;
        report2.setTitle("Report 2"); //= ;
        expectedReports.add(report2);
        
        when(repo.getAllReports()).thenReturn(expectedReports);

        List<Report> result = service.getAllReports();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Report 1", result.get(0).getTitle());
        assertEquals("Report 2", result.get(1).getTitle());
        verify(repo).getAllReports();
    }

    @Test
    @DisplayName("Should get report by ID")
    void testGetReportById() {
        // Test getting a specific report through the service
        int reportId = 1;
        Report expectedReport = new Report();
        expectedReport.setId(reportId); //= ;
        expectedReport.setTitle("Test Report");// = ;
        
        when(repo.getReportById(reportId)).thenReturn(expectedReport);

        Report result = service.getReportById(reportId);

        assertNotNull(result);
        assertEquals(reportId, result.getId());
        assertEquals("Test Report", result.getTitle());
        verify(repo).getReportById(reportId);
    }
    @Test
    @DisplayName("Should handle report with parameters")
    void testRunReportWithParameters() throws SQLException {
        // Simulate user typing “Europe”
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("Europe\n".getBytes());
        Scanner fakeScanner = new Scanner(fakeInput);

        // Use the overloaded constructor
        ReportService service = new ReportService(repo, con, fakeScanner);

        // Prepare report
        Report report = new Report();
        report.setId(1);
        report.setTitle("Test Report");
        report.setSql("SELECT * FROM test WHERE region = '%region%'");
        report.setParameterName("region");
        report.setParameterPrompt("Please enter region");

        // Mock repository
        when(repo.getReportById(1)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);

        // Mock ResultSet and metadata
        ResultSetMetaData md = mock(ResultSetMetaData.class);
        when(md.getColumnCount()).thenReturn(2);
        when(md.getColumnLabel(1)).thenReturn("id");
        when(md.getColumnLabel(2)).thenReturn("Title");

        when(rs.getMetaData()).thenReturn(md);
        when(rs.next()).thenReturn(true, false); // 1 row
        when(rs.getObject(1)).thenReturn(1);             // id
        when(rs.getObject(2)).thenReturn("Test User");   // name

        // Execute query returns mocked ResultSet
        when(stmt.executeQuery("SELECT * FROM test WHERE region = 'Europe'")).thenReturn(rs);

        // Run
        service.runReport(1);

        // Verify correct query was used
        verify(stmt).executeQuery("SELECT * FROM test WHERE region = 'Europe'");
    }

    @Test
    @DisplayName("Should handle database errors")
    void testRunReportError() throws SQLException {
        // Test what happens if there's a database error
        Report report = new Report();
        report.setId(1);// = 1;
        report.setTitle("Test Report");// = ;
        report.setSql("SELECT * FROM test");// = ;
        report.setParameterName(""); //= "";
        report.setParameterPrompt("");// = "";
        
        when(repo.getReportById(1)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenThrow(new SQLException("Database error"));

        service.runReport(1);

        verify(repo).getReportById(1);
        verify(con).createStatement();
        verify(stmt).executeQuery(anyString());
    }

    @Test
    @DisplayName("Should handle empty SQL query")
    void testRunReportWithEmptySql() throws SQLException {
        // Test edge case: report with empty SQL
        Report report = new Report();
        report.setId(99);
        report.setTitle("Empty SQL Report");
        report.setSql("");
        report.setParameterName("");
        report.setParameterPrompt("");
        
        when(repo.getReportById(99)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery("")).thenReturn(rs);
        when(rs.next()).thenReturn(false); // Empty result set
        
        service.runReport(99);
        
        verify(repo).getReportById(99);
        verify(con).createStatement();
        verify(stmt).executeQuery("");
    }

    @Test
    @DisplayName("Should handle null parameter input")
    void testRunReportWithNullParameter() throws SQLException {
        // Test edge case: null parameter handling
        Report report = new Report();
        report.setId(100);
        report.setTitle("Null Parameter Test");
        report.setSql("SELECT * FROM test WHERE name = '%name%'");
        report.setParameterName("name");
        report.setParameterPrompt("Enter name");
        
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("\n".getBytes());
        Scanner fakeScanner = new Scanner(fakeInput);
        ReportService serviceWithScanner = new ReportService(repo, con, fakeScanner);
        
        when(repo.getReportById(100)).thenReturn(report);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(contains("name = ''"))).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        
        serviceWithScanner.runReport(100);
        
        verify(repo).getReportById(100);
        verify(stmt).executeQuery(anyString());
    }


}