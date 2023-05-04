package me.minihg.stages;

import me.minihg.Main;
import me.minihg.events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PreGame {

    private static Integer shed_id = null;

    public PreGame(){
        if(Main.PreGame){
            shed_id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () ->{
                Player[] arrayOfPlayer;
                if(Main.StartTime > 0){
                    if((Main.StartTime >= 0)){
                        int g = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                        for(int i = 0; i < g ; i++){
                            Player p = arrayOfPlayer[i];
                            p.setHealth(20.0D);
                            p.setFoodLevel(20);
                            p.setExp(0.0F);
                            p.setRemainingAir(20);
                        }

                        if(Main.PreGame && Main.StartTime == 300){
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 05:00");
                        }
                        if(Main.PreGame && Main.StartTime == 240){
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 04:00");
                        }
                        if(Main.PreGame && Main.StartTime == 180) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 03:00");
                        }
                        if(Main.PreGame && Main.StartTime == 120) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 02:00");
                        }
                        if(Main.PreGame && Main.StartTime == 60) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 01:00");
                        }
                        if(Main.PreGame && Main.StartTime == 30) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 00:30");
                        }
                        if(Main.PreGame && Main.StartTime == 10) {
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 00:10");
                            Location spawn = new Location(Bukkit.getWorld("world"), 0, 80, 0,0,0);
                        }
                        if(Main.PreGame && Main.StartTime < 10 && Main.StartTime > 0){
                            Bukkit.broadcastMessage("§cA partida vai iniciar em: 00:0"+ Main.StartTime);
                            int f = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                            for(int i = 0; i < f; ++i) {
                                Player p = arrayOfPlayer[i];
                                p.playSound(p.getLocation(), Sound.CLICK, 8, 1);
                            }
                        }
                    }
                    Main.StartTime -= 1;
                    Bukkit.broadcastMessage(String.valueOf(Main.StartTime));
                }else if(Main.PreGame && Main.playersOnline.size() < Main.MinPlayers){
                    Bukkit.broadcastMessage("§cJogadores insuficientes!");
                    Main.StartTime = 300;
                }else{
                    Main.startMatch();
                    int f = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
                    for(int i = 0; i < f; ++i) {
                        Player p = arrayOfPlayer[i];
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 6, 1);
                    }
                }
            },0L,20L);
        }
    }

    public static void cancel(){
        if(shed_id != null){
            Bukkit.getServer().getScheduler().cancelTask(shed_id);
            shed_id = null;
        }
    }
}
