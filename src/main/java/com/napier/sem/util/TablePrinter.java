package com.napier.sem.util;

import java.sql.*;

    /**
     * TablePrinter class responsible for formatting and printing the contents of a
     * {@link ResultSet} object as a structured, human-readable table to the console.
     */
    public class TablePrinter {

        /**
         * Prints the data contained in a {@link ResultSet} to standard output.
         * The table header uses column names, and the rows display string representations
         * of the data.
         * @param rs The ResultSet containing the data to be printed. The cursor must be
         * positioned before the first row.
         * @throws SQLException If an error occurs while accessing the ResultSet metadata or data.
         */
        public static void printResultSet(ResultSet rs) throws SQLException {

            // Retrieve metadata to get column count and names
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            // ------------------
            // Print Header
            // ------------------
            for (int i = 1; i <= columns; i++) {
                System.out.printf("| %-20s", md.getColumnName(i));
            }
            System.out.println("|");
            System.out.println("------------------------------------------------------------");

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.printf("| %-20s", rs.getString(i));
                }
                System.out.println("|");
            }
            System.out.println("------------------------------------------------------------");
        }
    }
