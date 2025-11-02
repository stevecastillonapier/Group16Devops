package com.napier.sem;

import com.napier.sem.model.Report;
import com.napier.sem.service.ReportService;
import com.napier.sem.ui.ReportMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the ReportMenu class.
 */
@DisplayName("ReportMenu Tests")
class ReportMenuTest {

    @Mock
    private ReportService reportService;

    private ReportMenu reportMenu;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in); // Reset System.in
    }

    // Helper method to create a testable ReportMenu
    private ReportMenu createReportMenuWithCiSetting(boolean simulateCi) {
        return new ReportMenu(reportService) {
            @Override
            protected boolean isCiEnvironment() {
                return simulateCi;
            }
        };
    }

    @Test
    @DisplayName("Should skip menu in CI environment")
    void testSkipMenuInCI() {
        reportMenu = createReportMenuWithCiSetting(true);

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("Running in CI environment"),
                "Expected CI message but got: " + output);

        verifyNoInteractions(reportService);
    }

    @Test
    @DisplayName("Should display menu with available reports when not in CI")
    void testDisplayMainMenuWithReports() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        mockReports.add(new Report(1, "Country Report", "SELECT * FROM country", null, null));
        mockReports.add(new Report(2, "City Report", "SELECT * FROM city", "region", "Enter region:"));

        when(reportService.getAllReports()).thenReturn(mockReports);

        String input = "x\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("Report") || output.contains("Menu") || output.contains("Welcome"),
                "Expected menu content but got: " + output);

        verify(reportService).getAllReports();
    }

    @Test
    @DisplayName("Should handle empty reports list when not in CI")
    void testDisplayMainMenuWithNoReports() {
        reportMenu = createReportMenuWithCiSetting(false);

        when(reportService.getAllReports()).thenReturn(new ArrayList<>());

        String input = "x\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("No reports"), "Expected no reports message but got: " + output);
        verify(reportService).getAllReports();
    }

    @Test
    @DisplayName("Should handle null reports list when not in CI")
    void testDisplayMainMenuWithNullReports() {
        reportMenu = createReportMenuWithCiSetting(false);

        when(reportService.getAllReports()).thenReturn(null);

        String input = "x\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("No reports"), "Expected no reports message but got: " + output);
        verify(reportService).getAllReports();
    }

    @Test
    @DisplayName("Should run selected report successfully when not in CI")
    void testRunSelectedReport() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        Report mockReport = new Report(1, "Test Report", "SELECT * FROM test", null, null);
        mockReports.add(mockReport);

        when(reportService.getAllReports()).thenReturn(mockReports);
        when(reportService.getReportById(1)).thenReturn(mockReport);

        String input = "1\n\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        verify(reportService).getReportById(1);
        verify(reportService).runReport(mockReport);

        String output = outputStream.toString();
        assertTrue(output.contains("Running report") || output.contains("Test Report"),
                "Expected report execution message but got: " + output);
    }

    @Test
    @DisplayName("Should handle invalid report ID selection when not in CI")
    void testInvalidReportIdSelection() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        mockReports.add(new Report(1, "Test Report", "SELECT * FROM test", null, null));

        when(reportService.getAllReports()).thenReturn(mockReports);
        when(reportService.getReportById(99)).thenReturn(null);

        String input = "99\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid"), "Expected invalid ID message but got: " + output);

        verify(reportService).getReportById(99);
        verify(reportService, never()).runReport(any());
    }

    @Test
    @DisplayName("Should handle non-numeric input when not in CI")
    void testNonNumericInput() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        mockReports.add(new Report(1, "Test Report", "SELECT * FROM test", null, null));

        when(reportService.getAllReports()).thenReturn(mockReports);

        String input = "abc\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid"), "Expected invalid input message but got: " + output);

        verify(reportService, never()).getReportById(anyInt());
        verify(reportService, never()).runReport(any());
    }

    @Test
    @DisplayName("Should handle case-insensitive exit command when not in CI")
    void testCaseInsensitiveExit() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        mockReports.add(new Report(1, "Test Report", "SELECT * FROM test", null, null));

        when(reportService.getAllReports()).thenReturn(mockReports);

        // Test both uppercase and lowercase exit
        for (String exitCommand : new String[]{"X", "x"}) {
            outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Create fresh instance for each test
            reportMenu = createReportMenuWithCiSetting(false);
            when(reportService.getAllReports()).thenReturn(mockReports);

            String input = exitCommand + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            reportMenu.displayMainMenu();

            String output = outputStream.toString();
            assertTrue(output.contains("Exit") || output.contains("exiting"),
                    "Expected exit message but got: " + output);
        }
    }

    @Test
    @DisplayName("Should handle multiple menu iterations when not in CI")
    void testMultipleMenuIterations() {
        reportMenu = createReportMenuWithCiSetting(false);

        List<Report> mockReports = new ArrayList<>();
        Report report1 = new Report(1, "Report 1", "SELECT 1", null, null);
        Report report2 = new Report(2, "Report 2", "SELECT 2", null, null);
        mockReports.add(report1);
        mockReports.add(report2);

        when(reportService.getAllReports()).thenReturn(mockReports);
        when(reportService.getReportById(1)).thenReturn(report1);
        when(reportService.getReportById(2)).thenReturn(report2);

        String input = "1\n\n2\n\nx\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        reportMenu.displayMainMenu();

        verify(reportService).runReport(report1);
        verify(reportService).runReport(report2);
        verify(reportService, atLeast(2)).getAllReports();
    }
}