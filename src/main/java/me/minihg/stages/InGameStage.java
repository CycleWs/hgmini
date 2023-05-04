package me.minihg.stages;

import me.minihg.Main;
import me.minihg.events.InGameEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.minihg.events.InGameEvents.winCheck;

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
            verifyWinner();
            //Bukkit.broadcastMessage(String.valueOf(Main.InGameTime));
            //Bukkit.broadcastMessage("§AJogadores Online: "+(Main.playersOnline.size()));

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
