package de.yannik.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import de.yannik.sql.MySQL;
import de.yannik.sql.MySQLHandler;
import de.yannik.utils.ItemBuilder;

public class FFAManager {
private static ConcurrentHashMap<String, FFAHandler> arenas;
    
    static {
    	FFAManager.arenas = new ConcurrentHashMap<String, FFAHandler>();
    }
    
    public static FFAHandler getArena(final String name) {
        if (FFAManager.arenas.containsKey(name)) {
            return FFAManager.arenas.get(name);
        }
        return null;
    }
    public static FFAHandler isInFFA(final Player p) {
   	    	for(Entry<String, FFAHandler> entry : FFAManager.getallArenas().entrySet()) {
    		
    		FFAHandler arena =  entry.getValue();
    		
    		if (arena.FFAPlayers.contains(p)) {
    			return arena;
    		}
    		

    	}
    	    	
        return null;
    }

    
    public static void createNewArena(final String name, final World w) {
    	FFAHandler arenaData;
        Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9);
        arenaData = new FFAHandler(name, new ArrayList<Player>(), w, inv);
        FFAManager.arenas.put(name, arenaData);
        
        MySQLHandler.loadFFAMaps(name);
    }
    
    
    public static ConcurrentHashMap<String, FFAHandler> getallArenas() {
    	return arenas;
    }

    
    public static int getAllPlayerCount() {
    	
    	int i = 0;
    	for(Entry<String, FFAHandler> entry : arenas.entrySet()) {
    		
    		i += entry.getValue().getPlayers();

    	}
    	    	
    	return i;
    }
}
