package com.napier.sem.repository;

import com.napier.sem.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class responsible for data access operations related to {@link Report} entities.
 * It serves as a data layer abstraction, isolating the application from JDBC details.
 */
public class ReportRepository {

    //The database connection used for all operations.
    private final Connection con;

    /**
     * Constructor for the ReportRepository.
     * @param con The active JDBC connection to the database.
     */
    public ReportRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a single Report definition from the database by its unique ID.
     * @param id The ID of the report to retrieve.
     * @return The populated Report object, or {@code null} if no report is found or an error occurs.
     */
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

    /**
     * Retrieves all available Report definitions from the database.
     * @return A {@link List} of {@link Report} objects, or an empty list if none are found or an error occurs.
     */
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
            System.out.println("Failed to load all reports: " + e.getMessage());
        }
        return reports;
    }

    /**
     * Helper method to map a single row of a {@link ResultSet} to a {@link Report} object.
     * @param rs The ResultSet positioned at a valid row.
     * @return A new, populated Report instance.
     * @throws SQLException If an error occurs reading the column data from the ResultSet.
     */
    private Report mapRowToReport(ResultSet rs) throws SQLException {

        // Uses the no-arg constructor
        Report report = new Report();

        // Data extraction using database column names
        //report.id = rs.getInt("id");
        report.setId(rs.getInt("id"));
        //report.title = rs.getString("title");
        report.setTitle(rs.getString("title"));
        //report.sql = rs.getString("sql_query");
        report.setSql(rs.getString("sql_query"));
        //report.parameterName = rs.getString("parameter_name");
        report.setParameterName(rs.getString("parameter_name"));
        report.setParameterPrompt(rs.getString("parameter_prompt"));
        //report.parameterPrompt = rs.getString("parameter_prompt");

        return report;
    }
}
