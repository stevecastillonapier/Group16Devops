package com.napier.sem.repository;

import com.napier.sem.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {
    private final Connection con;

    public ReportRepository(Connection con) {
        this.con = con;
    }

    // Get a report by ID
    public Report getReportById(int id) {
        try {
            String sql = "SELECT * FROM reports WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRowToReport(rs);
            }
        } catch (Exception e) {
            System.out.println("Failed to load report: " + e.getMessage());
        }
        return null;
    }

    // Get all reports
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reports ORDER BY id ASC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                reports.add(mapRowToReport(rs));
            }
        } catch (Exception e) {
            System.out.println("Failed to load reports: " + e.getMessage());
        }
        return reports;
    }

    // Helper method to map ResultSet row to Report object
    private Report mapRowToReport(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.id = rs.getInt("id");
        report.title = rs.getString("title");
        report.sql = rs.getString("sql_query");
        report.parameterName = rs.getString("parameter_name");
        report.parameterPrompt = rs.getString("parameter_prompt");
        return report;
    }
}
