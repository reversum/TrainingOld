package de.yannik.handler;

import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class PlayerHandler
{
    private Player player;
    int playerLanguage;
    HashMap<String, String> sortierungen;


    public PlayerHandler(final Player player, final int language, final HashMap<String, String> sort) {
        this.player = player;
        this.playerLanguage = language;
        this.sortierungen = sort;

    }
    
  
    
}
