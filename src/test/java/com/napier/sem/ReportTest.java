package com.napier.sem;

import com.napier.sem.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the Report model class.
 * These tests verify the data model and utility methods of Report objects.
 */
@DisplayName("Report Model Tests")
class ReportTest {

    private Report report;

    @BeforeEach
    void setUp() {
        report = new Report();
    }

    @Test
    @DisplayName("Should create report with default constructor")
    void testDefaultConstructor() {
        assertNotNull(report);
        assertEquals(0, report.getId());
        assertNull(report.getTitle());
        assertNull(report.getSql());
        assertNull(report.getParameterName());
        assertNull(report.getParameterPrompt());
    }

    @Test
    @DisplayName("Should create report with full constructor")
    void testFullConstructor() {
        Report fullReport = new Report(1, "Test Report", "SELECT * FROM test", "region", "Enter region:");

        assertEquals(1, fullReport.getId());
        assertEquals("Test Report", fullReport.getTitle());
        assertEquals("SELECT * FROM test", fullReport.getSql());
        assertEquals("region", fullReport.getParameterName());
        assertEquals("Enter region:", fullReport.getParameterPrompt());
    }

    @Test
    @DisplayName("Should set and get all properties correctly")
    void testSettersAndGetters() {
        // Set values
        report.setId(5);
        report.setTitle("Population Report");
        report.setSql("SELECT * FROM population");
        report.setParameterName("country");
        report.setParameterPrompt("Enter country name:");

        // Verify values
        assertEquals(5, report.getId());
        assertEquals("Population Report", report.getTitle());
        assertEquals("SELECT * FROM population", report.getSql());
        assertEquals("country", report.getParameterName());
        assertEquals("Enter country name:", report.getParameterPrompt());
    }

    @Test
    @DisplayName("Should detect when parameter is required")
    void testRequiresParameterWithParameter() {
        report.setParameterName("region");
        assertTrue(report.requiresParameter());
    }

    @Test
    @DisplayName("Should detect when parameter is not required - null parameter name")
    void testRequiresParameterWithNullParameterName() {
        report.setParameterName(null);
        assertFalse(report.requiresParameter());
    }

    @Test
    @DisplayName("Should detect when parameter is not required - empty parameter name")
    void testRequiresParameterWithEmptyParameterName() {
        report.setParameterName("");
        assertFalse(report.requiresParameter());
    }

    @Test
    @DisplayName("Should detect when parameter is not required - whitespace parameter name")
    void testRequiresParameterWithWhitespaceParameterName() {
        report.setParameterName("   ");
        assertFalse(report.requiresParameter());
    }

