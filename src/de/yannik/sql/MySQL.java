package de.yannik.sql;

import org.bukkit.*;
import de.yannik.*;
import java.sql.*;

public class MySQL
{
    private static String username;
    private static String password;
    private static String database;
    private static String host;
    private static Connection con;
    
    static {
        MySQL.username = "root";
        MySQL.password = "";
        MySQL.database = "network";
        MySQL.host = "localhost";
    }
    
    public static void connect() {
        if (!isConnected()) {
            try {
                MySQL.con = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":3306/" + MySQL.database + "?autoReconnect=true", MySQL.username, MySQL.password);
                Bukkit.getConsoleSender().sendMessage("§aSuccessfully connected to MySQL-Database.");
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cCould not connect to MySQL-Database, please check your MySQL-Settings.");
            }
        }
    }
    
    public static void close() {
        if (isConnected()) {
            try {
                MySQL.con.close();
                MySQL.con = null;
                Bukkit.getConsoleSender().sendMessage("§aSuccessfully closed MySQL-Connection.");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean isConnected() {
        return MySQL.con != null;
    }
    
    public static void update(final String sql) {
        try {
            final Statement statement = MySQL.con.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch (SQLException e) {
        	
        	e.printStackTrace();
        }
    }
    
    public static ResultSet getResult(final String sql) {
        try {
            return MySQL.con.createStatement().executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
