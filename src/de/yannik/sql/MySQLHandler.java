package de.yannik.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import de.yannik.handler.BedRushManager;
import de.yannik.handler.FFAHandler;
import de.yannik.handler.FFAManager;
import de.yannik.handler.LanguageHandler;



public class MySQLHandler {

	
    public static boolean existPlayer(final String uuid) {
        final ResultSet rs = MySQL.getResult("SELECT UUID FROM training_data WHERE UUID= '" + uuid + "'");
        String s = null;
        try {
            while (rs.next()) {
                s = rs.getString("UUID");
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return s != null;
    }
    public static int getValue(final String uuid, final String value) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM players_data WHERE UUID= '" + uuid + "'");
        int s = 0;
        try {
            while (rs.next()) {
                s = rs.getInt(value);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static int getValueFromLobbyData(final String uuid, final String value) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM players_data WHERE UUID= '" + uuid + "'");
        int s = 0;
        try {
            while (rs.next()) {
                s = rs.getInt(value);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public static void loadFFAMaps(String name) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM training_spawnpoints WHERE ffaname = '" + name + "';");
        try {
            while (rs.next()) {

             
               		
            	FFAHandler arena = FFAManager.getArena(name);
               		
               		
               	System.out.println("" +rs.getString("ffaname") + " " +  rs.getFloat("x") + " " + rs.getFloat("y") + " " + rs.getFloat("z") + " " +  rs.getFloat("heading"));
               		arena.setFFASpawn(rs.getString("ffaname"), rs.getFloat("x"), rs.getFloat("y"), rs.getFloat("z"), rs.getFloat("heading"));
               		
               		
                 
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadFFAArenas() {
        final ResultSet rs = MySQL.getResult("SELECT * FROM traing_ffa");
        try {
            while (rs.next()) {
            	Bukkit.getServer().createWorld(new WorldCreator(rs.getString("ffamap")));

            	FFAManager.createNewArena(rs.getString("ffamode"), Bukkit.getWorld("ffamap"));
            
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }   
    
    public static void loadBedRushMaps() {
        final ResultSet rs = MySQL.getResult("SELECT * FROM training_bedrush");
        try {
            while (rs.next()) {
            	World w = Bukkit.getServer().createWorld(new WorldCreator(rs.getString("bedrushname")));

            	Bukkit.getConsoleSender().sendMessage("" + w.getName());
            	BedRushManager.createNewArena(rs.getString("bedrushname"), w);
            
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static String getString(final String uuid, final String value) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM players_data WHERE UUID= '" + uuid + "'");
        String s = null;
        try {
            while (rs.next()) {
                s = rs.getString(value);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void setStringValue(final String uuid, final String type, final String value) {
        
    	String string = "UPDATE players_data SET " + type + " = '" + value + "' WHERE UUID = '"+ uuid +"'";
    	MySQL.update(string);
    }
    public static void setValue(final String uuid, final String type, final int value) {
        
    	String string = "UPDATE training_data SET " + type + " = " + value + " WHERE UUID = '"+ uuid +"'";
    	MySQL.update(string);
    }
}
