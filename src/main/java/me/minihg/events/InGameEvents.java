package me.minihg.events;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InGameEvents implements Listener {
    public static boolean feastProtection;
    private Map<String, Long> timeout = new HashMap<>();
    public static String NEW_WINNER = "";

    public InGameEvents(){

    }

    private static void cakeTeleport(){
        ItemStack MLG = new ItemStack(Material.WATER_BUCKET);
        Iterator var2 = Main.playersOnline.iterator();
        while (var2.hasNext()) {
            String s = (String) var2.next();
            final Player pl1 = Bukkit.getPlayer(s);
            final Location winnerLoc = new Location(pl1.getWorld(), (double) 0.0D, 170.0, (double) 0.0D);

            for (int i = 0; i < 1; ++i) {
                for (int x = -3; x < 3; ++x) {
                    for (int z = -3; z < 3; ++z) {
                        Block b = winnerLoc.clone().add((double) x, -2.0, (double) z).getBlock();
                        Block b2 = winnerLoc.clone().add((double) x, -1.0, (double) z).getBlock();
                        b.setType(Material.GLASS);
                        b2.setType(Material.CAKE_BLOCK);
                        pl1.teleport(winnerLoc);
                        pl1.getInventory().clear();
                        pl1.getInventory().setItem(4, MLG);
                    }
                }
            }
        }
    }

    public static void winCheck(){
        if(Main.inGame){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->{
                if(Main.inGame && Main.playersOnline.size() > 1){
                    cakeTeleport();
                    Bukkit.broadcastMessage("§e o jogo acabou!");
                }
            },19L);
        }
    }

}
