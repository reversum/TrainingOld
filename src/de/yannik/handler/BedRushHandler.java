package de.yannik.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.yannik.utils.ItemBuilder;

public class BedRushHandler {
	    String BedRushMap;
	    List<Player> BedRushPlayers;
	    World BedRushWorld;
	    List<Location> BedRushSpawns;
	    List<Block> BedRushBlocks;

	    
	    String gamestate;
	    Player teamrot;
	    Player teamblau;
	    Player teamgruen;
	    Player teamgelb;
	    int pointsrot;
	    int pointsblau;
	    int pointsgruen;
	    int pointsgelb;
	    
	    
	    Location teamgruenspawn;
	    Location teamgelbspawn;
	    Location teamrotspawn;
	    Location teamblauspawn;

	    public BedRushHandler(final String name, final List<Player> players, final World world, final List<Location> spawns) {
	        this.BedRushMap = name;
	        this.BedRushPlayers = players;
	        this.BedRushWorld = world;
	        this.BedRushSpawns = spawns;
	        this.BedRushBlocks = new ArrayList<Block>();

	        this.pointsrot = 0;
	        this.pointsblau = 0;
	        this.pointsgelb = 0;
	        this.pointsgruen = 0;
	        this.teamrot = null;
	        this.teamblau = null;
	        this.teamgelb = null;
	        this.teamgruen = null;
	        this.gamestate = "READY";
	        this.teamgruenspawn = new Location(BedRushWorld, -1524.62, 97.00, 249.49);
	        this.teamgelbspawn = new Location(BedRushWorld, -1542.37, 97.0, 231.49);
	        this.teamrotspawn = new Location(BedRushWorld, -1524.49, 97.0, 213.79);
	        this.teamblauspawn = new Location(BedRushWorld, -1506.559, 97.0, 231.49);

	    }
	    
	    
	    public int getPlayers() {
	    	return this.BedRushPlayers.size();
	    }
	    
