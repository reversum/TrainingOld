package de.yannik.listener;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.yannik.handler.BedRushHandler;
import de.yannik.handler.BedRushManager;
import de.yannik.handler.FFAHandler;
import de.yannik.handler.FFAManager;
import de.yannik.handler.LanguageHandler;
import de.yannik.main.Main;
import de.yannik.utils.PacketManager;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import static com.sk89q.worldguard.bukkit.BukkitUtil.*;

public class GeneralListener implements Listener {

    public static ArrayList<Player> nodamage = new ArrayList<Player>();

    public boolean isWithinRegion(Player player, String region)
    { return isWithinRegion(player.getLocation(), region); }

public boolean isWithinRegion(Block block, String region)
    { return isWithinRegion(block.getLocation(), region); }

public boolean isWithinRegion(Location loc, String region)
{
    WorldGuardPlugin guard = WorldGuardPlugin.inst();
    com.sk89q.worldedit.Vector v = toVector(loc);
    RegionManager manager = guard.getRegionManager(loc.getWorld());
    ApplicableRegionSet set = manager.getApplicableRegions(v);
    for (ProtectedRegion each : set)
        if (each.getId().startsWith(region))
            return true;
    return false;
}
    @EventHandler
    public void on(final PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void on(final InventoryPickupItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void on(final PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void on(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE) {
            if (p.hasPermission("lobby.sdfdsff")) {
                e.setCancelled(false);
            }
            else {
                e.setCancelled(true);
            }
        }
        else {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void on(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        
       BedRushHandler bedrush = BedRushManager.getPlayerArena(p);
        
        if (bedrush != null) {
        	
            if (e.getBlock().getLocation().getX() == -1542 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 231) {


            	bedrush.breakBed(p, bedrush.getTeam("gelb"));
            }
            if (e.getBlock().getLocation().getX() == -1543 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 231) {


            	bedrush.breakBed(p, bedrush.getTeam("gelb"));
            }
            
            
            if (e.getBlock().getLocation().getX() == -1525 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 214) {


            	bedrush.breakBed(p, bedrush.getTeam("rot"));
            }
            if (e.getBlock().getLocation().getX() == -1525 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 213) {


            	bedrush.breakBed(p, bedrush.getTeam("rot"));
            }
            if (e.getBlock().getLocation().getX() == -1507 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 231) {


            	bedrush.breakBed(p, bedrush.getTeam("blau"));
            }      
            if (e.getBlock().getLocation().getX() == -1508 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 231) {


            	bedrush.breakBed(p, bedrush.getTeam("blau"));
            }       
            if (e.getBlock().getLocation().getX() == -1525 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 248) {


            	bedrush.breakBed(p, bedrush.getTeam("gruen"));
            }  
            if (e.getBlock().getLocation().getX() == -1525 || e.getBlock().getLocation().getY() == 94 || e.getBlock().getLocation().getX() == 249) {


            	bedrush.breakBed(p, bedrush.getTeam("gruen"));
            }    
        }
        if (p.getGameMode() == GameMode.CREATIVE) {
            if (p.hasPermission("lobby.sdfsdfsdf")) {
                e.setCancelled(false);
            }
            else {
                e.setCancelled(true);
            }
        }
        else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDeaweewth(PlayerDeathEvent event) {
        Player p = event.getEntity();
        event.getDrops().clear();
        event.setDeathMessage(null);
        

        FFAHandler arena = FFAManager.isInFFA(p);
        
        if (arena != null) {
        	
        for (Player all : arena.getAllPlayers()) {
        	if (p.getKiller() == null) {
            	PacketManager.sendActionBar(all, Main.prefix + "§c" + p.getName() + " §7died");

        	} else {
        	PacketManager.sendActionBar(all, Main.prefix + "§c" + p.getName() + " §7was killed by §e" + p.getKiller().getName());
        	}
        }
        event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
        event.getEntity().setGameMode(GameMode.SPECTATOR);
        Bukkit.getScheduler().runTaskLater((Plugin)Main.getPlugin(Main.class), () -> { event.getEntity().spigot().respawn(); arena.respawnPlayer(p); }, 4L);
        }
        
    }
    @EventHandler
    public void onDeaweewererth(PlayerDeathEvent event) {
        Player p = event.getEntity();
        event.getDrops().clear();
        event.setDeathMessage(null);
        

        BedRushHandler arena = BedRushManager.getPlayerArena(p);
        
        if (arena != null) {
        	
        for (Player all : arena.getAllPlayers()) {
        	if (p.getKiller() == null) {
            	PacketManager.sendActionBar(all, Main.prefix + "§c" + p.getName() + " §7died");

        	} else {
        	PacketManager.sendActionBar(all, Main.prefix + "§c" + p.getName() + " §7was killed by §e" + p.getKiller().getName());
        	}
        }
        event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
        event.getEntity().setGameMode(GameMode.SPECTATOR);
        Bukkit.getScheduler().runTaskLater((Plugin)Main.getPlugin(Main.class), () -> { event.getEntity().spigot().respawn(); arena.respawnPlayer(p); }, 4L);
        }
        
    }
    @EventHandler
    public void ArmorStandDestroy(EntityDamageByEntityEvent e){
       if (!(e.getEntity() instanceof LivingEntity)) {
           return;
         }
     
       final LivingEntity livingEntity = (LivingEntity)e.getEntity();
       if(!livingEntity.getType().equals(EntityType.ARMOR_STAND)){
          return;
       }
       
       if(e.getDamager() instanceof Player){
          Player p = (Player) e.getDamager();
                e.setCancelled(true);
                return;
         
       
       }else if(e.getDamager() instanceof Projectile){
          Projectile pro = (Projectile) e.getDamager();
          if(pro.getShooter() instanceof Player){
              Player p = (Player) pro.getShooter();
                    e.setCancelled(true);
                    return;
          }
       
          e.setCancelled(true);
            return;
       }
    }
 
    
    @EventHandler
    public void on(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final Block block = e.getBlock();
        
        FFAHandler arena = FFAManager.isInFFA(p);
        
        if (arena != null) {
        
        if (e.getBlock().getType() == Material.SANDSTONE && !isWithinRegion(p.getLocation(), "spawn")) {
            e.setCancelled(false);
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
                
         	   block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 88);
         	   block.getWorld().playSound(block.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
         	   
         	   block.setType(Material.AIR);


                }, 60L);

            return;
        }
        }

        BedRushHandler bedrush = BedRushManager.getPlayerArena(p);
        
        if (bedrush != null) {
            if (e.getBlock().getType() == Material.SANDSTONE) {

            	bedrush.addBlock(e.getBlock());
            }
        	
        }
        if (p.getGameMode() == GameMode.CREATIVE) {
            if (p.hasPermission("lobby.sdfsdfsdf")) {
                e.setCancelled(false);
            }
            else {
                e.setCancelled(true);
            }
        }
        else {
            e.setCancelled(true);
        }
    }
    
 
    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
    	Player p = e.getPlayer();
    	
    	if (e.getTo().getY() < 80) {
    		BedRushHandler bedrush = BedRushManager.getPlayerArena(p);
            
            if (bedrush != null) {

            	p.setHealth(0);
            }
    	}
    }

    @EventHandler
    public void on(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND || e.getRightClicked() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void on(final InventoryClickEvent e) {
    	final Player p = (Player) e.getWhoClicked();
	       if (e.getInventory().getName().startsWith(LanguageHandler.getSettings(p)) && !e.getInventory().getName().equals(LanguageHandler.getSettings(p))) {
   	        e.setCancelled(false);

   	    	   return;
   	       }

        e.setCancelled(true);
    }
    public static ArrayList<Material> dontInteract = new ArrayList<Material>();
    
    public static void load() {
        dontInteract.add(Material.CHEST);
        dontInteract.add(Material.ANVIL);
        dontInteract.add(Material.DROPPER);
        dontInteract.add(Material.DISPENSER);
        dontInteract.add(Material.REDSTONE_ORE);
        dontInteract.add(Material.GLOWING_REDSTONE_ORE);
        dontInteract.add(Material.TRAPPED_CHEST);
        dontInteract.add(Material.ENDER_CHEST);
        dontInteract.add(Material.HOPPER);
        dontInteract.add(Material.FURNACE);
        dontInteract.add(Material.BURNING_FURNACE);
        dontInteract.add(Material.DARK_OAK_FENCE);
        dontInteract.add(Material.FENCE);
        dontInteract.add(Material.BIRCH_FENCE);
        dontInteract.add(Material.ACACIA_FENCE);
        dontInteract.add(Material.IRON_FENCE);
        dontInteract.add(Material.JUNGLE_FENCE);
        dontInteract.add(Material.SPRUCE_FENCE);
        dontInteract.add(Material.NETHER_FENCE);
        dontInteract.add(Material.CHEST);
        dontInteract.add(Material.TRAP_DOOR);
        dontInteract.add(Material.TRAPPED_CHEST);
        dontInteract.add(Material.WORKBENCH);
        dontInteract.add(Material.HOPPER);
        dontInteract.add(Material.ANVIL);
        dontInteract.add(Material.ENCHANTMENT_TABLE);
        dontInteract.add(Material.SOIL);
    }

    @EventHandler
    public void osssn(final PlayerInteractEvent e) {
    	
    	if (dontInteract.contains(e.getClickedBlock().getType())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onasasasd(final EntityDamageEvent e) {
    	if (e.getEntityType() == EntityType.PLAYER) {
    	final Player player = (Player) e.getEntity();
    	
 
        if(e.getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
        if(player.getLastDamageCause().getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
    	if (isWithinRegion(player.getLocation(), "spawn") || player.getWorld().getName().equals("world") || nodamage.contains(player)) {
            e.setCancelled(true);
    	} else {
    		e.setCancelled(false);
    	}
    	
    	}

    
    }
    @EventHandler
    public void onaua(EntityDamageByEntityEvent e) {
    	if (e.getEntityType() == EntityType.PLAYER) {
        Player p = (Player) e.getEntity();       
        if(e.getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
        if(p.getLastDamageCause().getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
            if (e.getDamager() instanceof Player) {
                if (e.getEntityType() == EntityType.ARMOR_STAND) {
                	p.sendMessage("Hör auf!");
                	e.setDamage(0);
                    e.setCancelled(true);
                }
            }
    	}
    }
    public static CopyOnWriteArrayList<Player> queue = new CopyOnWriteArrayList<Player>();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onaua(final PlayerInteractEntityEvent e) {
    	final Player p = e.getPlayer();
    	

    	if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
    		
    		
    		if (e.getRightClicked().getCustomName().equals("§8§l» §c§lBED§4§lRUSH §7§l× §e4x1 §8§l«")) {

    			handleQueueBedRush(p);
    			
        		e.setCancelled(true);

    		}
    		return;
    	}


    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onauaewew(final PlayerInteractAtEntityEvent e) {
    	final Player p = e.getPlayer();
    	

    	if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
    		
    		
    		if (e.getRightClicked().getCustomName().equals("§8§l» §c§lBED§4§lRUSH §7§l× §e4x1 §8§l«")) {

    			handleQueueBedRush(p);
    			
        		e.setCancelled(true);

    		}
    		return;
    	}


    }
    
    public static void handleQueueBedRush(Player p) {
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 25.0f, 25.0f);

		if (queue.contains(p)) {
			queue.remove(p);
		    p.sendMessage(LanguageHandler.removeQueue(p));
		} else {
			queue.add(p);
		    p.sendMessage(LanguageHandler.addQueue(p));

		}
    }
    
    public static void QueueRefresh() {
 
    	
    	for (Player p : queue) {
    		PacketManager.sendActionBar(p, Main.prefix + " §6§l" + queue.size() + " §7players in queue");
    	}
    	if (queue.size() >= 4) {
    	    BedRushHandler arena = BedRushManager.getFreeArena();
    	    
    	    arena.startGame(queue.get(0), queue.get(1), queue.get(2), queue.get(3));
    	    queue.remove(queue.get(0));
    	    queue.remove(queue.get(1));
    	    queue.remove(queue.get(2));
    	    queue.remove(queue.get(3));

    	    
    	}
    }
    
    public static Player getPlayerByUUID(String UUID) {
    	
    	for (Player p : Bukkit.getOnlinePlayers()) {
    		if (p.getUniqueId().toString().equals(UUID)) {
    			return p;
    		}
    	}
    	return null;
    }
    @EventHandler
    public void on(final EntityDamageByEntityEvent e) {
    	final Player player = (Player) e.getDamager();
        if(e.getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
        if(player.getLastDamageCause().getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }

    	if (e.getEntityType() == EntityType.ARMOR_STAND) {
    		e.setCancelled(true);
    		return;
    	}
        if(e.getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
        if(player.getLastDamageCause().getCause() == DamageCause.FALL){
            e.setCancelled(true);
        }
    	if (isWithinRegion(player.getLocation(), "spawn") || player.getWorld().getName().equals("world") || nodamage.contains(player)) {
            e.setCancelled(true);
    	} else {
    		e.setCancelled(false);
    	}

    }
    @EventHandler
    public void on(final BlockExplodeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void on(final CreatureSpawnEvent e) {
    	if (e.getEntityType() == EntityType.ARMOR_STAND) {
    		e.setCancelled(false);
    	} else {
        e.setCancelled(true);
    	}
    }
    @EventHandler
    public void on(final EntityExplodeEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final LeavesDecayEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final PlayerItemConsumeEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final PlayerBucketEmptyEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final PlayerBucketFillEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void on(final PlayerUnleashEntityEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void on(final AsyncPlayerChatEvent e) {
    	final Player p = e.getPlayer();
        final IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        if (user == null) {
            return;
        }
        
    	final IPermissionGroup group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user); 
    	
        p.playSound(p.getLocation(), Sound.LAVA_POP, 25.0f, 25.0f);

    	if (group.getName().equals("Admin")) {
    		e.setFormat("§8[§4Administator§8] §4" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("SrModerator")) {
    		e.setFormat("§8[§cSrModerator§8] §c" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Content")) {
    		e.setFormat("§8[§bContent§8] §b" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Developer")) {
    		e.setFormat("§8[§bDeveloper§8] §b" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Moderator")) {
    		e.setFormat("§8[§cModerator§8] §c" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Supporter")) {
    		e.setFormat("§8[§aSupporter§8] §a" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("TestSupporter")) {
    		e.setFormat("§8[§9Test-Supporter§8] §9" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Builder")) {
    		e.setFormat("§8[§eBuilder§8] §e" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("YouTuber")) {
    		e.setFormat("§8[§5YouTuber§8] §5" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("PremiumPlus")) {
    		e.setFormat("§8[§aPremium-Plus§8] §a" + p.getName() + " §7» §f" + e.getMessage());
    	} else if (group.getName().equals("Premium")) {
    		e.setFormat("§8[§6Premium§8] §6" + p.getName() + " §7» §f" + e.getMessage());
    	} else {
    		e.setFormat("§8[§3Spieler§8] §3" + p.getName() + " §7» §f" + e.getMessage());

    	}
    	
    }
    @EventHandler
    public void on(final PlayerLeashEntityEvent e) {
        e.setCancelled(true);
    }
}
