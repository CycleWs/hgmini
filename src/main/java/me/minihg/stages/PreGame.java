package me.minihg.stages;

import me.minihg.Main;
import me.minihg.kits.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PreGame {
    private static Integer shed_id = null;

    public PreGame() {
        if (Main.preGame) {
            shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                Player[] arrayOfPlayer;
                int g;
                int f;
                Player p;
                if (Main.startTime > 0) {
                    if (Main.startTime >= 0) {
                        g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

                        for(f = 0; f < g; ++f) {
                            p = arrayOfPlayer[f];
                            p.setHealth(20.0);
                            p.setFoodLevel(20);
                            p.setExp(0.0F);
                            p.setRemainingAir(20);
                        }

                        if (Main.preGame && Main.startTime == 300) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 05:00");
                        }

                        if (Main.preGame && Main.startTime == 240) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 04:00");
                        }

                        if (Main.preGame && Main.startTime == 180) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 03:00");
                        }

                        if (Main.preGame && Main.startTime == 120) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 02:00");
                        }

                        if (Main.preGame && Main.startTime == 60) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 01:00");
                        }

                        if (Main.preGame && Main.startTime == 30) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 00:30");
                        }

                        if (Main.preGame && Main.startTime == 10) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 00:10");
                            new Location(Bukkit.getWorld("world"), 0.0, 80.0, 0.0, 0.0F, 0.0F);
                        }

                        if (Main.preGame && Main.startTime < 10 && Main.startTime > 0) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em 00:0" + Main.startTime);
                            f = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

                            for(int i = 0; i < f; ++i) {
                                Player px = arrayOfPlayer[i];
                                px.playSound(px.getLocation(), Sound.CLICK, 8.0F, 1.0F);
                            }
                        }
                    }

                    Main.startTime = Main.startTime - 1;
                    Main.updateScore();
                } else if (Main.preGame && Main.playersOnline.size() < Main.minPlayers) {
                    Bukkit.broadcastMessage("§cJogadores insuficientes!");
                    Main.startTime = 300;
                } else {
                    Main.startMatch();
                    KitSelector.kitSelectorRandom();
                    g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

                    for(f = 0; f < g; ++f) {
                        p = arrayOfPlayer[f];
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 6.0F, 1.0F);
                    }
                }

            }, 0L, 20L);
        }

    }

    public static void cancel() {
        if (shed_id != null) {
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }

    }
}