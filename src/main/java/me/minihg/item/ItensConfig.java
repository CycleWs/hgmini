package me.minihg.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItensConfig {
    private final ItemStack itemstack;
    private final ItemMeta itemmeta;

    public ItensConfig(Material m, int quantity, short data){
        itemstack = new ItemStack(m, quantity, data);
        itemmeta = itemstack.getItemMeta();
    }

    public ItensConfig setName(String name){
        itemmeta.setDisplayName(name);
        itemstack.setItemMeta(itemmeta);
        return this;
    }

    public ItensConfig setLore(List<String> lore){
        itemmeta.setLore(lore);
        itemstack.setItemMeta(itemmeta);
        return this;
    }

    public ItensConfig setEnchant(Enchantment enchant, int level) {
        itemmeta.addEnchant(enchant, level, false);
        itemstack.setItemMeta(itemmeta);
        return this;
    }

    public ItensConfig setUnbreakable(){
        itemmeta.spigot().setUnbreakable(true);
        itemstack.setItemMeta(itemmeta);
        return this;
    }

    public ItensConfig hideEnchant(){
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemstack.setItemMeta(itemmeta);
        return this;
    }

    public ItemStack getItemStack(){
        return itemstack;
    }

}
