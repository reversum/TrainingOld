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

public class BedRushManager {
private static ConcurrentHashMap<String, BedRushHandler> arenas;
    
    static {
    	BedRushManager.arenas = new ConcurrentHashMap<String, BedRushHandler>();
    }
    
    public static BedRushHandler getArena(final String name) {
        if (BedRushManager.arenas.containsKey(name)) {
            return BedRushManager.arenas.get(name);
        }
        return null;
    }

    public static BedRushHandler getPlayerArena(final Player p) {
    	for(Entry<String, BedRushHandler> entry : arenas.entrySet()) {
    		
    		if (entry.getValue().getAllPlayers().contains(p)) {
    			return entry.getValue();
    		}

    	}
        return null;
    }


    public static BedRushHandler getFreeArena() {
    	for(Entry<String, BedRushHandler> entry : arenas.entrySet()) {
    		
    	
    		if (entry.getValue().teamrot == null || entry.getValue().teamblau == null || entry.getValue().teamgruen == null || entry.getValue().teamgelb == null) {
    			return entry.getValue();
    		}

    	}
        return null;
    }

    
    public static void createNewArena(final String name, final World w) {
    	BedRushHandler arenaData;
        arenaData = new BedRushHandler(name, new ArrayList<Player>(), w, new ArrayList<Location>());
        Bukkit.getConsoleSender().sendMessage("NA DU HUND: " + w.getName());
        BedRushManager.arenas.put(name, arenaData);
        
    }
    
    
    public static ConcurrentHashMap<String, BedRushHandler> getallArenas() {
    	return arenas;
    }

    
    public static int getAllPlayerCount() {
    	
    	int i = 0;
    	for(Entry<String, BedRushHandler> entry : arenas.entrySet()) {
    		
    		i += entry.getValue().getPlayers();

    	}
    	    	
    	return i;
    }
}
