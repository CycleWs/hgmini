package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Poseidon implements Listener {
    public static ArrayList<Player> poseidonList = new ArrayList<>();

    @EventHandler
    public void onPoseidon(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Material m = p.getLocation().getBlock().getType();
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED,100,1);
        PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE,100,0);
        if(poseidonList.contains(p) && Main.inGame){
            if((m == Material.STATIONARY_WATER) || (m == Material.WATER)){
                p.addPotionEffect(speed);
                p.addPotionEffect(strength);
            }
        }

    }
}
