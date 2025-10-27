package com.napier.sem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Model representing a report definition stored in the database.
 * This class serves as a Data Transfer Object (DTO) for report metadata.
 */
public class Report {

    //Report unique identifier (Primary Key).
    private int id;

    //User-friendly title for the report.
    private String title;

    //The SQL template to be executed (may contain placeholders like %param%).
    private String sql;

    //The name of the parameter expected by the SQL query (e.g. "region"). Optional.
    private String parameterName;

    //The prompt text displayed to the user to request the parameter value (e.g. "Please enter region:"). Optional.
    private String parameterPrompt;   // optional (e.g. "Please enter region:")

    /**
     * Default no-argument constructor.
     */
    public Report() {
    }

    /**
     * Full constructor for initializing all fields.
     */
    public Report(int id, String title, String sql, String parameterName, String parameterPrompt) {
        this.id = id;
        this.title = title;
        this.sql = sql;
        this.parameterName = parameterName;
        this.parameterPrompt = parameterPrompt;
    }

    /**
     * Create a Report instance from a ResultSet row.
     * ResultSet contain columns named:
     *  - id
     *  - title
     *  - sql (or sql_query)
     *  - parameter_name (optional)
     *  - parameter_prompt (optional)
     *
     * @param rs ResultSet positioned at a valid row
     * @return a populated Report instance
     * @throws SQLException if an SQL error occurs reading the ResultSet
     */
    public static Report fromResultSet(ResultSet rs) throws SQLException {
        Report r = new Report();

        // Non-optional columns (will throw SQLException if missing)
        r.id = rs.getInt("id");
        r.title = rs.getString("title");

        // Logic to handle multiple possible column names for the SQL text
        String sqlText = null;
        try { sqlText = rs.getString("sql"); } catch (SQLException ignored) {}
        if (sqlText == null) {
            try { sqlText = rs.getString("sql_query"); } catch (SQLException ignored) {}
        }

        // NOTE: If both fail, r.sql will be null. Consider throwing an exception if SQL is mandatory.
        r.sql = sqlText;

        // Optional parameters: Using try-catch to gracefully handle missing columns
        try { r.parameterName = rs.getString("parameter_name"); } catch (SQLException ignored) { r.parameterName = null; }
        try { r.parameterPrompt = rs.getString("parameter_prompt"); } catch (SQLException ignored) { r.parameterPrompt = null; }

        return r;
    }

    /**
     * Checks if this report requires a parameter to be executed.
     * @return true if parameterName is not null and not empty after trimming.
     */
    public boolean requiresParameter() {
        return parameterName != null && !parameterName.trim().isEmpty();
    }

    // --- getters & setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSql() { return sql; }
    public void setSql(String sql) { this.sql = sql; }

    public String getParameterName() { return parameterName; }
    public void setParameterName(String parameterName) { this.parameterName = parameterName; }

    public String getParameterPrompt() { return parameterPrompt; }
    public void setParameterPrompt(String parameterPrompt) { this.parameterPrompt = parameterPrompt; }

    /**
     * Provides a string representation of the Report object for logging and debugging.
     * Note: The SQL text is truncated for readability.
     * @return A formatted string showing all field values.
     */
    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sql='" + (sql != null ? (sql.length() > 60 ? sql.substring(0, 60) + "..." : sql) : null) + '\'' +
                ", parameterName='" + parameterName + '\'' +
                ", parameterPrompt='" + parameterPrompt + '\'' +
                '}';
    }

    /**
     * Equals contract. Checks equality based on all fields.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return id == report.id &&
                Objects.equals(title, report.title) &&
                Objects.equals(sql, report.sql) &&
                Objects.equals(parameterName, report.parameterName) &&
                Objects.equals(parameterPrompt, report.parameterPrompt);
    }

    /**
     * Standard implementation of the hashCode contract, consistent with equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, sql, parameterName, parameterPrompt);
    }
}
