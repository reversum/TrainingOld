package de.yannik.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.yannik.handler.InventoryHandlers;
import de.yannik.handler.PlayerItemHandler;
import de.yannik.main.Main;



public class PlayerInteractListener implements Listener {

	 
    @EventHandler
    public void on(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack hand = p.getItemInHand();

        if (hand != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (hand.getType() == Material.COMPASS) {
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0f, 5.0f);
                InventoryHandlers.getAllGames(p);
            }
            if (hand.getType() == Material.MAGMA_CREAM) {
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0f, 5.0f);
                p.kickPlayer("");
            }
 
        }
    }
}
