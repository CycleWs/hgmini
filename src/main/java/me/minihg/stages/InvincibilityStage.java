package me.minihg.stages;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class InvincibilityStage {

    private static Integer shed_id;

    public InvincibilityStage(){
        shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), ()->{
            Player [] arrayOfPlayer;

            if(Main.InvincibilityTime > 0 && Main.Invincibility){
                if(Main.InvincibilityTime == 60 && Main.Invincibility){
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 01:00");
                }else if(Main.InvincibilityTime == 30 && Main.Invincibility){
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:30");
                }else if(Main.InvincibilityTime == 10 && Main.Invincibility){
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:10");
                }else if((Main.InvincibilityTime <= 5 && Main.InvincibilityTime > 0) && Main.Invincibility){
                    Bukkit.broadcastMessage("§cA invencibilidade acaba em 00:0"+Main.InvincibilityTime);
                    int g = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                    for(int i = 0; i < g; ++i){
                        Player p = arrayOfPlayer[i];
                        p.playSound(p.getLocation(), Sound.CLICK, 8, 1);
                    }
                }if(Main.InvincibilityTime == 0 ){
                    Bukkit.broadcastMessage("§A invencibilidade acabou!");
                    int g = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                    for(int i = 0; i < g; ++i){
                        Player p = arrayOfPlayer[i];
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 1);
                    }
                }
                Main.InvincibilityTime -= 1;
            }else{
                Main.finishInvencibility();
            }
            Bukkit.broadcastMessage(String.valueOf(Main.InvincibilityTime));
        },0L,20L);
    }

    public static void cancel(){
        if(shed_id != null){
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }
    }
}
