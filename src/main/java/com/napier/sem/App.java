package com.napier.sem;

import com.napier.sem.repository.ReportRepository;
import com.napier.sem.service.ReportService;
import com.napier.sem.ui.ReportMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The main entry point for the World Data Explorer application.
 * This class handles the initial setup, including database connection,
 * and manages the application lifecycle and flow (interactive vs. automated reporting).
 */

public class App {

    /**
     * Connection to the MySQL database.
     * It is set during the connect() method call.
     */
    private Connection con = null;

    /**
     * Main method to start the application.
     */
    public static void main(String[] args)
    {
        //welcome banner
        System.out.println("=====================================");
        System.out.println("  Welcome to the World Data Explorer ");
        System.out.println("=====================================");
        System.out.println();

        // Create new Application
          App a = new App();

        // Connect to the database. This connection object will be passed to other components.
        a.connect();

        // Initialize core application components, injecting the Connection dependency.
        ReportRepository reportRepo = new ReportRepository(a.con);
        ReportService reportService = new ReportService(a.con, reportRepo);
        ReportMenu menu = new ReportMenu(reportService);

        // Determine execution mode (automated vs. interactive)
        if (args.length > 0) {
            int reportId = Integer.parseInt(args[0]);
            reportService.runReport(reportId); // automated, non-interactive
        } else {
            menu.displayMainMenu(); // interactive mode for local runs
        }

        // Disconnect from database at the end of execution
        a.disconnect();

        System.out.println("!!!!!Have a great day and thank you for using World Data Explorer!!!!!");

    }//end of main



    /**
     * Connect to the MySQL database.
     * This method attempts multiple retries to connect.
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

        int retries = 10; // Number of connection attempts
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "CRGroup16");
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
     * Disconnect from the MySQL database by closing the connection.
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
