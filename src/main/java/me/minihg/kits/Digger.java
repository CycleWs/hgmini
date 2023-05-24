package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Digger implements Listener {
//    public static ArrayList<Player> diggerList = new ArrayList<>();
//
//    public static ItemStack digger;
//
//    public static boolean Digger(Player p){
//        digger = new ItensConfig(Material.DRAGON_EGG,1,(short) 0).setName("&2Digger").getItemStack();
//        return true;
//    }
//    @EventHandler
//    public void onPlace(BlockPlaceEvent event) {
//        Player p = event.getPlayer();
//        if (event.getBlockPlaced().getType() == Material.DRAGON_EGG) {
//            remove(event.getBlockPlaced());
//            event.getBlockPlaced().setType(Material.AIR);
//            p.sendMessage(ChatColor.RED + "You placed the egg, run!");
//        }
//    }
//
//    public void remove(final Block b) {
//        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
//            public void run() {
//                int dist = (int) Math.ceil(2.0D);
//                for (int y = -1; y >= -5; y--)
//                    for (int x = -dist; x <= dist; x++)
//                        for (int z = -dist; z <= dist; z++)
//                            if (b.getY() + y > 0) {
//                                Block block = b.getWorld().getBlockAt(b.getX() + x, b.getY() + y, b.getZ() + z);
//                                if (block.getType() != Material.BEDROCK)
//                                    block.setType(Material.AIR);
//                            }
//            }
//        }, 60L);
//    }
//
//    public static List<String> getKitDescription(){
//        List<String> list = new ArrayList<>();
//        list.add("§cAbra uma cratera 6x6x6 no chão");
//        return list;
//    }



}
