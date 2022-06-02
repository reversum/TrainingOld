package de.yannik.handler; 

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.yannik.main.Main;
import de.yannik.utils.PacketScoreboard;


public class RangHandler {
	
	

	 
		private static HashMap<String, String> teams;
	 
		static {
			teams = new HashMap<>();
		}
	 
		public void create(String name, int rank, String prefix, String suffix, String permission) {
			String fullName = rank + "_" + name;
			Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
			Team t = board.getTeam(fullName);
	 
			if (t != null) {
				t.unregister();
			}
	 
			t = board.registerNewTeam(fullName);
	 
			if (prefix != null) {
				t.setPrefix(prefix);
			}
	 
			if (suffix != null) {
				t.setSuffix(suffix);
			}
	 
			teams.put(permission, fullName);
	 
		}
	 
		@SuppressWarnings("deprecation")
		public void addPlayer(Player p) {
			Team t = null;
	 
			for (String perm : teams.keySet()) {
		        final IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
		        if (user == null) {
		            return;
		        }
		        
		    	final IPermissionGroup group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user); 
		    	
				if (perm == null || group.getName().equals(perm)) {
					String currentTeamName = teams.get(perm);
	 
					if (t == null || this.getRank(currentTeamName) < this.getRank(t.getName())) {
						t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(currentTeamName);
					}
	 
				}
			}
	 
			if (t != null) {
				t.addPlayer(p);
			}
		}
	 
		public void update() {
			for (Player players : Bukkit.getOnlinePlayers()) {
				this.removePlayer(players);
				this.addPlayer(players);
			}
		}
	 
		@SuppressWarnings("deprecation")
		public void removePlayer(Player p) {
			for (String teamName : teams.values()) {
				Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
	 
				if (t != null && t.hasPlayer(p)) {
					t.removePlayer(p);
				}
			}
		}
	 
		private int getRank(String teamName) {
			if (!teamName.contains("_")) {
				return -1;
			}
	 
			String[] array = teamName.split("_");
			try {
				int i = Integer.parseInt(array[0]);
				return i;
			} catch (NumberFormatException ex) {
				return -1;
			}
	 
		}
    
    
}
