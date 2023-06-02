package me.minihg.kits;

import java.util.ArrayList;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Lumberjack implements Listener {
    public static ArrayList<Player> lumberjackList = new ArrayList();
    public static ItemStack lumberjack;
    int value = 11;

    public Lumberjack() {
    }

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        lumberjack = (new ItensConfig(Material.WOOD_AXE, 1, (short)0)).setName("§aLumberJack").setUnbreakable().getItemStack();
        UndroppableItens.undroppableItens.add(lumberjack);
        p.getInventory().addItem(new ItemStack[]{lumberjack});
    }

    public static void breakTree(Block tree) {
        if (tree.getType() == Material.LOG || tree.getType() == Material.LOG_2) {
            tree.breakNaturally();
            BlockFace[] var1 = BlockFace.values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                BlockFace face = var1[var3];
                breakTree(tree.getRelative(face));
            }

        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();
        Block b = e.getBlock();
        if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && item.getType() == lumberjack.getType() && b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
            breakTree(b);
        }
    }

    @EventHandler
    public void onDeathDrops(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            e.getDrops().remove(lumberjack);
        }
    }
    @EventHandler
    public void deleteItemKit(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Inventory invP = p.getInventory();
        if(Main.finalArena){
            invP.remove(lumberjack);
        }
    }
    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit LUMBERJACK");
        p.sendMessage("§aQuebre uma madeira e derrube a arvore INTEIRA");
    }
}