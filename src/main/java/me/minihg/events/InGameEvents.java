package me.minihg.events;

import me.minihg.Main;
import me.minihg.stages.InGameStage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InGameEvents implements Listener{
    public static boolean feastProtection;
    private Map<String, Long> timeout = new HashMap<>();
    public static String NEW_WINNER = "";
    public static Player[] arrayOfPlayer;

    public InGameEvents(){

    }
    public static void cakeTeleport() {
        int p = (arrayOfPlayer = Main.playersOnline.toArray(new Player[0])).length;
        Player player = null;
        for(int i = 0; i < p; ++i) {
            player = arrayOfPlayer[i];
        }
        InGameStage.cancel();
        ItemStack MLG = new ItemStack(Material.WATER_BUCKET);
        Location winnerLoc = new Location(player.getWorld(), 0.0D, 170.0, 0.0D);
        Main.ending = true;

        for (int i = 0; i < 1; ++i) {
            for (int x = -3; x < 3; ++x) {
                for (int z = -3; z < 3; ++z) {
                    Block b = winnerLoc.clone().add(x, -2.0, z).getBlock();
                    Block b2 = winnerLoc.clone().add(x, -1.0, z).getBlock();
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
                if(Main.playersOnline.size() == 1){
                    Bukkit.broadcastMessage("§e O jogo acabou!");
                    cakeTeleport();
                }
            },19L);
        }
    }

}
