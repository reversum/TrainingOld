package de.yannik.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.yannik.handler.LanguageHandler;
import de.yannik.handler.PlayerManager;
import de.yannik.handler.RangHandler;
import de.yannik.handler.ScoreboardHandler;
import de.yannik.handler.SettingsManager;
import de.yannik.handler.TeleportManager;
import de.yannik.listener.GeneralListener;
import de.yannik.listener.JoinListener;
import de.yannik.listener.PlayerInteractListener;
import de.yannik.listener.PlayerInventoryClickListener;
import de.yannik.listener.SwitchItemListener;
import de.yannik.sql.MySQL;
import de.yannik.sql.MySQLHandler;


public class Main extends JavaPlugin {

	public static File file = new File("plugins//Training//spawns.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	public static String prefix = "§8[§eTraining§8] §7";
	public static String[] scoretitle = {"§6Lydoria§8.§6eu", "§6§lL§6ydoria§8.§6eu", "§6§lLy§6doria§8.§6eu", "§6§lLyd§6oria§8.§6eu", "§6§lLydo§6ria§8.§6eu", "§6§lLydor§6ia§8.§6eu", "§6§lLydori§6a§8.§6eu", "§6§lLydoria§8.§6eu", "§6§lLydoria§8§l.§6eu", "§6§lLydoria§8§l.§6§le§6u", "§6§lLydoria§8§l.§6§leu"};

	public static int i = 0;
	
	
	
	  
    public void onEnable() {

        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((Listener)new GeneralListener(), (Plugin)this);
        pm.registerEvents((Listener)new JoinListener(), (Plugin)this);
        pm.registerEvents((Listener)new SwitchItemListener(), (Plugin)this);
		getCommand("changepoint").setExecutor(new TeleportManager());
		getCommand("settings").setExecutor(new SettingsManager());
		getCommand("spawn").setExecutor(new SettingsManager());

    	RangHandler teams = new RangHandler();
    	 
		teams.create("admin", 10, "§8[§4A§8] §4", null, "Admin");
		teams.create("srmod", 11, "§8[§cSM§8] §c", null, "SrModerator");
		teams.create("dev", 12, "§8[§bD§8] §b", null, "Developer");
		teams.create("content", 13, "§8[§bC§8] §b", null, "Content");
		teams.create("mod", 14, "§8[§cM§8] §c", null, "Moderator");
		teams.create("sup", 15, "§8[§aS§8] §a", null, "Supporter");
		teams.create("tsup", 16, "§8[§9TS§8] §9", null, "Test-Supporter");
		teams.create("builder", 17, "§8[§eB§8] §e", null, "Builder");
		teams.create("youtuber", 18, "§8[§5YT§8] §5", null, "YouTuber");
		teams.create("premiumplus", 19, "§8[§aP+§8] §a", null, "PremiumPlus");
		teams.create("premium", 19, "§8[§6P§8] §6", null, "Premium");

		teams.create("spieler", 30, "§8[§3S§8] §3",null , null);
 
		teams.update();
 
		
		
        GeneralListener.load();
        pm.registerEvents((Listener)new PlayerInteractListener(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerInventoryClickListener(), (Plugin)this);
        pm.registerEvents((Listener)new SettingsManager(), (Plugin)this);

        
        MySQL.connect();
        MySQLHandler.loadFFAArenas();
    }
    
}
