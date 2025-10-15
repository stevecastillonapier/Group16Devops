package com.napier.sem.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class responsible for formatting and printing the contents of a
 * {@link ResultSet} as a structured, human-readable table to the console.
 * This version dynamically adjusts column widths based on data content.
 */
public class TablePrinter {

    /**
     * Prints the data contained in a {@link ResultSet} to standard output,
     * with column widths adjusted to fit the widest content.
     *
     * @param rs The ResultSet containing the data to be printed.
     * @throws SQLException If an error occurs while accessing the ResultSet metadata or data.
     */
    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();

        // 1. Prepare data structures to store data and calculated widths
        int[] columnWidths = new int[columnCount];
        List<String[]> data = new ArrayList<>();
        String[] columnNames = new String[columnCount];

        // 2. Initial pass: Get column names and set initial widths (based on headers)
        for (int x = 0; x < columnCount; x++) {
            // Use getColumnLabel for user-friendly column names
            columnNames[x] = md.getColumnLabel(x + 1);
            // Set initial column width to the header length
            columnWidths[x] = columnNames[x].length();
        }

        // 3. Second pass: Retrieve all data and calculate max width for each column
        while (rs.next()) {
            String[] rowData = new String[columnCount];
            for (int x = 0; x < columnCount; x++) {
                // Get data as string. Handle null values by converting them to an empty string.
                // NOTE: Use rs.getObject(x + 1) to retrieve the value, then toString(), which handles null.
                Object obj = rs.getObject(x + 1);
                rowData[x] = (obj == null) ? "" : obj.toString();

                // Check if the column width needs to be adjusted
                if (rowData[x].length() > columnWidths[x]) {
                    columnWidths[x] = rowData[x].length();
                }
            }
            data.add(rowData);
        }

        // --- All data retrieved and widths calculated at this point ---

        // 4. Construct the row separator (e.g., +---+---+---+)
        StringBuilder rowSeparator = new StringBuilder("+");
        // Add a small padding (e.g., 2 spaces) to the width for cleaner output
        final int PADDING = 2;
        for (int width : columnWidths) {
            // Append '-' repeated (width + PADDING) times
            rowSeparator.append("-".repeat(width + PADDING)).append("+");
        }
        String separator = rowSeparator.toString();

        // Check if any data was returned
        if (data.isEmpty() && columnCount > 0) {
            // If only headers were returned, print just the headers/separator
            System.out.println("\nNo results found.");
            return;
        }

        // 5. Print the table structure

        // Print header separator
        System.out.println(separator);

        // Print the headings
        System.out.print("|");
        for (int x = 0; x < columnCount; x++) {
            // Use the calculated width for formatting
            System.out.printf(" %-" + (columnWidths[x]) + "s |", columnNames[x]);
        }
        System.out.println();

        // Print separator after the heading row
        System.out.println(separator);

        // Print rows
        for (String[] row : data) {
            System.out.print("|");
            for (int x = 0; x < columnCount; x++) {
                String cellData = row[x];
                // Use the calculated width for formatting
                System.out.printf(" %-" + (columnWidths[x]) + "s |", cellData);
            }
            System.out.println();
        }

        // Print final separator
        System.out.println(separator);
    }
}