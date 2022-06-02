package de.yannik.handler;

import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.yannik.main.Main;
import de.yannik.utils.ItemBuilder;
import de.yannik.utils.ItemManager;
public class InventoryHandlers {
	

  public static void getAllGames(Player p) {
		  
		  final PlayerHandler data = PlayerManager.getNewPlayer(p);
	        Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*3, LanguageHandler.getCompassTitle(p));
	  
	        
	    	for(Entry<String, FFAHandler> entry : FFAManager.getallArenas().entrySet()) {
	    		
	    		FFAHandler arena =  entry.getValue();
	    		
	    		inv.addItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setDisplayName("§8» §e§lFFA §8┃ §7" + arena.FFAMode).setLore(new String[]{  "", "§8➢ §7Online §8× §e" + arena.getPlayers()}).build());

	    	}
	    	for(Entry<String, BedRushHandler> entry : BedRushManager.getallArenas().entrySet()) {
	    		
	    		BedRushHandler arena =  entry.getValue();
	    		
	    		if (arena.getState() == "READY") {
	    		
	    		inv.addItem(new ItemBuilder(Material.BED, 1, 0).setDisplayName("§8» §c§lBed§4§lRush §8┃ §7" + arena.getName()).setLore(new String[]{  "", "§8➢ §7Online §8× §e" + arena.getPlayers()}).build());
	    		}
	    	}
	    
	        p.openInventory(inv);

	    }
  
  
  public static void openSettings(Player p) {
	  
        Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*3, LanguageHandler.getSettings(p));
        for (int i = 0; i < 9 * 3; ++i) {
            inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setDisplayName("§0").build());
        }
        
    	inv.setItem(10, new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setDisplayName("§8» §e§lFFA §8┃ §9Classic").build());
    	inv.setItem(11, new ItemBuilder(Material.SANDSTONE, 1, 0).setDisplayName("§8» §e§lFFA §8┃ §9BuildFFA").build());
    	inv.setItem(15, new ItemBuilder(Material.STICK, 1, 0).setDisplayName("§8» §b§lTRAINING §8┃ §6MLGRush").build());
    	inv.setItem(16, new ItemBuilder(Material.BED, 1, 0).setDisplayName("§8» §b§lTRAINING §8┃ §cBed§4Rush").build());

        
    
        p.openInventory(inv);

    }



	
	
	
	  public static void openBedRush(Player p) {
		  
	      Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*1, LanguageHandler.getSettings(p) + " BedRush");
	 
	      
	    	inv.setItem(getSlot(p, "SwordBedRush"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	      	inv.setItem(getSlot(p, "StickBedRush"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	      	inv.setItem(getSlot(p, "BowBedRush"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	      	inv.setItem(getSlot(p, "BlocksBedRush"), new ItemBuilder(Material.SANDSTONE, 1, 0).build());
	      	inv.setItem(getSlot(p, "Blocks2BedRush"), new ItemBuilder(Material.SANDSTONE, 2, 0).build());
	      	inv.setItem(getSlot(p, "RodBedRush"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
	      p.openInventory(inv);
	        p.getInventory().clear();
	        SettingsManager.inv.put(p, inv);

	  } 
	  
	  public static void openMLGRush(Player p) {
		  
	      Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*1, LanguageHandler.getSettings(p) + " MLGRush");
	 
	      
	  	inv.setItem(getSlot(p, "StickMLGRush"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	  	inv.setItem(getSlot(p, "PickaxeMLGRush"), new ItemBuilder(Material.WOOD_PICKAXE, 1, 0).setUnbreakable().enchant(Enchantment.DIG_SPEED, 3).build());

	  	inv.setItem(getSlot(p, "BlocksMLGRush"), new ItemBuilder(Material.SANDSTONE, 1, 0).build());
	  	inv.setItem(getSlot(p, "Blocks2MLGRush"), new ItemBuilder(Material.SANDSTONE, 2, 0).build());

	      p.openInventory(inv);
	        p.getInventory().clear();
	        SettingsManager.inv.put(p, inv);

	  } 
	  
	  
  public static void openClassicFFA(Player p) {
	  
      Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*1, LanguageHandler.getSettings(p) + " ClassicFFA");
 
      
  	inv.setItem(getSlot(p, "SwordFFAClassic"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
  	inv.setItem(getSlot(p, "RodFFAClassic"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
  	inv.setItem(getSlot(p, "BowFFAClassic"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());


  
      p.openInventory(inv);
      p.getInventory().clear();
      SettingsManager.inv.put(p, inv);


  } 
  public static void openBuildFFA(Player p) {
	  
      Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 9*1, LanguageHandler.getSettings(p) + " BuildFFA");
 
      
  	inv.setItem(getSlot(p, "SwordBuildFFA"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
  	inv.setItem(getSlot(p, "StickBuildFFA"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
  	inv.setItem(getSlot(p, "BowBuildFFA"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
  	inv.setItem(getSlot(p, "BlocksBuildFFA"), new ItemBuilder(Material.SANDSTONE, 1, 0).build());
  	inv.setItem(getSlot(p, "Blocks2BuildFFA"), new ItemBuilder(Material.SANDSTONE, 2, 0).build());
  	inv.setItem(getSlot(p, "PearlBuildFFA"), new ItemBuilder(Material.ENDER_PEARL, 1, 0).build());


  
      p.openInventory(inv);
      p.getInventory().clear();
      SettingsManager.inv.put(p, inv);

  } 
  
  
  
  public static int getSlot(Player p, String name) {
	  
	  
		PlayerHandler data = PlayerManager.getNewPlayer(p);

	  
	  return Integer.parseInt(data.sortierungen.get(name));
  }
}
