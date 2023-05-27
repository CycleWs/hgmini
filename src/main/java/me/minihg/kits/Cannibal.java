package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cannibal implements Listener {
    int value = 3;
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player damage = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(damager) && KitSelector.kitMap.get(damager) == value) && Main.inGame){
                if(new Random().nextInt(3) == 1)damage.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,120,0),true);
                if(damager.getFoodLevel() < 20){
                    damager.setFoodLevel(damager.getFoodLevel() + 2);
                    if(damage.getFoodLevel() > 1)
                        damage.setFoodLevel(damage.getFoodLevel() - 2);
                }
            }
        }
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit CANNIBAL");
        p.sendMessage("§aRegenere sua fome ao hitar seus inimigos e os deixe com fome");
    }
}
