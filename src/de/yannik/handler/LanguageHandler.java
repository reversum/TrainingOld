package de.yannik.handler;

import org.bukkit.entity.Player;

import de.yannik.main.Main;

public class LanguageHandler {
	
	public static String compass(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return "§8▶ §6§lAlle Spiele §8【§a§l➟ §7§lRechtsklick§8】";
		}
		if (data.playerLanguage == 1) {
			return "§8▶ §6§lGames §8【§a§l➟ §7§lRightclick§8】";
		} else {
			return "§8▶ §6§lAlle Spiele §8【§a§l➟ §7§lRechtsklick§8】";
		}		
	}
	public static String spectate(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return "§8▶ §b§lZuschauen §8【§a§l➟ §7§lRechtsklick§8】";
		}
		if (data.playerLanguage == 1) {
			return "§8▶ §b§lSpectate §8【§a§l➟ §7§lRightclick§8】";
		} else {
			return "§8▶ §b§lZuschauen §8【§a§l➟ §7§lRechtsklick§8】";
		}		
	}
	public static String challange(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return "§8▶ §c§lHerausfordern §8【§a§l➟ §7§lSchlagen§8】";
		}
		if (data.playerLanguage == 1) {
			return "§8▶ §c§lChallange §8【§a§l➟ §7§lHit a player§8】";
		} else {
			return "§8▶ §c§lHerausfordern §8【§a§l➟ §7§lSchlagen§8】";
		}		
	}
	public static String leave(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return "§8▶ §4§lVerlassen §8【§a§l➟ §7§lRechtsklick§8】";
		}
		if (data.playerLanguage == 1) {
			return "§8▶ §4§lLeave game §8【§a§l➟ §7§lRight click§8】";
		} else {
			return "§8▶ §4§lVerlassen §8【§a§l➟ §7§lRechtsklick§8】";
		}		
	}
	public static String rank(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  "Rang";
		}
		if (data.playerLanguage == 1) {
			return  "Rank";
		} else {
			return  "Rang";
		}		
	}
	public static String getSettings(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  "§8Einstellungen";
		}
		if (data.playerLanguage == 1) {
			return  "§8Settings";
		} else {
			return  "§8Einstellungen";
		}		
	}
	public static String getCompassTitle(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  "§8Wähle ein Spiel";
		}
		if (data.playerLanguage == 1) {
			return  "§8Choose a gamemode";
		} else {
			return  "§8Wähle ein Spiel";
		}		
	}
	public static String addQueue(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§7Du wurdest zur §eWarteschlange §7hinzugefügt";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§7You was added to the §eQueue§8.";
		} else {
			return  Main.prefix+  "§7Du wurdest zur §eWarteschlange §7hinzugefügt";
		}		
	}
	
	public static String removeQueue(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§7Du wurdest von der §cWarteschlange §7entfernt";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§7You was removed from the §cQueue§8.";
		} else {
			return  Main.prefix+  "§7Du wurdest von der §cWarteschlange §7entfernt";
		}		
	}
	public static String geheIndieQueue(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§cDu musst in die Warteschlange gehen!";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§cYou have to join the queue!";
		} else {
			return  Main.prefix+  "§cDu musst in die Warteschlange gehen!";
		}		
	}
	
	
	public static String datasnychron(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§7Du kannst deine Inventar-Sortierung mit /settings ändern";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§7You can change your inventory slots with /settings";
		} else {
			return  Main.prefix+  "§7Du kannst deine Inventar-Sortierung mit /settings ändern";
		}		
	}	public static String saved(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§aEinstellungen wurden gespeichert!";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§aSettings saved!";
		} else {
			return  Main.prefix+  "§aEinstellungen wurden gespeichert!";
		}		
	}
	public static String dontput(Player p) {
		PlayerHandler data = PlayerManager.getNewPlayer(p);
		if (data == null) {
			return  Main.prefix+  "§cLege keine Items in dein Inventar!";
		}
		if (data.playerLanguage == 1) {
			return  Main.prefix+  "§cDon´t put any items to your inventory!";
		} else {
			return  Main.prefix+  "§cLege keine Items in dein Inventar!";
		}		
	}
}
