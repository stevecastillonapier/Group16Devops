package com.napier.sem.util;

import java.sql.*;

    public class TablePrinter {
        public static void printResultSet(ResultSet rs) throws SQLException {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            // Print header
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
