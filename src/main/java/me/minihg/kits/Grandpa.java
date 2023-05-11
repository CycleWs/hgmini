package me.minihg.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Grandpa implements Listener {
    public static ItemStack stick;

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getItemDrop().equals(Material.STICK)){
            e.setCancelled(true);
        }
    }
}
