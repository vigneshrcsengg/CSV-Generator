package com.report.csv.test;

import com.report.csv.gen.CSVException;
import com.report.csv.gen.CSVGenerator;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    private static CSVGenerator csv = null;

    /**
     * Connection Creation to SQL Server
     */
    public static Connection conn = null;

    /**
     * Statement of SQL
     */
    public static Statement stmt = null;

    /**
     * Result Set.
     */
    private static ResultSet rs = null;

    /**
     * Query
     */
    private static String query = null;

    public static final String filePath = "C:\\Users\\Prasanth R\\Desktop\\DirectoryCreated\\cellDatabase\\";
    public static final String fileName = "REPORT_FROM_DB";

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

    public static void main(String... args) throws IOException, SQLException, CSVException
    {
        CSVTestDB csvDB = new CSVTestDB();
        List<RejectRateForm> list = null;
        csv = new CSVGenerator(fileName, filePath, false);

        RejectRateForm form = null;

        try
        {
            if (csvDB.connectDatabase())
            {
                stmt = conn.createStatement();
                String SQL = "SELECT * FROM CDM";
                rs = stmt.executeQuery(SQL);

                list = new ArrayList<RejectRateForm>();

                while (rs.next())
                {
                    form = new RejectRateForm();

                    form.settermid(rs.getString("termid"));
                    form.settrxdate(rs.getString("trxdate"));
                    form.settrxtime(rs.getString("trxtime"));
                    form.setencashed(rs.getString("encashed"));
                    form.setrefunded(rs.getString("refunded"));
                    form.setescrow(rs.getInt("escrow"));
                    form.setrejected(rs.getInt("rejected"));
                    form.setrejectrate(rs.getFloat("rejectrate"));
                    form.setacceptedNotes(rs.getString("acceptedNotes"));
                    form.setrejectedNotes(rs.getString("rejectedNotes"));

                    list.add(form);
                }

                String[] rejectRate =
                {
                    "termid", "trxdate", "trxtime", "encashed", "refunuded", "escrow", "rejected", "rejectrate", "acceptedNotes", "rejectedNotes"
                };

                csv.setHeader(rejectRate);

                for (RejectRateForm _forms : list)
                {
                    csv.set(0, _forms.gettermid());
                    csv.set(1, _forms.gettrxdate());
                    csv.set(2, _forms.gettrxtime());
                    csv.set(3, _forms.getencashed());
                    csv.set(4, _forms.getrefunded());
                    csv.set(5, _forms.getescrow());
                    csv.set(6, _forms.getrejected());
                    csv.set(7, _forms.getrejectrate());
                    csv.set(8, _forms.getacceptedNotes());
                    csv.set(9, _forms.getrejectedNotes());
                    csv.next();
                }

                csv.writeData();
            }

        }

        finally
        {
            csvDB.disconnectDatabase();
            csv.close();
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
