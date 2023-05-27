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
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Miner implements Listener {
    public static ArrayList<Player> minerList = new ArrayList<>();

    int value = 12;

    public static ItemStack miner;
    public static void getItems(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        miner = new ItensConfig(Material.STONE_PICKAXE, 1, (short) 0)
                .setName("§aMiner")
                .setEnchant(Enchantment.DIG_SPEED, 1)
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(miner);
        p.getInventory().addItem(miner);
    }

    public static void breakOres(Block ores) {
        if (ores.getType() != Material.IRON_ORE && ores.getType() != Material.COAL_ORE) return;
        ores.breakNaturally();
        for (BlockFace face : BlockFace.values())
            breakOres(ores.getRelative(face));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        ItemStack item = p.getInventory().getItemInHand();
        if ((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                && item.getType() == miner.getType()
                && (b.getType() == Material.IRON_ORE
                || b.getType() == Material.COAL_ORE)) {
            breakOres(b);
        }
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit MINER");
        p.sendMessage("§aQuebre um minério e receba todos os outros proximos a ele");
    }
}
