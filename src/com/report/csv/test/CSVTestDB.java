package com.report.csv.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author VIGNESH R
 */
public class CSVTestDB
{
    public static String SQLInstance;
    public static String DBHost;
    public static String DBLogin;
    public static String DBPassword;
    public static String DBName;

    /** Connection Creation to SQL Server */
    public static Connection conn = null;

    /** Statement of SQL */
    public static Statement stmt = null;
    
    /** Result Set.*/
    private static ResultSet rs = null;

    /** Query */
    private static String query = null;

    CSVTestDB() throws IOException
    {
        Properties prop = new Properties();
        prop.load(new FileInputStream(".//dbSetup//setup.ini"));
        
        this.SQLInstance = prop.getProperty("SQLInstance");
        this.DBHost = prop.getProperty("DBHost");
        this.DBLogin = prop.getProperty("DBLogin");
        this.DBPassword = prop.getProperty("DBPassword");
        this.DBName = prop.getProperty("DBName");
    }

    public static void main(String... args) throws IOException, SQLException
    {
        CSVTestDB csvDB = new CSVTestDB();

        try
        {
            if (csvDB.connectDatabase())
            {
                stmt = conn.createStatement();
                String SQL = "SELECT * FROM CDM";
                rs = stmt.executeQuery(SQL);

                while (rs.next())
                {
                    
                }
            }
        }
        finally
        {
            csvDB.disconnectDatabase();
        }
    }

    /**
     * Using SQL JDBC driver to load the SQL Database by give below Parameters.
     * <p>
     * @return Database Connection Status
     * @throws java.io.IOException
     */
    public static boolean connectDatabase() throws IOException
    {
        boolean sqlCheck = false;

        try
        {

            String dbURL = "jdbc:sqlserver://" + DBHost + "\\" + SQLInstance + ";database=" + DBName;
            String user = DBLogin;
            String pass = DBPassword;
            conn = DriverManager.getConnection(dbURL, user, pass);

            sqlCheck = conn != null;
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException in Database Connection: " + ex.getMessage());
        }
        finally
        {

        }
        return sqlCheck;
    }

    /**
     * Disconnect the Database Connection
     * <p>
     * @throws java.io.IOException
     */
    public static void disconnectDatabase() throws IOException
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException in Database Close Connection: " + ex.getMessage());
        }
    }

}
