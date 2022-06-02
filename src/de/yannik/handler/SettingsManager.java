package de.yannik.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WeatherType;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;

import de.yannik.main.Main;
import de.yannik.sql.MySQL;
import de.yannik.utils.ItemBuilder;
import de.yannik.utils.ItemManager;
import de.yannik.utils.PacketManager;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;


public class SettingsManager implements CommandExecutor, Listener {
    public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();

 
    
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("settings")){
			Player p = (Player)s;
			
			
			if (args.length == 0) {
			InventoryHandlers.openSettings(p);
			} else {
			
				if (p.hasPermission("*")) {
					if (args[0].equals("1v1")) {
			        ArmorStand as = p.getLocation().getWorld().spawn(p.getLocation(), ArmorStand.class);
			        
			        as.setBasePlate(false);
			        as.setArms(true);
			        as.setCustomName("§8§l» §6§lMLGRUSH §7§l× §e1vs1 §8§l«");
			        as.setCustomNameVisible(true);
			        as.setLeftArmPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));
			        as.setRightLegPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));

			        as.setVisible(true);
			        as.setItemInHand(new ItemBuilder(Material.STICK, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
			        as.setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner("calledMentrex").build());
			        as.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
			        as.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).build());
			        as.setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).build());

			        as.setCanPickupItems(false);
			        as.setGravity(true);
					} else 					if (args[0].equals("2vs2")) {

				        ArmorStand as = p.getLocation().getWorld().spawn(p.getLocation(), ArmorStand.class);
				        
				        as.setBasePlate(false);
				        as.setArms(true);
				        as.setCustomName("§8§l» §6§lMLGRUSH §7§l× §e2vs2 §8§l«");
				        as.setCustomNameVisible(true);
				        as.setLeftArmPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));
				        as.setRightLegPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));
				        as.setVisible(true);
				        as.setItemInHand(new ItemBuilder(Material.WOOD_AXE, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
				        as.setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner("BorussiaGladbach").build());
				        as.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
				        as.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).build());
				        as.setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).build());

				        as.setCanPickupItems(false);
				        as.setGravity(true);	
					} else {
		        ArmorStand as = p.getLocation().getWorld().spawn(p.getLocation(), ArmorStand.class);
				        
				        as.setBasePlate(false);
				        as.setArms(true);
				        as.setCustomName("§8§l» §c§lBED§4§lRUSH §7§l× §e4x1 §8§l«");
				        as.setCustomNameVisible(true);
				        as.setLeftArmPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));
				        as.setRightLegPose(new EulerAngle(Math.toRadians(90),Math.toRadians(180), Math.toRadians(90)));
				        as.setVisible(true);
				        as.setItemInHand(new ItemBuilder(Material.GOLD_SWORD, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
				        as.setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner("BorussiaGladbach").build());
				        as.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).enchant(Enchantment.KNOCKBACK, 3).build());
				        as.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1, 0).build());
				        as.setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).build());

				        as.setCanPickupItems(false);
				        as.setGravity(true);	
					}
				}
			}
			
			
					
		}
		if(c.getName().equalsIgnoreCase("spawn")){
			Player p = (Player)s;

			PlayerManager.leaveAll(p);
			
			
					
		}
		return false;
	}
	  @EventHandler
	    public void Close(final InventoryCloseEvent e) {
	       final Player p = (Player) e.getPlayer();
	       
	       
	
	       if ( e.getInventory().getName().startsWith(LanguageHandler.getSettings(p)) && !e.getInventory().getName().equals(LanguageHandler.getSettings(p))) {
	    	   if (!this.checkInv(p.getInventory())) {
	    	   p.getInventory().clear();
	    	   PlayerItemHandler.givePlayerLobbyItems(p);
	    	   p.sendMessage(LanguageHandler.dontput(p));
	    	   return;
	    	   } else {
	    	       
	    	    	   p.getInventory().clear();
	    	    	   PlayerItemHandler.givePlayerLobbyItems(p);
	    	    	   inv.remove(p);
	    	    	   p.sendMessage(LanguageHandler.saved(p));
	    	    	   
	    	    	   if (e.getInventory().getName().contains("BuildFFA")) {
	    	    		   this.saveInventory(p, e.getInventory(), "BuildFFA");
	    	    	   }
	    	    	   if (e.getInventory().getName().contains("ClassicFFA")) {
	    	    		   this.saveInventory(p, e.getInventory(), "ClassicFFA");
	    	    	   }
	    	    	   if (e.getInventory().getName().contains("ClassicFFA")) {
	    	    		   this.saveInventory(p, e.getInventory(), "ClassicFFA");
	    	    	   }
	    	    	   if (e.getInventory().getName().contains("MLGRush")) {
	    	    		   this.saveInventory(p, e.getInventory(), "MLGRush");
	    	    	   }
	    	    	   if (e.getInventory().getName().contains("BedRush")) {
	    	    		   this.saveInventory(p, e.getInventory(), "BedRush");
	    	    	   }
	    	   }
	       }

	       
	      
	    }
	  
