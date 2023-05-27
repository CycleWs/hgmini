package me.minihg.stages;

import me.minihg.Main;
import me.minihg.events.InGameEvents;
import me.minihg.feastminifeast.FeastManager;
import me.minihg.feastminifeast.MiniFeast;
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
            ++Main.inGameTime;
            Main.updateScore();
            //verifyWinner();
<<<<<<< Updated upstream
            if(Main.InGameTime == 3){
                FeastManager.feastAnnouncement(0);
                MiniFeast.miniFeastAnnouncemnt();
=======
            if(Main.finalArena){
                FinalArena.checkFinalArena();
            }if(Main.inGameTime == 20){
                MiniFeast.miniFeastAnnouncement();
            }if(Main.inGameTime == 50){
                MiniFeast.miniFeastAnnouncement();
            }if(Main.inGameTime == 80){
                MiniFeast.miniFeastAnnouncement();
            }
            if(Main.inGameTime == 540){
                FeastManager.feastAnnouncement(0);
>>>>>>> Stashed changes
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§cO Feast irá spawnar em 3 minutos em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }if(Main.inGameTime == 600){
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§cO Feast irá spawnar em 2 minutos em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }if(Main.inGameTime == 660){
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§cO Feast irá spawnar em 1 minuto em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }if(Main.inGameTime == 690){
                int X = (int) FeastManager.mainBlock.getLocation().getX();
                int Y = (int) FeastManager.mainBlock.getLocation().getY();
                int Z = (int) FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§cO Feast irá spawnar em 30 segundos em X:§e " + X + " §bY:§e " + Y + " §bZ:§e " + Z);
            }
<<<<<<< Updated upstream
            if(Main.InGameTime == 20){
                FeastManager.spawnFeast();
=======
            if(Main.inGameTime == 720){
                Bukkit.broadcastMessage("§cO Feast Spawnou!");
            }
            if(Main.inGameTime == 1200){
                World world = Bukkit.getWorld("world");
                List<Entity> entList = world.getEntities();
                for(Entity current : entList){
                    if (current.getType() == EntityType.DROPPED_ITEM){
                        current.remove();
                    }
                }
                Main.finalArena = true;
                Main.inGame = false;
                FinalArena.finalArenaAnnouncement();
                for(int i = 0; i < p; ++i) {
                    Player playersList = arrayOfPlayer[i];
                    Location arenaFinal = new Location(Bukkit.getWorld("world"), 0, 4, 0, 0, 0);
                    playersList.teleport(arenaFinal);
                }
>>>>>>> Stashed changes
            }
            Bukkit.broadcastMessage(String.valueOf(Main.InGameTime));

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
