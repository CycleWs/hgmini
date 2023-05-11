package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Grandpa implements Listener {
    public static ArrayList<Player> grandpaList = new ArrayList<>();
    public static ItemStack stick;

    public static boolean Grandpa(Player p){
        stick = new ItensConfig(Material.STICK,1,(short) 0).setName("&2Grandpa")
                .setEnchant(Enchantment.KNOCKBACK,5).getItemStack();
        return true;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getItemDrop().equals(stick)){
            e.setCancelled(true);
        }
    }
}