private void saveInventory(Player p, Inventory inv, String name) {
	PlayerHandler data = PlayerManager.getNewPlayer(p);

    for (byte i = 0; i < 9; ++i) {
        if (inv.getItem((int)i) != null) {
            switch (inv.getItem((int)i).getType()) {
                case GOLD_SWORD: {
                	data.sortierungen.put("Sword"+name, Integer.toString(i));
                    break;
                }
                case BOW: {
                	data.sortierungen.put("Bow"+name, Integer.toString(i));
                    break;
                }
                case STICK: {
                	data.sortierungen.put("Stick"+name, Integer.toString(i));
                    break;
                }
                case SANDSTONE: {
                 	if (inv.getItem(i).getAmount() == 2) {
                    	data.sortierungen.put("Blocks2"+name, Integer.toString(i));
                    	} else {
                	data.sortierungen.put("Blocks"+name, Integer.toString(i));
                    	}
                    break;
                }
     
                case ENDER_PEARL: {
                	data.sortierungen.put("Pearl"+name, Integer.toString(i));
                    break;
                }
                case FISHING_ROD: {
                	data.sortierungen.put("Rod"+name, Integer.toString(i));
                    break;
                }
                case WOOD_PICKAXE: {
                	data.sortierungen.put("Pickaxe"+name, Integer.toString(i));
                    break;
                }
            }
        }
    }
}
	  
	    private boolean checkInv(final Inventory inv) {

	        for (byte i = 0; i < 9; ++i) {
	            if (inv.getItem((int)i) != null) {
	                switch (inv.getItem((int)i).getType()) {
	                    case GOLD_SWORD: {
	                    	return false;
	                    }
	                    case BOW: {
	                    	return false;
	                    }
	                    case STICK: {
	                    	return false;
	                    }
	                    case SANDSTONE: {
	                    	return false;
	                    }
	                    case ENDER_PEARL: {
	                    	return false;
	                    }
	                    case FISHING_ROD: {
	                    	return false;
	                    }
	                    case WOOD_PICKAXE: {
	                    	return false;
	                    }
	                }
	            }
	        }
	        return true;

	    }
	
	  @EventHandler
	    public void onClick(final InventoryClickEvent e) {
	       final Player p = (Player) e.getWhoClicked();
	       
	       if (e.getClickedInventory().getName().equals(LanguageHandler.getSettings(p))) {
	    	   if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
	    		   InventoryHandlers.openClassicFFA(p);
	    	   }
	    	   
	    	   if (e.getCurrentItem().getType() == Material.SANDSTONE) {
	    		   InventoryHandlers.openBuildFFA(p);
	    	   }
	    	   if (e.getCurrentItem().getType() == Material.BED) {
	    		   InventoryHandlers.openBedRush(p);
	    	   }
	    	   if (e.getCurrentItem().getType() == Material.STICK) {
	    		   InventoryHandlers.openMLGRush(p);
	    	   }
	       }
	       
	      
	    }

}
