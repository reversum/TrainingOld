package de.yannik.utils;

import org.bukkit.inventory.*;
import com.google.common.base.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ItemManager
{
    public static ItemStack createHead(final String owner, final ArrayList<String> lore, final String name) {
        final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setOwner(owner);
        sm.setDisplayName(name);
        sm.setLore((List)lore);
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
    
    public static ItemStack createHeadA(final String skullData, final String name, final int amount, final String... lore) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
        final String[] args = { skullData };
        i = Bukkit.getUnsafe().modifyItemStack(i, Joiner.on(' ').join((Iterable)Arrays.asList(args).subList(0, args.length)));
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setDisplayName(name);
        sm.setLore((List)Arrays.asList(lore));
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
    
    public static ItemStack createHeadB(final String skullData, final String name) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final String[] args = { skullData };
        i = Bukkit.getUnsafe().modifyItemStack(i, Joiner.on(' ').join((Iterable)Arrays.asList(args).subList(0, args.length)));
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setDisplayName(name);
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
    
    public static ItemStack createHeadBackButton() {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final String backHead = "{display:{Name:\"Stone Arrow Left\"},SkullOwner:{Id:\"2fad8146-186b-4c9c-8c62-7d5ccb083faa\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmIwZjZlOGFmNDZhYzZmYWY4ODkxNDE5MWFiNjZmMjYxZDY3MjZhNzk5OWM2MzdjZjJlNDE1OWZlMWZjNDc3In19fQ==\"}]}}}";
        final String[] args = { backHead };
        i = Bukkit.getUnsafe().modifyItemStack(i, Joiner.on(' ').join((Iterable)Arrays.asList(args).subList(0, args.length)));
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setDisplayName("§8» §cZur\u00fcck");
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
    
    public static ItemStack CreateItemwithID(final int id, final int subid, final int amount, final String DisplayName, final ArrayList<String> lore) {
        final ItemStack is = new ItemStack(id, amount, (short)subid);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(DisplayName);
        im.setLore((List)lore);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack create(final Material m, final int subid, final int amount, final String displayName, final ArrayList<String> lore) {
        final ItemStack is = new ItemStack(m, amount, (short)subid);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore((List)lore);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack createItemUnbreak(final Material m, final String displayName) {
        final ItemStack is = new ItemStack(m, 1, (short)0);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayName);
        im.spigot().setUnbreakable(true);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack createArmor(final Material m, final Color c, final String displayName) {
        final ItemStack is = new ItemStack(m, 1, (short)0);
        final LeatherArmorMeta im = (LeatherArmorMeta)is.getItemMeta();
        im.setColor(c);
        im.spigot().setUnbreakable(true);
        im.setDisplayName(displayName);
        is.setItemMeta((ItemMeta)im);
        return is;
    }
    
    @SuppressWarnings("rawtypes")
	public static ItemStack createE(final Material m, final int subid, final int amount, final String DisplayName, final ArrayList<String> lore) {
        final ItemStack is = new ItemStack(m, amount, (short)subid);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(DisplayName);
        im.setLore((List)lore);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack addLore(final Material m, final int subid, final int amount, final String displayName, final String... lore) {
        final ItemStack is = new ItemStack(m, amount, (short)subid);
        final ItemMeta im = is.getItemMeta();
        im.setLore((List)Arrays.asList(lore));
        im.setDisplayName(displayName);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack createHead(final String owner, final ArrayList<String> lore) {
        final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setOwner(owner);
        sm.setLore((List)lore);
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
}
