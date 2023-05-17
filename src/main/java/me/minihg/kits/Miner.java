package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
import java.util.List;

public class Miner implements Listener {
    public static ArrayList<Player> minerList = new ArrayList<>();

    public static ItemStack miner;

    public static boolean Miner(Player p){
        miner = new ItensConfig(Material.STONE_PICKAXE,1,(short) 0).setName("&2Miner")
                .setEnchant(Enchantment.DIG_SPEED,0).setUnbreakable().getItemStack();
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block b = event.getBlock();
        if (isOre(b.getType()) && event.getPlayer().getItemInHand().getType() == Material.STONE_PICKAXE)
            breakAround(b);
    }
	private void breakAround(Block b) {
		List<Block> also = new ArrayList<Block>();
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				for (int z = 0; z < 3; z++) {
					Location loc = new Location(b.getWorld(), b.getX() - 1 + x, b.getY() - 1 + y, b.getZ() - 1 + z);
					if (isOre(loc.getBlock().getType()))
						also.add(loc.getBlock());
				}
		for (Block b2 : also)
			breakAround(b2);
	}

	private boolean isOre(Material mat) {
		return mat == Material.IRON_ORE || mat == Material.COAL_ORE || mat == Material.GOLD_ORE;
	}

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getItemDrop().equals(miner)){
            e.setCancelled(true);
        }
    }
}
