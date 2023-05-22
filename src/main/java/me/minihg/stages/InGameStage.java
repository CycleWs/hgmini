package me.minihg.stages;

import me.minihg.Main;
import me.minihg.events.InGameEvents;
import me.minihg.feastminifeast.FeastManager;
import me.minihg.feastminifeast.MiniFeast;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

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
            if(Main.finalArena){
                FinalArena.checkFinalArena();
            }
            if(Main.InGameTime == 3){
                FeastManager.feastAnnouncement(0);
                //MiniFeast.miniFeastAnnouncement();
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§bO Feast nasceu em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }
            if(Main.InGameTime == 20){
                World world = Bukkit.getWorld("world");
                List<Entity> entList = world.getEntities();
                for(Entity current : entList){
                    if (current.getType() == EntityType.DROPPED_ITEM){
                        current.remove();
                    }
                }
                Main.finalArena = true;
                FinalArena.finalArenaAnnouncement();
                for(int i = 0; i < p; ++i) {
                    Player playersList = arrayOfPlayer[i];
                    Location arenaFinal = new Location(Bukkit.getWorld("world"), 0, 4, 0, 0, 0);
                    playersList.teleport(arenaFinal);
                }
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
