package com.napier.sem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Model representing a report definition stored in the database.
 *
 * Typical database table columns:
 *  - id (INT)
 *  - title (VARCHAR)
 *  - sql_query (TEXT)
 *  - parameter_name (VARCHAR)         -- optional
 *  - parameter_prompt (VARCHAR)       -- optional
 */
public class Report {
    public int id;
    public String title;
    public String sql;               // the SQL template (may contain placeholders like %param%)
    public String parameterName;     // optional (e.g. "region")
    public String parameterPrompt;   // optional (e.g. "Please enter region:")

    public Report() {
    }

    public Report(int id, String title, String sql, String parameterName, String parameterPrompt) {
        this.id = id;
        this.title = title;
        this.sql = sql;
        this.parameterName = parameterName;
        this.parameterPrompt = parameterPrompt;
    }

    /**
     * Create a Report instance from a ResultSet row.
     * Expects the ResultSet to contain columns named at least:
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
        // try a few common SQL column names for the SQL text
        r.id = rs.getInt("id");
        r.title = rs.getString("title");

        String sqlText = null;
        try { sqlText = rs.getString("sql"); } catch (SQLException ignored) {}
        if (sqlText == null) {
            try { sqlText = rs.getString("sql_query"); } catch (SQLException ignored) {}
        }
        r.sql = sqlText;

        // optional parameters
        try { r.parameterName = rs.getString("parameter_name"); } catch (SQLException ignored) { r.parameterName = null; }
        try { r.parameterPrompt = rs.getString("parameter_prompt"); } catch (SQLException ignored) { r.parameterPrompt = null; }
        return r;
    }

    /** Returns true if this report expects a parameter (non-empty parameterName). */
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

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sql, parameterName, parameterPrompt);
    }
}
