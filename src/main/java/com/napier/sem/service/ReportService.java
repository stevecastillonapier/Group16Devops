package com.napier.sem.service;

import com.napier.sem.model.Report;
import com.napier.sem.repository.ReportRepository;
import com.napier.sem.util.TablePrinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

/**
 * Service layer responsible for business logic related of running the reports.
 * It coordinates interaction between the Repository (data retrieval) and the database (SQL execution).
 */
public class ReportService {

    //The active JDBC connection, used here for executing the dynamic SQL queries.
    private final Connection con;

    //The active JDBC connection, used here for executing the dynamic SQL queries.
    private final ReportRepository repo;

    private final Scanner input;


    /**
     * Constructor for the ReportService.
     * @param con The active database connection.
     * @param repo The repository for accessing report definitions.
     */
    public ReportService(Connection con, ReportRepository repo) {
        this.con = con;
        this.repo = repo;
        this.input = new Scanner(System.in); //ensures initialization
    }

    public ReportService(ReportRepository repo, Connection con, Scanner input) {
        this.repo = repo;
        this.con = con;
        this.input = input;
    }

    /**
     * Finds a report by its ID and runs it. Serves as the primary public entry point.
     * @param reportId The ID of the report to run.
     */
    public void runReport(int reportId) {
        Report report = repo.getReportById(reportId);
        if (report == null) {
            System.out.println("Invalid report ID. Report not found in repository.");
            return;
        }

        // Delegate to the overloaded method
        runReport(report);
    }

    /**
     * Executes the SQL query defined in the provided {@link Report} object.
     * Handles parameter prompting/substitution and query execution.
     * @param report The Report object containing the SQL and parameter metadata.
     */
    public void runReport(Report report) {
        try {
            String sql = report.getSql();

            // Use the injected Scanner (either System.in in production or a test scanner)
            Scanner sc = input;

            // Handle continent parameter
            if (sql.contains("%continent%")) {
                System.out.print(report.getParameterPrompt() + ": ");
                String continent = sc.nextLine();
                sql = sql.replace("%continent%", continent);
            }

            // Handle N parameter
            if (sql.contains("%n%")) {
                System.out.print("Please enter the number of results to show: ");
                String n = sc.nextLine();
                sql = sql.replace("%n%", n);
            }

            // Handle other single parameters
            if (report.getParameterName() != null && !report.getParameterName().isEmpty() &&
                    !report.getParameterName().equals("continent") &&
                    !report.getParameterName().equals("n")) {
                System.out.print(report.getParameterPrompt() + ": ");
                String value = sc.nextLine();
                sql = sql.replace("%" + report.getParameterName() + "%", value);
            }

            // Execute the SQL
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Print results using TablePrinter
            System.out.println("\n=== " + report.getTitle() + " ===");
            TablePrinter.printResultSet(rs);

        } catch (Exception e) {
            System.out.println("Error running report: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // Report Metadata Retrieval
    // ------------------------------------------------------------------

    /**
     * Retrieves all available report definitions from the repository.
     * @return A list of all available reports.
     */
    public List<Report> getAllReports() {
        return repo.getAllReports();
    }

    /**
     * Retrieves a single report definition by ID from the repository.
     * @param id The ID of the report to retrieve.
     * @return The Report object, or null if not found.
     */
    public Report getReportById(int id) {
        return repo.getReportById(id);
    }
}
