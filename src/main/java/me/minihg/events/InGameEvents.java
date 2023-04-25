package me.minihg.events;

import me.minihg.Main;
import me.minihg.stages.InGameStage;
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
import java.util.UUID;

public class InGameEvents implements Listener {
    public static boolean feastProtection;
    private Map<String, Long> timeout = new HashMap<>();
    public static String NEW_WINNER = "";

    public InGameEvents(){

    }

    public static void cakeTeleport(){
        InGameStage.cancel();
        ItemStack MLG = new ItemStack(Material.WATER_BUCKET);
        Player player = Bukkit.getPlayer(PlayerEvents.uuid);
        Location winnerLoc = new Location(player.getWorld(), 0.0D, 170.0,  0.0D);
        Main.Ending = true;

        for (int i = 0; i < 1; ++i) {
            for (int x = -3; x < 3; ++x) {
                for (int z = -3; z < 3; ++z) {
                    Block b = winnerLoc.clone().add(x, -2.0,  z).getBlock();
                    Block b2 = winnerLoc.clone().add(x, -1.0,  z).getBlock();
                    b.setType(Material.GLASS);
                    b2.setType(Material.CAKE_BLOCK);
                    player.teleport(winnerLoc);
                    player.getInventory().clear();
                    player.getInventory().setItem(4, MLG);
                }
            }
        }
    }

    public static void winCheck(){
        if(Main.inGame){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->{
                if(Main.inGame && Main.playersOnline.size() == 1){
                    InGameEvents.cakeTeleport();
                    Bukkit.broadcastMessage("§e o jogo acabou!");
                }
            },19L);
        }
    }

}
