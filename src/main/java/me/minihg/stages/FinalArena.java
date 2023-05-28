//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.minihg.stages;

import java.util.ArrayList;
import java.util.Random;
import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class FinalArena {
    public static Block mainBlock = null;
    private static Integer radius = 20;
    public static Boolean spawned = false;
    private static ArrayList<Location> AFlocks = new ArrayList();

    public FinalArena() {
    }

    public static void checkFinalArena() {
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

        for(int i = 0; i < j; ++i) {
            Player Participando = arrayOfPlayer[i];
            Location a;
            if (Participando.getLocation().getY() > 50.0 && Main.finalArena) {
                a = new Location(Bukkit.getWorld("world"), 0.0, 5.0, 0.0);
                Participando.teleport(a);
            }

            if (Participando.getLocation().getX() >= 35.0 || Participando.getLocation().getX() <= -35.0 && Main.finalArena) {
                a = new Location(Bukkit.getWorld("world"), 0.0, 5.0, 0.0);
                Participando.teleport(a);
            }

            if (Participando.getLocation().getZ() >= 35.0 || Participando.getLocation().getZ() <= -35.0 && Main.finalArena) {
                a = new Location(Bukkit.getWorld("world"), 0.0, 5.0, 0.0);
                Participando.teleport(a);
            }
        }

    }

    public static void finalArenaAnnouncement() {
        if (mainBlock == null) {
            do {
                do {
                    int min = -20;
                    int max = 20;
                    Random r = new Random();
                    int x = r.nextInt(max - min + 1) + max;
                    int z = r.nextInt(max - min + 1) + max;
                    ((World)Bukkit.getServer().getWorlds().get(0)).getHighestBlockAt(x, z);
                    Block loc = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(0, 0, 0);
                    mainBlock = loc;
                } while(mainBlock.getType() == Material.LOG);
            } while(mainBlock.getType() == Material.LEAVES);

            mainBlock.setType(Material.GRASS);
            AFlocks.add(mainBlock.getLocation());
            removeAbove(mainBlock);
            createFinalArena(Material.BEDROCK);
            spawned = true;
        }

    }

    private static void createFinalArena(Material m) {
        Location loc = mainBlock.getLocation();
        Integer r = radius;

        for(double x = (double)(-r); x <= (double)r; ++x) {
            for(double z = (double)(-r); z <= (double)r; ++z) {
                Location l = new Location((World)Bukkit.getServer().getWorlds().get(0), loc.getX() + x, loc.getY(), loc.getZ() + z);
                if (l.distance(loc) <= (double)r && l.getBlock().getType() != Material.NETHERRACK) {
                    removeAbove(l.getBlock());
                    l.getBlock().setType(m);
                    AFlocks.add(l);
                }
            }
        }

    }

    public static void removeAbove(Block block) {
        Location loc = block.getLocation();
        loc.setY(loc.getY() + 0.5);

        for(Block newBlock = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(loc); loc.getY() < (double)((World)Bukkit.getServer().getWorlds().get(0)).getMaxHeight(); newBlock = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(loc)) {
            newBlock.setType(Material.AIR);
            loc.setY(loc.getY() + 0.5);
        }

    }
}
