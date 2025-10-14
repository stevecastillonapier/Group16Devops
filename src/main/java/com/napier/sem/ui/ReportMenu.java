package com.napier.sem.ui;

import com.napier.sem.model.Report;
import com.napier.sem.service.ReportService;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the interactive console menu for selecting and running reports.
 * It acts as the presentation layer, delegating business logic to {@link ReportService}.
 */
public class ReportMenu {

    //The service layer instance is used to fetch report definitions and execute reports.
    private final ReportService reportService;

    /**
     * Constructor for the ReportMenu.
     * @param reportService The service instance for report operations.
     */
    public ReportMenu(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Displays the main menu to the user, lists available reports, and handles user input
     * to execute the selected report or exit.
     */
    public void displayMainMenu() {

        if (System.getenv("CI") != null) {
            System.out.println("Running in CI environment — skipping interactive menu.");

            return;
        }

        // Initialize the Scanner once for the duration of the menu loop
        Scanner sc = new Scanner(System.in);
        String choice;

        // Start the continuous menu loop
        while (true) {

            System.out.println("\n=====================================");
            System.out.println("        === Main Menu ===           ");
            System.out.println("\n=====================================");
            System.out.println("Welcome! Please select a report:");

            // Fetch reports from the database
            List<Report> reports = reportService.getAllReports();

            // Display the list of available reports
            if (reports != null && !reports.isEmpty()) {
                for (Report r : reports) {

                    // Use printf for clean, formatted output
                    System.out.printf("%d - %s%n", r.getId(), r.getTitle());
                }
            } else {

                // Inform the user if the repository returned no reports
                System.out.println("No reports found in the database.");
                break; // Exit the loop if no reports are available
            }

             System.out.println("x - Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();


            // Process exit command
            if (choice.equalsIgnoreCase("x")) {
                System.out.println("Exiting...");
                break; // Exit the loop
            }

            // Process report selection (numerical input)
            try {
                int reportId = Integer.parseInt(choice);

                Report selectedReport = reportService.getReportById(reportId);

                if (selectedReport != null) {
                    System.out.println("Running report: " + selectedReport.getTitle());

                    // Delegate execution to the ReportService
                    // Call method to execute report SQL and display results
                    reportService.runReport(selectedReport);
                } else {

                    // Input was a valid number, but no matching report was found
                    System.out.println("Invalid report ID. No report found with ID:");
                }
            } catch (NumberFormatException e) {

                // Input was not 'x' and not a valid integer
                System.out.println("Invalid input. Please enter a report number or 'x' to exit.");
            }
        }//end of while loop
        sc.close();
    }
}

