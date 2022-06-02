package de.yannik.handler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.yannik.utils.ItemBuilder;

public class FFAHandler {
	    String FFAMode;
	    List<Player> FFAPlayers;
	    World FFAWorld;
	    Inventory FFAInv;
	    Location FFALoc;

	    public FFAHandler(final String name, final List<Player> players, final World world, final Inventory inv) {
	        this.FFAMode = name;
	        this.FFAPlayers = players;
	        this.FFAWorld = world;
	        this.FFAInv = inv;
	        this.FFALoc = new Location(Bukkit.getServer().getWorld(name), 0, 0, 0);
	    }
	    
	    
	    public int getPlayers() {
	    	return this.FFAPlayers.size();
	    }
	    
	    public void joinFFA(Player p) {
	    	this.FFAPlayers.add(p);
	    	Inventory inv = p.getInventory();
	    	p.getInventory().clear();
	    	p.teleport(new Location(Bukkit.getWorld(getFFAName()), getFFALoc().getX(), getFFALoc().getY(),getFFALoc().getZ()));
	    	
	    	if (FFAMode.contains("Classic")) {
	    	  	inv.setItem(getSlot(p, "SwordFFAClassic"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	    	  	inv.setItem(getSlot(p, "RodFFAClassic"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
	    	  	inv.setItem(getSlot(p, "BowFFAClassic"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	    	} else {
	    	  	inv.setItem(getSlot(p, "SwordBuildFFA"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	    	  	inv.setItem(getSlot(p, "StickBuildFFA"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	    	  	inv.setItem(getSlot(p, "BowBuildFFA"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	    	  	inv.setItem(getSlot(p, "BlocksBuildFFA"), new ItemBuilder(Material.SANDSTONE, 64, 0).build());
	    	  	inv.setItem(getSlot(p, "Blocks2BuildFFA"), new ItemBuilder(Material.SANDSTONE, 64, 0).build());
	    	  	inv.setItem(getSlot(p, "PearlBuildFFA"), new ItemBuilder(Material.ENDER_PEARL, 1, 0).build());
	    	}
	    	
	    	p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_FALL, 2).build());
	    	p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	p.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_PROJECTILE, 2).build());
	    	p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());

	    }
	    
	    public void respawnPlayer(Player p) {
	    	Inventory inv = p.getInventory();

	    	p.getInventory().clear();
	    	p.teleport(new Location(Bukkit.getWorld(getFFAName()), getFFALoc().getX(), getFFALoc().getY(),getFFALoc().getZ()));
	    	
	    	if (FFAMode.contains("Classic")) {
	    	  	inv.setItem(getSlot(p, "SwordFFAClassic"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	    	  	inv.setItem(getSlot(p, "RodFFAClassic"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
	    	  	inv.setItem(getSlot(p, "BowFFAClassic"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	    	} else {
	    	  	inv.setItem(getSlot(p, "SwordBuildFFA"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	    	  	inv.setItem(getSlot(p, "StickBuildFFA"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	    	  	inv.setItem(getSlot(p, "BowBuildFFA"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	    	  	inv.setItem(getSlot(p, "BlocksBuildFFA"), new ItemBuilder(Material.SANDSTONE, 64, 0).build());
	    	  	inv.setItem(getSlot(p, "Blocks2BuildFFA"), new ItemBuilder(Material.SANDSTONE, 64, 0).build());
	    	  	inv.setItem(getSlot(p, "PearlBuildFFA"), new ItemBuilder(Material.ENDER_PEARL, 1, 0).build());
	    	}
	    	
	    	p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_FALL, 2).build());
	    	p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	p.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_PROJECTILE, 2).build());
	    	p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());

	    	
	    	p.setGameMode(GameMode.SURVIVAL);
	    	
	    
	    }
	    public void leaveFFA(Player p) {
	    	this.FFAPlayers.remove(p);
	    	TeleportManager.teleportToSpawn(p);
	    	p.getInventory().clear();
	    	p.getInventory().setArmorContents(null);
	    	p.setGameMode(GameMode.SURVIVAL);

	    	PlayerItemHandler.givePlayerLobbyItems(p);
	    	
	    }
	    public String getFFAName() {
	    	return this.FFAMode;
	    	
	    }
	    
	    public List<Player> getAllPlayers() {
	    	return this.FFAPlayers;
	    }
	    public Location getFFALoc() {
	    	return this.FFALoc;
	    	
	    }
	    public Location setFFASpawn(String name, float x, float y, float z, float h) {
	    	return this.FFALoc = new Location(Bukkit.getServer().getWorld(name), x, y, z);
	    	
	    }
	    
	    public int getSlot(Player p, String name) {
	  	  
	  	  
			PlayerHandler data = PlayerManager.getNewPlayer(p);

		  
		  return Integer.parseInt(data.sortierungen.get(name));
	  }
}
