package de.yannik.handler;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.yannik.utils.ItemBuilder;
import de.yannik.utils.ItemManager;

public class PlayerItemHandler implements Listener {
	
	public static void givePlayerLobbyItems(Player p ) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);

		if (data == null) {
			
			p.kickPlayer("§8[§bLobby§8] §c§lError!");
			return;
		}
		p.getInventory().setItem(3, new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable().setDisplayName(LanguageHandler.challange(p)).build());	
		p.getInventory().setItem(1, ItemManager.create(Material.COMPASS, 0, 1, LanguageHandler.compass(p), null));
		p.getInventory().setItem(5, ItemManager.create(Material.WATCH, 0, 1, LanguageHandler.spectate(p), null));
		p.getInventory().setItem(7, ItemManager.create(Material.MAGMA_CREAM, 0, 1, LanguageHandler.leave(p), null));

	}
	
	

}
