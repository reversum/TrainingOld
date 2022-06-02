package de.yannik.listener;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.yannik.handler.FFAHandler;
import de.yannik.handler.FFAManager;
import de.yannik.handler.InventoryHandlers;
import de.yannik.handler.LanguageHandler;
import de.yannik.handler.PlayerHandler;
import de.yannik.handler.PlayerManager;
import de.yannik.handler.TeleportManager;
import de.yannik.main.Main;
import de.yannik.utils.ItemBuilder;



public class PlayerInventoryClickListener implements Listener {

	 
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
       final Player p = (Player) e.getWhoClicked();
       
   	for(Entry<String, FFAHandler> entry : FFAManager.getallArenas().entrySet()) {
   		
   		FFAHandler arena =  entry.getValue();
   		
        if ( e.getInventory().getName().startsWith(LanguageHandler.getCompassTitle(p))) {
        	if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §e§lFFA §8┃ §7" + arena.getFFAName())) {
        		p.sendMessage("§8» §e§lFFA §8┃ §9" + arena.getFFAName());
        		arena.joinFFA(p);

        	}
       
        }
        }
    if ( e.getInventory().getName().startsWith(LanguageHandler.getCompassTitle(p))) {

 	if (e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §c§lBed§4§lRush")) {
		p.sendMessage(LanguageHandler.geheIndieQueue(p));
		return;

	}
    }
   	
    	   

      
    	   
       
    }
}