	    public void startGame(Player p1, Player p2, Player p3, Player p4) {
	    	this.gamestate = "INGAME";
	        this.teamrot = p1;
	        this.teamblau = p2;
	        this.teamgelb = p3;
	        this.teamgruen = p4;
	    	this.BedRushPlayers.add(p1);
	    	this.BedRushPlayers.add(p2);
	    	this.BedRushPlayers.add(p3);
	    	this.BedRushPlayers.add(p4);

	    	for (Player p : BedRushPlayers) 
	    	{

	    	Inventory inv = p.getInventory();
	    	p.getInventory().clear();

	    	  
	    	inv.setItem(getSlot(p, "SwordBedRush"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	      	inv.setItem(getSlot(p, "StickBedRush"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	      	inv.setItem(getSlot(p, "BowBedRush"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	      	inv.setItem(getSlot(p, "BlocksBedRush"), new ItemBuilder(Material.SANDSTONE, 1, 0).build());
	      	inv.setItem(getSlot(p, "Blocks2BedRush"), new ItemBuilder(Material.SANDSTONE, 2, 0).build());
	      	inv.setItem(getSlot(p, "RodBedRush"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
	    	p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_FALL, 2).build());
	    	p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	p.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_PROJECTILE, 2).build());
	    	p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	
	    	teleporttoSpawn(p);
	    	}
	    }
	    
	    public void addBlock(Block b) {
	    	this.BedRushBlocks.add(b);
	    }
	    public void leaveGame(Player p) {
	    	this.BedRushPlayers.remove(p);
	    	TeleportManager.teleportToSpawn(p);
	    	p.getInventory().clear();
	    	p.getInventory().setArmorContents(null);

	    	if (p == teamrot) {

	    		teamrot = null;
	    	} else if (p == teamblau) {
	    		teamblau = null;
	    	} else if (p == teamgruen) {
	    		teamgruen = null;
	    	} else if (p == teamgelb) {
	    		teamgelb = null;
	    	}
	    	PlayerItemHandler.givePlayerLobbyItems(p);
	    	
	    }
	    public String getName() {
	    	return this.BedRushMap;
	    	
	    }
	    
	    public String getState() {
	    	return this.gamestate;
	    	
	    }
	    public List<Location> getLocations() {
	    	return this.BedRushSpawns;
	    	
	    }
	    
	    public void endGame() {
	    	for (Block b : BedRushBlocks) {
	    		b.setType(Material.AIR);
	    
	    		BedRushBlocks.remove(b);
	    	}
	    	BedRushBlocks.clear();
	    	
	    	int restpoints = this.pointsrot + this.pointsblau + this.pointsgelb + this.pointsgruen;
	    	if (this.pointsrot < restpoints - this.pointsrot) {
	    		this.sendMessage("§eWINNER §8» §c§lRED");
	    	} else 	    	if (this.pointsblau < restpoints - this.pointsblau) {
	    		this.sendMessage("§eWINNER §8» §9§lBLUE");
	    	} else 	    	if (this.pointsgelb < restpoints - this.pointsgelb) {
	    		this.sendMessage("§eWINNER §8» §e§lYELLOW");
	    	}else 	    	if (this.pointsgruen < restpoints - this.pointsgruen) {
	    		this.sendMessage("§eWINNER §8» §2§lGRUEN");
	    	}
	    	
	        this.playSound(Sound.LEVEL_UP);

	    	
	        this.pointsrot = 0;
	        this.pointsblau = 0;
	        this.pointsgelb = 0;
	        this.pointsgruen = 0;
	        this.teamrot = null;
	        this.teamblau = null;
	        this.teamgelb = null;
	        this.teamgruen = null;
	        
	        this.gamestate = "READY";
	    }
	    
	    public void WinnerGame(Player p) {
	    	for (Block b : BedRushBlocks) {
	    		b.setType(Material.AIR);
	    
	    		BedRushBlocks.remove(b);
	    	}
	    	BedRushBlocks.clear();
	    	
	    	if (p == teamrot) {
	    		this.sendMessage("§eWINNER §8» §c§l" + p.getName());
	    	} else if (p == teamblau) {
	    		this.sendMessage("§eWINNER §8» §9§l" + p.getName());
	    	} else if (p == teamgruen) {
	    		this.sendMessage("§eWINNER §8» §2§l" + p.getName());
	    	} else if (p == teamgelb) {
	    		this.sendMessage("§eWINNER §8» §e§l" + p.getName());
	    	}
	        this.playSound(Sound.LEVEL_UP);
	        this.gamestate = "READY";

	   
	    }
	    
	    public void teleporttoSpawn(Player p) {
	    	if (p == teamrot) {

	    		p.teleport(this.teamrotspawn);
	    	} else if (p == teamblau) {
	    		p.teleport(this.teamblauspawn);
	    	} else if (p == teamgruen) {
	    		p.teleport(this.teamgruenspawn);
	    	} else if (p == teamgelb) {
	    		p.teleport(this.teamgruenspawn);
	    	}
	    }
	    public void breakBed(Player p, Player team) {
	    	if (p == team) {
	    		return;
	    	}
    		
    		if (this.teamrot == p) {
    			this.pointsrot += 1;
    		}
    		if (this.teamblau == p) {
    			this.pointsblau += 1;
    		}
    		if (this.teamgruen == p) {
    			this.pointsgruen += 1;
    		}
    		if (this.teamgelb == p) {
    			this.pointsgelb += 1;
    		}
    		teleporttoSpawn(p);
    		playSound(Sound.NOTE_PLING);
    		if (this.pointsrot == 20) {
    			WinnerGame(teamrot);
    		} else if (this.pointsblau == 20) {
    			WinnerGame(teamblau);
    		} else if (this.pointsgelb == 20) {
    			WinnerGame(teamgelb);
    		} else if (this.pointsgruen == 20) {
    			WinnerGame(teamgruen);
 
    		}

	    }
	    
	    public void watchGame() {
	    	if (this.BedRushPlayers.size() < 2) {
	    		endGame();
	    	}
	    }
	    
	    public void sendMessage(String msg) {
	    	for (Player all : this.BedRushPlayers) {
	    		all.sendMessage(msg);
	    	}
	    }
	    
	    public void respawnPlayer(Player p) {
	    	Inventory inv = p.getInventory();

	    	p.getInventory().clear();
	    	  
	    	inv.setItem(getSlot(p, "SwordBedRush"), new ItemBuilder(Material.GOLD_SWORD, 1, 0).setUnbreakable().enchant(Enchantment.DAMAGE_ALL, 1).build());
	      	inv.setItem(getSlot(p, "StickBedRush"), new ItemBuilder(Material.STICK, 1, 0).setUnbreakable().enchant(Enchantment.KNOCKBACK, 1).build());
	      	inv.setItem(getSlot(p, "BowBedRush"), new ItemBuilder(Material.BOW, 1, 0).setUnbreakable().enchant(Enchantment.ARROW_INFINITE, 1).build());
	      	inv.setItem(getSlot(p, "BlocksBedRush"), new ItemBuilder(Material.SANDSTONE, 1, 0).build());
	      	inv.setItem(getSlot(p, "Blocks2BedRush"), new ItemBuilder(Material.SANDSTONE, 2, 0).build());
	      	inv.setItem(getSlot(p, "RodBedRush"), new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable().build());
	    	p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_FALL, 2).build());
	    	p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	p.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_PROJECTILE, 2).build());
	    	p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).setUnbreakable().enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
	    	
	    	teleporttoSpawn(p);
	    	
	    	p.setGameMode(GameMode.SURVIVAL);
	    	
	    
	    }
	    
	    public List<Player> getAllPlayers() {
	    	return this.BedRushPlayers;
	    }
	    public void playSound(Sound sound) {
	    	for (Player all : this.BedRushPlayers) {
	    		all.playSound(all.getLocation(), sound, 15.0f, 20.0f);
	    	}
	    }
	
	    public Player getTeam(String team) {
	    	if (team.equals("rot")) {
	    		return this.teamrot;
	    	}
	    	if (team.equals("blau")) {
	    		return this.teamblau;
	    	}	
	    	if (team.equals("gruen")) {
	    		return this.teamgruen;
	    	}	
	    	if (team.equals("gelb")) {
	    		return this.teamgelb;
	    	}	
	    	return null;
	    }
	
	    public int getSlot(Player p, String name) {
	  	  
	  	  
			PlayerHandler data = PlayerManager.getNewPlayer(p);

		  
		  return Integer.parseInt(data.sortierungen.get(name));
	  }
}
