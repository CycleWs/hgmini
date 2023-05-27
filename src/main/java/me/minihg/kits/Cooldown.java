package me.minihg.kits;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    public static HashMap<UUID, Double> cooldowns;


    public static void setupCooldown(){
        cooldowns = new HashMap<>();
    }

    //setCooldown
    public static void setCooldown(Player p, int seconds){
        double delay = System.currentTimeMillis() + (seconds*1000);
        cooldowns.put(p.getUniqueId(), delay);
    }
    //getCooldown
    public static int getCooldown(Player p){
        return Math.toIntExact(Math.round((cooldowns.get(p.getUniqueId()) - System.currentTimeMillis())/1000));
    }

    //checkCooldown
    public static boolean checkCooldown(Player p){
        if(!cooldowns.containsKey(p.getUniqueId()) || cooldowns.get(p.getUniqueId()) <= System.currentTimeMillis()){
            cooldowns.remove(p.getUniqueId());
            return true;
        }
        return false;
    }
}
