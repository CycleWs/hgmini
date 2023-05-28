package me.minihg.stages;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class InvincibilityStage {
    private static Integer shed_id;

    public InvincibilityStage() {
        shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (Main.invincibilityTime > 0 && Main.invincibility) {
                Player[] arrayOfPlayer;
                int g;
                int i;
                Player p;
                if (Main.invincibilityTime == 60 && Main.invincibility) {
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 01:00");
                } else if (Main.invincibilityTime == 30 && Main.invincibility) {
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:30");
                } else if (Main.invincibilityTime == 10 && Main.invincibility) {
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:10");
                } else if (Main.invincibilityTime <= 5 && Main.invincibilityTime > 0 && Main.invincibility) {
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:0" + Main.invincibilityTime);
                    g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

                    for(i = 0; i < g; ++i) {
                        p = arrayOfPlayer[i];
                        p.playSound(p.getLocation(), Sound.CLICK, 8.0F, 1.0F);
                    }
                }
                if (Main.invincibilityTime == 0) {
                    Bukkit.broadcastMessage("§A invencibilidade acabou!");
                    g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

                    for(i = 0; i < g; ++i) {
                        p = arrayOfPlayer[i];
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10.0F, 1.0F);
                    }
                }
                Main.invincibilityTime -= 1;
                Main.updateScore();
                g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                for(int f = 0; f < g; ++f) {
                    p = arrayOfPlayer[f];
                    p.setHealth(20.0);
                    p.setFoodLevel(20);
                    p.setExp(0.0F);
                    p.setRemainingAir(20);
                }
            } else {
                Main.finishInvencibility();
            }

        }, 0L, 20L);
    }

    public static void cancel() {
        if (shed_id != null) {
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }

    }
}