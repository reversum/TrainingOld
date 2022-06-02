package de.yannik.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import de.yannik.handler.LanguageHandler;
import de.yannik.main.Main;
import de.yannik.utils.ItemsEnchant;
import de.yannik.utils.PacketManager;

public class SwitchItemListener implements Listener {

	
    @EventHandler
    public void on(final PlayerItemHeldEvent e) {
 
    	final Player p = e.getPlayer();
    	if (p.getWorld().getName().equals("world")) {
    	p.playSound(p.getLocation(), Sound.CLICK, 20.0f, 20.0f);
    	}
   
    }
}
