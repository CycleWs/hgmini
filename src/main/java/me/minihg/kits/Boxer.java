package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.*;

public class Boxer implements Listener {

    int value = 1;
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();
            double damageHand = 5.0D;

            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
                if(p.getInventory().getItemInHand().getType() == AIR){
                    e.setDamage(damageHand);
                    if(isCritical(p)){
                        e.setDamage(damageHand + 1);
                    }
                }
            }
            if((KitSelector.kitMap.containsKey(damaged) && KitSelector.kitMap.get(damaged) == value)){
                if(e.getDamage() > 1){
                    e.setDamage(e.getDamage() - 1);
                    if(isCritical(p)){
                        e.setDamage(e.getDamage() - 1);
                    }
                }
            }
        }

    }
    public static void getKitDescription(Player p){
        p.sendMessage("§aReceba meio coração de dano a menos e dê dano de uma espada de pedra usando a mão");
    }

    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }
}
