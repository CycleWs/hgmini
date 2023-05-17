package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Miner implements Listener {
    public static ArrayList<Player> minerList = new ArrayList<>();

    public static ItemStack miner;

    public static boolean Miner(Player p){
        miner = new ItensConfig(Material.STONE_PICKAXE,1,(short) 0).setName("&2Miner")
                .setEnchant(Enchantment.DIG_SPEED,0).setUnbreakable().getItemStack();
        return true;
    }

    @EventHandler
    public void onMiner(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getItemInHand().equals(Material.STONE_PICKAXE) && (e.getBlock().getType() == Material.IRON_ORE)){
            e.getBlock().breakNaturally();
            for (BlockFace face : BlockFace.values()){
                e.getBlock().breakNaturally();
            }
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getItemDrop().equals(miner)){
            e.setCancelled(true);
        }
    }
}
