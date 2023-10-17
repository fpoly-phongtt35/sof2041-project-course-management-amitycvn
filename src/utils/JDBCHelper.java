/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class JDBCHelper {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "Polypro";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "a1234b5c";

    /**
     * Get connection to MSSQL Server
     *
     * @return Connection
     */
    public static Connection getConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) {
        try {
            Connection c = getConnection();
            PreparedStatement ps = null;
            if (sql.trim().startsWith("{")) {
                ps = c.prepareCall(sql);
            }
            ps = c.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                return ps.executeQuery();
            } finally {
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
