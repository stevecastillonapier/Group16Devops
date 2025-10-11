package com.napier.sem;

import com.napier.sem.repository.ReportRepository;
import com.napier.sem.service.ReportService;
import com.napier.sem.ui.ReportMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args)
    {
        System.out.println("=====================================");
        System.out.println("  Welcome to the World Data Explorer ");
        System.out.println("=====================================");
        System.out.println();

        // Create new Application
          App a = new App();

        // Connect to database
         a.connect();


            ReportRepository reportRepo = new ReportRepository(a.con);
            //testing
           // System.out.println(reportRepo.getReportById(1).toString());


            ReportService reportService = new ReportService(a.con, reportRepo);
            ReportMenu menu = new ReportMenu(reportService);

            menu.displayMainMenu();


        //disconnect from database
         a.disconnect();

        System.out.println("Goodbye!");



        // Connect to database
       // a.connect();

        //disconnect from database
       // a.disconnect();


    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                //con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "CRGroup16");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
