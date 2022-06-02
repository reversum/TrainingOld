package de.yannik.handler;

import java.io.IOException;
import java.util.ArrayList;
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
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import de.yannik.main.Main;
import de.yannik.sql.MySQL;
import de.yannik.utils.PacketManager;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;


public class TeleportManager implements CommandExecutor{
    public static ArrayList<Location> teleport = new ArrayList<Location>();

    
    private static String toNormal(final List<Location> list) {
        String ss = "";
        if (!list.isEmpty()) {
            for (final Location ff : list) {
                ss = String.valueOf(ss) + ff.toString() + ";";
            }
        }
        return ss;
    }
	
    
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("changepoint")){
			Player p = (Player)s;
			if(p.hasPermission("*")){
			
					if(args[0].equalsIgnoreCase("loc")){
						String sex = args[1];
				           p.sendMessage("§a" + p.getLocation().getX() + " §8| §a" + p.getLocation().getY() + " §8| §a" + p.getLocation().getZ() + " §8| §a" + p.getLocation().getYaw());

			            MySQL.update("INSERT INTO training_spawnpoints(x, y,z,heading, ffaname) VALUES ('"+ p.getLocation().getX()+"', '"+ p.getLocation().getY()+"', '"+ p.getLocation().getZ()+"', '"+ p.getLocation().getYaw()+"', '"+ sex +"');");


						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						
					} else
					if(args[0].equalsIgnoreCase("spawn")){
						String i = "Spawn";
						Location loc = p.getLocation();
						Main.cfg.set("Spawn" + i + ".X", loc.getX());
						Main.cfg.set("Spawn" + i + ".Y", loc.getY());
						Main.cfg.set("Spawn" + i + ".Z", loc.getZ());
						Main.cfg.set("Spawn" + i + ".Yaw", loc.getYaw());
						Main.cfg.set("Spawn" + i + ".Pitch", loc.getPitch());
						Main.cfg.set("Spawn" + i + ".Weltname", loc.getWorld().getName());
						
						try {
							Main.cfg.save(Main.file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						p.sendMessage(Main.prefix + "§8» §e§l" + i);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						
					}
				else{
					String i = args[0];
					Location loc = p.getLocation();
					Main.cfg.set("Spawn" + i + ".X", loc.getX());
						Main.cfg.set("Spawn" + i + ".Y", loc.getY());
						Main.cfg.set("Spawn" + i + ".Z", loc.getZ());
						Main.cfg.set("Spawn" + i + ".Yaw", loc.getYaw());
						Main.cfg.set("Spawn" + i + ".Pitch", loc.getPitch());
						Main.cfg.set("Spawn" + i + ".Weltname", loc.getWorld().getName());
						try {
							Main.cfg.save(Main.file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						p.sendMessage(Main.prefix + "§8» §e§l" + i);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						
					}
				
				
			}else{
				p.sendMessage(Main.prefix + "§cDu hast dafür keine Berechtigung!");
			}
		}
		return false;
	}
	
	public static void teleportToGame(Player p, String Spawn){
		
		
		
		
		p.damage(0);
		p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 25.0f, 22.0f);

		 PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.MOB_APPEARANCE, false, 1, 1, 1, 1, 1, 1, 0, 1);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,9999,1)); // give player blindness effect for 5 seconds
		p.setVelocity(new Vector(1, 1, 0));
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
      
    		int x = Main.cfg.getInt("Spawn" + Spawn + ".X");
    		int y = Main.cfg.getInt("Spawn" + Spawn + ".Y");
    		int z = Main.cfg.getInt("Spawn" +  Spawn + ".Z");
    		float yaw = (float) Main.cfg.getDouble("Spawn" + Spawn + ".Yaw");
    		float pitch = (float) Main.cfg.getDouble("Spawn"+ Spawn + ".Pitch");
    		String weltname = Main.cfg.getString("Spawn" + Spawn + ".Weltname");
    		Location loc = new Location(Bukkit.getWorld(weltname), x, y, z);
    		loc.setYaw((float) yaw);
    		
    		loc.setPitch((float) pitch);
    		p.teleport(loc);
    		p.playSound(p.getLocation(), Sound.BLAZE_HIT, 25.0f, 22.0f);
    		p.setPlayerWeather(WeatherType.CLEAR);

    		  PacketManager.sendTitle(p, "§e§l" + Spawn, "", 10, 10, 10);

    		p.removePotionEffect(PotionEffectType.BLINDNESS);
            }, 25L);

		
		return;
		
	}
	public static void teleportToSpawn(Player p){
		String Spawn = "Spawn";
		int x = Main.cfg.getInt("Spawn" + Spawn + ".X");
		int y = Main.cfg.getInt("Spawn" + Spawn + ".Y");
		int z = Main.cfg.getInt("Spawn" + Spawn + ".Z");
		double yaw = Main.cfg.getDouble("Spawn" + Spawn + ".Yaw");
		double pitch = Main.cfg.getDouble("Spawn" + Spawn + ".Pitch");
		String weltname = Main.cfg.getString("Spawn" + Spawn + ".Weltname");
		Location loc = new Location(Bukkit.getWorld(weltname), x, y, z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		p.teleport(loc);
	}

}
