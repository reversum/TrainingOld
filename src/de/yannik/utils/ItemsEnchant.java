package de.yannik.utils;

import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import java.lang.reflect.*;

public class ItemsEnchant extends EnchantmentWrapper
{
    private static Enchantment glow;
    
    public ItemsEnchant(final int id) {
        super(id);
    }
    
    public boolean canEnchantItem(final ItemStack item) {
        return true;
    }
    
    public boolean conflictsWith(final Enchantment other) {
        return false;
    }
    
    public EnchantmentTarget getItemTarget() {
        return null;
    }
    
    public int getMaxLevel() {
        return 10;
    }
    
    public String getName() {
        return "Glow";
    }
    
    public int getStartLevel() {
        return 1;
    }
    
    public static Enchantment getGlow() {
        if (ItemsEnchant.glow != null) {
            return ItemsEnchant.glow;
        }
        try {
            final Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Enchantment.registerEnchantment(ItemsEnchant.glow = (Enchantment)new ItemsEnchant(255));
        return ItemsEnchant.glow;
    }
    public static void removeGlow(final ItemStack item) {
        item.removeEnchantment(glow);
    }
    public static void addGlow(final ItemStack item) {
        final Enchantment glow = getGlow();
        item.addEnchantment(glow, 1);
    }
}
