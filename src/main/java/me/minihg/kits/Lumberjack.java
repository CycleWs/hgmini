package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Lumberjack implements Listener {
    public static ArrayList<Player> lumberjackList = new ArrayList<>();
    public static ItemStack lumberjack;

    public static boolean Lumberjack(Player p){
        lumberjack = new ItensConfig(Material.WOOD_AXE,1,(short) 0).setName("&3LumberJack").setUnbreakable()
                .getItemStack();
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if ((event.getBlock().getType() == Material.LOG) && (event.getPlayer().getItemInHand().getType() == Material.WOOD_AXE)) {
            Block b = event.getBlock();
            while (b.getType() == Material.LOG) {
                b.breakNaturally();
                b = b.getRelative(BlockFace.UP);
            }
        }
    }
}
