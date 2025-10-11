package com.napier.sem.service;

import com.napier.sem.model.Report;
import com.napier.sem.repository.ReportRepository;
import com.napier.sem.util.TablePrinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class ReportService {
    private final Connection con;
    private final ReportRepository repo;

    public ReportService(Connection con, ReportRepository repo) {
        this.con = con;
        this.repo = repo;
    }

    // Run report by ID
    public void runReport(int reportId) {
        Report report = repo.getReportById(reportId);
        if (report == null) {
            System.out.println("Invalid report ID.");
            return;
        }
        runReport(report);
    }

    // Run report by Report object
    public void runReport(Report report) {
        try {
            String sql = report.sql;

            // Prompt user if report requires a parameter
            if (report.parameterName != null && !report.parameterName.isEmpty()) {
                Scanner sc = new Scanner(System.in);
                System.out.print(report.parameterPrompt + ": ");
                String value = sc.nextLine();
                sql = sql.replace("%" + report.parameterName + "%", value);
            }

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== " + report.title + " ===");
            TablePrinter.printResultSet(rs);

        } catch (Exception e) {
            System.out.println("Error running report: " + e.getMessage());
        }
    }

    // Get all reports for menu
    public List<Report> getAllReports() {
        return repo.getAllReports();
    }

    // Get a single report
    public Report getReportById(int id) {
        return repo.getReportById(id);
    }
}
