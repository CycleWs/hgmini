package me.minihg.stages;

import me.minihg.Main;
import me.minihg.events.InGameEvents;
import me.minihg.feastminifeast.FeastManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

import static me.minihg.events.InGameEvents.winCheck;
import static me.minihg.feastminifeast.FeastManager.*;

public class InGameStage {
    public static Player[] arrayOfPlayer;
    private static Integer shed_id = null;


    public InGameStage(){
    }

    public static void verifyWinner(){
        InGameEvents.winCheck();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
        }, 600L);
    }

    public static void Start(int counter){
        int p = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
        shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () ->{
            ++Main.InGameTime;
            //verifyWinner();
            if(Main.InGameTime == 10){
                FeastManager.feastAnnouncement(0);
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§bO Feast nasceu em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }
            if(Main.InGameTime == 20){
                FeastManager.spawnFeast();
            }

        },0L,20L);
    }

    public static void cancel(){
        if(shed_id != null){
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }
        Main.inGame = false;
    }
}
