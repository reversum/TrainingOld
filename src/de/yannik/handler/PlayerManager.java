package de.yannik.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.yannik.sql.MySQL;
import de.yannik.sql.MySQLHandler;
import de.yannik.utils.ItemBuilder;


public class PlayerManager {
private static ConcurrentHashMap<Player, PlayerHandler> players;
    
    static {
        PlayerManager.players = new ConcurrentHashMap<Player, PlayerHandler>();
    }
    
    public static PlayerHandler getNewPlayer(final Player p) {
        if (PlayerManager.players.containsKey(p)) {
            return PlayerManager.players.get(p);
        }
        return null;
    }
    
    private static List<String> toList(final String ss) {
        final List<String> toLoad = new ArrayList<String>();
        if (!ss.isEmpty()) {
            final String[] ff = ss.split(";");
            for (int i = 0; i < ff.length; ++i) {
                toLoad.add(ff[i]);
            }
        }
        return toLoad;
    }
    
    private static String toString(final List<String> list) {
        String ss = "";
        if (!list.isEmpty()) {
            for (final String ff : list) {
                ss = String.valueOf(ss) + ff + ";";
            }
        }
        return ss;
    }
    
    public static String toStringVorherMap(HashMap<String, String> map) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append(key + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
    public static HashMap<String, String> convertWithStream(String mapAsString) {
    	if (mapAsString.equals("") || mapAsString.equals(null)) {
    		
    		HashMap<String, String> sort = new HashMap<String, String>();
    		return sort;
    	}
        Map<String, String> map = Arrays.stream(mapAsString.split(","))
          .map(entry -> entry.split("="))
          .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
        return (HashMap<String, String>) map;
    }

     
    public static void loadPlayerData(final Player p) {
    	PlayerHandler playerData;
            playerData = new PlayerHandler(p, MySQLHandler.getValueFromLobbyData(p.getUniqueId().toString(), "language"), convertWithStream(MySQLHandler.getString(p.getUniqueId().toString(), "sort")));
 
        PlayerManager.players.put(p, playerData);
        
        
        
        if (playerData.sortierungen.get("SwordFFAClassic") == null) {
        	playerData.sortierungen.put("SwordFFAClassic", "0");
        	playerData.sortierungen.put("RodFFAClassic", "1");
        	playerData.sortierungen.put("BowFFAClassic", "2");
        	playerData.sortierungen.put("SwordBuildFFA", "0");
        	playerData.sortierungen.put("BowBuildFFA", "1");
        	playerData.sortierungen.put("StickBuildFFA", "2");
        	playerData.sortierungen.put("BlocksBuildFFA", "3");
        	playerData.sortierungen.put("Blocks2BuildFFA", "4");
        	playerData.sortierungen.put("PearlBuildFFA", "8");
        	playerData.sortierungen.put("StickMLGRush", "0");
        	playerData.sortierungen.put("PickaxeMLGRush", "1");
        	playerData.sortierungen.put("BlocksMLGRush", "2");
        	playerData.sortierungen.put("Blocks2MLGRush", "3");
        	playerData.sortierungen.put("SwordBedRush", "0");
        	playerData.sortierungen.put("BowBedRush", "1");
        	playerData.sortierungen.put("StickBedRush", "2");
        	playerData.sortierungen.put("RodBedRush", "3");
        	playerData.sortierungen.put("BlocksBedRush", "4");
        	playerData.sortierungen.put("Blocks2BedRush", "5");
        }
		PlayerItemHandler.givePlayerLobbyItems(p);

    }
    
   public static void savePlayerData(final Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);

    	if (data == null) {
    		return;
    	}        

    	MySQLHandler.setStringValue(p.getUniqueId().toString(), "sort", toStringVorherMap(data.sortierungen));

    	
    	for(Entry<String, FFAHandler> entry : FFAManager.getallArenas().entrySet()) {
    		
    		FFAHandler arena =  entry.getValue();
    		
    		if (arena.FFAPlayers.contains(p)) {
    			arena.FFAPlayers.remove(p);
    		}
    		

    	}
   }
    
    
   
  public static void leaveAll(final Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);

   	if (data == null) {
   		return;
   	}        


   	
   	for(Entry<String, FFAHandler> entry : FFAManager.getallArenas().entrySet()) {
   		
   		FFAHandler arena =  entry.getValue();
   		
   		if (arena.FFAPlayers.contains(p)) {
   			arena.leaveFFA(p);
   			arena.FFAPlayers.remove(p);
   			
   		}
   		

   	}
  }

    

}
