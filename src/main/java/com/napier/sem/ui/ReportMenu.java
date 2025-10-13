package com.napier.sem.ui;

import com.napier.sem.model.Report;
import com.napier.sem.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportMenu {

    private final ReportService reportService;

    public ReportMenu(ReportService reportService) {
        this.reportService = reportService;
    }

    public void displayMainMenu() {

        if (System.getenv("CI") != null) {
            System.out.println("Running in CI environment — skipping interactive menu.");
            return;
        }

        System.out.println("=== Main Menu ===");
        System.out.println("Welcome! Please select a report:");

        // Fetch reports from the database
        List<Report> reports = reportService.getAllReports();

        if (reports != null && !reports.isEmpty()) {
            for (Report r : reports) {
                System.out.printf("%d - %s%n", r.getId(), r.getTitle());
            }
        } else {
            System.out.println("No reports found in the database.");
        }

        System.out.println("x - Exit");

        // Handle user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("x")) {
            System.out.println("Exiting...");
            return;
        }

        try {
            int reportId = Integer.parseInt(choice);
            Report selectedReport = reportService.getReportById(reportId);
            if (selectedReport != null) {
                System.out.println("Running report: " + selectedReport.getTitle());
                // Call method to execute report SQL and display results
                reportService.runReport(selectedReport);
            } else {
                System.out.println("Invalid report ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
