package me.minihg.kits;

import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Lumberjack implements Listener {
    public static ArrayList<Player> lumberjackList = new ArrayList<>();
    public static ItemStack lumberjack;
    int value = 11;

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        lumberjack = new ItensConfig(Material.WOOD_AXE, 1, (short) 0)
                .setName("§aLumberJack")
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(lumberjack);
        p.getInventory().clear();
        p.getInventory().addItem(lumberjack);
        p.getInventory().addItem(Bussola);
    }

    public static void breakTree(Block tree) {
        if (tree.getType() != Material.LOG && tree.getType() != Material.LOG_2) return;
        tree.breakNaturally();
        for (BlockFace face : BlockFace.values())
            breakTree(tree.getRelative(face));

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();
        Block b = e.getBlock();

        if ((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                && item.getType() == lumberjack.getType()
                && b.getType()== Material.LOG
                || b.getType() == Material.LOG_2) {
            breakTree(b);
        }
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§aQuebre uma madeira e derrube a arvore INTEIRA");
    }
}