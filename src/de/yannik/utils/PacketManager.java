package de.yannik.utils;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class PacketManager {
	
	public static void sendActionBar(final Player player, final String message) {
        final CraftPlayer p = (CraftPlayer)player;
        final IChatBaseComponent ibc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        final PacketPlayOutChat packet = new PacketPlayOutChat(ibc, (byte)2);
        p.getHandle().playerConnection.sendPacket((Packet)packet);
    }

	
	public static void sendTitle(final Player player, final String title, final String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        final PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent)null, fadeIn, stay, fadeOut);
        connection.sendPacket((Packet)packetPlayOutTimes);
        final IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        final PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
        connection.sendPacket((Packet)packetPlayOutSubTitle);
        final IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        final PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
        connection.sendPacket((Packet)packetPlayOutTitle);
    }
    
    public static void sendTablistHeaderAndFooter(final Player p, String header, String footer) {
        if (header == null) {
            header = "";
        }
        if (footer == null) {
            footer = "";
        }
        final IChatBaseComponent tabHeader = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
        final IChatBaseComponent tabFooter = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");
        final PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabHeader);
        try {
            final Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFooter);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        finally {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)headerPacket);
        }
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)headerPacket);
    }
    
}
