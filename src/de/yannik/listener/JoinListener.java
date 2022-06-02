package de.yannik.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.yannik.handler.LanguageHandler;
import de.yannik.handler.PlayerItemHandler;
import de.yannik.handler.PlayerManager;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.yannik.handler.*;
import de.yannik.main.Main;
import de.yannik.utils.PacketManager;

public class JoinListener implements Listener {
    @EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		
		p.setMaxHealth(20.0f);
		p.setHealth(20.0f);
		
		p.sendTitle("§e§lTRAINING", "");
		p.getInventory().clear();
		
		p.setGameMode(GameMode.SURVIVAL);
		TeleportManager.teleportToSpawn(p);
		p.removePotionEffect(PotionEffectType.BLINDNESS);		
		

		PlayerManager.loadPlayerData(p);
        new BukkitRunnable() {
            public void run() {
                try {
                    Thread.sleep(750L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                PlayerManager.loadPlayerData(p);
                p.sendMessage(LanguageHandler.datasnychron(p));
                ScoreboardHandler.setScoreboard(p, Main.scoretitle[0]);
        		new RangHandler().addPlayer(e.getPlayer());

            }
        }.runTaskAsynchronously((Plugin)Main.getPlugin(Main.class));

		
	}

    @EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		final Player p = e.getPlayer();
		
		if (GeneralListener.queue.contains(p)) {
			GeneralListener.queue.remove(p);
		}


	       new BukkitRunnable() {
	            public void run() {
	        		new RangHandler().removePlayer(e.getPlayer());
	        		PlayerManager.savePlayerData(p);

	        	
	            }
	        }.runTaskAsynchronously((Plugin)Main.getPlugin(Main.class));
		
	}

}
