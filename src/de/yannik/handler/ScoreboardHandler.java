package de.yannik.handler; 

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.yannik.main.Main;
import de.yannik.utils.PacketScoreboard;


public class ScoreboardHandler {
	
	



    public static PacketScoreboard packetScoreboard;
    
    public static void setScoreboard(final Player player, String title) {

        (packetScoreboard = new PacketScoreboard(player)).remove();
        packetScoreboard.sendSidebar(title);
     
        
        packetScoreboard.setLine(Integer.valueOf(10), "񙓬-------------------------");
        packetScoreboard.setLine(Integer.valueOf(9), "?8? " + LanguageHandler.rank(player) + "?8: " + getRang(player));
        packetScoreboard.setLine(Integer.valueOf(8), "");
        packetScoreboard.setLine(Integer.valueOf(7), "?8? MLGRush?8: ?6" + 0);
        packetScoreboard.setLine(Integer.valueOf(6), "?8? BedRush?8: ?6" + BedRushManager.getAllPlayerCount());
        packetScoreboard.setLine(Integer.valueOf(5), "?8? FFA?8: ?6" + FFAManager.getAllPlayerCount());
        packetScoreboard.setLine(Integer.valueOf(4), "?8? Sumo?8: ?6" + 0);
        packetScoreboard.setLine(Integer.valueOf(3), "");
        packetScoreboard.setLine(Integer.valueOf(2), "?8? lydoria.eu");
        packetScoreboard.setLine(Integer.valueOf(1), "?8-------------------------");
        
        
  
    }
    
    public static String getRang(Player p) {
        final IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        if (user == null) {
            return "?3Spieler";
        }
        
    	final IPermissionGroup group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user); 
    	

    	if (group.getName().equals("Admin")) {
            return "?4Administrator";
    	} else if (group.getName().equals("SrModerator")) {
            return "Senior-Moderator";
    	} else if (group.getName().equals("Content")) {
            return "Content";
    	} else if (group.getName().equals("Developer")) {
            return "Developer";
    	} else if (group.getName().equals("Moderator")) {
            return "Moderator";
    	} else if (group.getName().equals("Supporter")) {
            return "Supporter";
    	} else if (group.getName().equals("TestSupporter")) {
            return "?9T-Supporter";
    	} else if (group.getName().equals("Builder")) {
            return "Builder";
    	} else if (group.getName().equals("YouTuber")) {
            return "?5YouTuber";
    	} else if (group.getName().equals("PremiumPlus")) {
            return "Premium?6+";

    	} else if (group.getName().equals("Premium")) {
            return "?6Premium";
    	} else {
            return "?3Spieler";

    	}
    }
    
    
}
