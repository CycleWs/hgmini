package me.minihg.kits;

import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Miner implements Listener {
    public static ArrayList<Player> minerList = new ArrayList<>();

    public static ItemStack miner;
    public static boolean minerKit(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        miner = new ItensConfig(Material.STONE_PICKAXE, 1, (short) 0)
                .setName("§aMiner")
                .setEnchant(Enchantment.DIG_SPEED, 0)
                .setUnbreakable()
                .getItemStack();

        p.getInventory().clear();
        p.getInventory().addItem(miner);
        p.getInventory().addItem(Bussola);
        return true;
    }

//    public static void breakOres(Block ores) {
//        if (ores.getType() != Material.IRON_ORE && ores.getType() != Material.COAL_ORE) return;
//        ores.breakNaturally();
//        for (BlockFace face : BlockFace.values())
//            breakOres(ores.getRelative(face));
//
//    }
//
//    @EventHandler
//    public void onBreak(BlockBreakEvent e) {
//        Player p = e.getPlayer();
//        Block b = e.getBlock();
//        ItemStack item = p.getInventory().getItemInHand();
//        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aMiner") && (b.getType()
//                == Material.IRON_ORE || b.getType() == Material.COAL_ORE) && minerList.contains(p)) {
//            breakOres(b);
//        }
//    }

    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cQuebre um minério e receba todos");
        list.add("§cos outros proximos a ele");
        return list;
    }
}
