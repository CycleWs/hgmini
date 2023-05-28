package me.minihg.stages;

import java.util.Iterator;
import java.util.List;
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

public class InGameStage {
    public static Player[] arrayOfPlayer;
    private static Integer shed_id = null;
    public InGameStage() {
    }

    private static void verifyWinner() {
        InGameEvents.winCheck();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
        }, 600L);
    }

    public static void Start(int counter) {
        int p = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
        shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            Main.inGameTime += 1;
            //verifyWinner();
            Main.updateScore();
            if (Main.finalArena) {
                FinalArena.checkFinalArena();
            }

            if (Main.inGameTime == 400) {
                MiniFeast.miniFeastAnnouncement();
            }
            int X;
            int Y;
            int i;
            if (Main.inGameTime == 540) {
                FeastManager.feastAnnouncement(0);
                X = (int)FeastManager.mainBlock.getLocation().getX();
                Y = (int)FeastManager.mainBlock.getLocation().getY();
                i = (int)FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§aO Feast irá spawnar em 3 minutos em §aX:§r " + X + " §aY:§r " + Y + " §aZ:§r " + i);
            }

            if (Main.inGameTime == 600) {
                X = (int)FeastManager.mainBlock.getLocation().getX();
                Y = (int)FeastManager.mainBlock.getLocation().getY();
                i = (int)FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§aO Feast irá spawnar em 2 minutos em §aX:§r " + X + " §aY:§r " + Y + " §aZ:§r " + i);
            }

            if (Main.inGameTime == 660) {
                X = (int)FeastManager.mainBlock.getLocation().getX();
                Y = (int)FeastManager.mainBlock.getLocation().getY();
                i = (int)FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§aO Feast irá spawnar em 1 minuto em §aX:§r " + X + " §aY:§r " + Y + " §aZ:§r " + i);
            }

            if (Main.inGameTime == 690) {
                X = (int)FeastManager.mainBlock.getLocation().getX();
                Y = (int)FeastManager.mainBlock.getLocation().getY();
                i = (int)FeastManager.mainBlock.getLocation().getZ();
                Bukkit.broadcastMessage("§aO Feast irá spawnar em 30 segundos em §aX:§r " + X + " §aY:§r " + Y + " §aZ:§r " + i);
            }

            if (Main.inGameTime == 720) {
                FeastManager.spawnFeast();
                Bukkit.broadcastMessage("§aO Feast Spawnou!");
            }

            if (Main.inGameTime == 1200) {
                World world = Bukkit.getWorld("world");
                List<Entity> entList = world.getEntities();
                Iterator var8 = entList.iterator();

                while(var8.hasNext()) {
                    Entity current = (Entity)var8.next();
                    if (current.getType() == EntityType.DROPPED_ITEM) {
                        current.remove();
                    }
                }

                Main.finalArena = true;
                Main.inGame = false;
                FinalArena.finalArenaAnnouncement();

                for(i = 0; i < p; ++i) {
                    Player playersList = arrayOfPlayer[i];
                    Location arenaFinal = new Location(Bukkit.getWorld("world"), 0.0, 4.0, 0.0, 0.0F, 0.0F);
                    playersList.teleport(arenaFinal);
                }
            }

        }, 0L, 20L);
    }

    public static void cancel() {
        if (shed_id != null) {
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }
        Main.inGame = false;
    }
}