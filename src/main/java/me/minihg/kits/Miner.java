package me.minihg.kits;

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

public class Miner implements Listener {
    public void MinerPickaxe(){
        ItemStack stick = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta meta = stick.getItemMeta();
        meta.setDisplayName("Miner");
        stick.addEnchantment(Enchantment.DIG_SPEED, 1);
        stick.setDurability(Short.MAX_VALUE);
        stick.setItemMeta(meta);
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
        if (e.getItemDrop().equals(Material.STONE_PICKAXE)){
            e.setCancelled(true);
        }
    }
}
