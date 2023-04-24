package me.minihg.stages;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.minihg.events.InGameEvents.winCheck;

public class InGameStage {
    public static Player[] arrayOfPlayer;
    private static Integer shed_id = null;

    public InGameStage(){
    }

    public static void verifyWinner(){
        winCheck();
    }

    public static void Start(int counter){
        int p = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
        shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () ->{
            verifyWinner();
        },0L,20L);
    }
}