    @Test
    @DisplayName("Should create report from ResultSet with standard column names")
    void testFromResultSet() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getInt("id")).thenReturn(10);
        when(rs.getString("title")).thenReturn("Cities Report");
        when(rs.getString("sql")).thenReturn("SELECT * FROM city");
        when(rs.getString("parameter_name")).thenReturn("continent");
        when(rs.getString("parameter_prompt")).thenReturn("Enter continent:");

        Report result = Report.fromResultSet(rs);

        assertNotNull(result);
        assertEquals(10, result.getId());
        assertEquals("Cities Report", result.getTitle());
        assertEquals("SELECT * FROM city", result.getSql());
        assertEquals("continent", result.getParameterName());
        assertEquals("Enter continent:", result.getParameterPrompt());
    }

    @Test
    @DisplayName("Should create report from ResultSet with sql_query column name")
    void testFromResultSetWithSqlQueryColumn() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getInt("id")).thenReturn(11);
        when(rs.getString("title")).thenReturn("Countries Report");

        // Simulate sql column not existing but sql_query existing
        when(rs.getString("sql")).thenThrow(new SQLException("Column not found"));
        when(rs.getString("sql_query")).thenReturn("SELECT * FROM country");

        when(rs.getString("parameter_name")).thenReturn(null);
        when(rs.getString("parameter_prompt")).thenReturn(null);

        Report result = Report.fromResultSet(rs);

        assertNotNull(result);
        assertEquals(11, result.getId());
        assertEquals("Countries Report", result.getTitle());
        assertEquals("SELECT * FROM country", result.getSql());
        assertNull(result.getParameterName());
        assertNull(result.getParameterPrompt());
    }

    @Test
    @DisplayName("Should handle missing optional columns in ResultSet")
    void testFromResultSetWithMissingOptionalColumns() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getInt("id")).thenReturn(12);
        when(rs.getString("title")).thenReturn("Basic Report");
        when(rs.getString("sql")).thenReturn("SELECT * FROM basic");

        // Simulate optional columns not existing
        when(rs.getString("parameter_name")).thenThrow(new SQLException("Column not found"));
        when(rs.getString("parameter_prompt")).thenThrow(new SQLException("Column not found"));

        Report result = Report.fromResultSet(rs);

        assertNotNull(result);
        assertEquals(12, result.getId());
        assertEquals("Basic Report", result.getTitle());
        assertEquals("SELECT * FROM basic", result.getSql());
        assertNull(result.getParameterName());
        assertNull(result.getParameterPrompt());
    }

    @Test
    @DisplayName("Should generate meaningful toString representation")
    void testToString() {
        report.setId(1);
        report.setTitle("Test Report");
        report.setSql("SELECT * FROM test");
        report.setParameterName("param");
        report.setParameterPrompt("Enter param:");

        String result = report.toString();

        // Check the actual format based on your Report.java toString implementation
        assertTrue(result.contains("Report{"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("title='Test Report'"));
        assertTrue(result.contains("sql='SELECT * FROM test"));
        assertTrue(result.contains("parameterName='param'"));
        assertTrue(result.contains("parameterPrompt='Enter param:'"));
    }

    @Test
    @DisplayName("Should truncate long SQL in toString")
    void testToStringWithLongSql() {
        String longSql = "SELECT * FROM very_long_table_name_where_we_have_many_columns_to_display_in_the_report_output";
        report.setId(1);
        report.setTitle("Long SQL Report");
        report.setSql(longSql);

        String result = report.toString();

        // Should be truncated (check for the actual behavior)
        // The toString method truncates SQL longer than 60 characters
        assertTrue(result.length() < longSql.length() + 100); // Account for other fields
        // Check if it contains the truncation indicator
        assertTrue(result.contains("...") || result.length() < longSql.length());
    }

    @Test
    @DisplayName("Should handle null SQL in toString")
    void testToStringWithNullSql() {
        report.setId(2);
        report.setTitle("Null SQL Report");
        report.setSql(null);

        String result = report.toString();

        // Check what actually appears in the toString for null SQL
        // Based on your Report.java, it should show "sql=null"
        assertTrue(result.contains("sql=null") || result.contains("sql='null'"));
    }

    @Test
    @DisplayName("Should implement equals correctly")
    void testEquals() {
        Report report1 = new Report(1, "Report A", "SELECT A", "param", "prompt");
        Report report2 = new Report(1, "Report A", "SELECT A", "param", "prompt");
        Report report3 = new Report(2, "Report B", "SELECT B", "param2", "prompt2");

        // Reflexive
        assertEquals(report1, report1);

        // Symmetric
        assertEquals(report1, report2);
        assertEquals(report2, report1);

        // Different objects
        assertNotEquals(report1, report3);
        assertNotEquals(report1, null);
        assertNotEquals(report1, "some string");
    }

    @Test
    @DisplayName("Should implement hashCode consistently with equals")
    void testHashCode() {
        Report report1 = new Report(1, "Report A", "SELECT A", "param", "prompt");
        Report report2 = new Report(1, "Report A", "SELECT A", "param", "prompt");

        assertEquals(report1.hashCode(), report2.hashCode());

        // Consistent
        int hash1 = report1.hashCode();
        int hash2 = report1.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Should generate different hashCodes for different reports")
    void testHashCodeDifferentReports() {
        Report report1 = new Report(1, "Report A", "SELECT A", "param", "prompt");
        Report report2 = new Report(2, "Report B", "SELECT B", "param2", "prompt2");

        assertNotEquals(report1.hashCode(), report2.hashCode());
    }

    @Test
    @DisplayName("Should handle equals with null fields")
    void testEqualsWithNullFields() {
        Report report1 = new Report(1, null, null, null, null);
        Report report2 = new Report(1, null, null, null, null);
        Report report3 = new Report(1, "Title", null, null, null);

        assertEquals(report1, report2);
        assertNotEquals(report1, report3);
    }
}