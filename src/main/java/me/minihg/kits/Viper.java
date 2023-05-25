package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Viper implements Listener {
    public static ArrayList<Player> viperList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)){
            //the sender is not a Player
            return;
        }else{
            Player damager = (Player) e.getDamager();
            Player damage = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(damager) && KitSelector.kitMap.containsValue(24)) && Main.inGame){
                if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                    if(new Random().nextInt(3) == 1)
                        damage.addPotionEffect(new PotionEffect(PotionEffectType.POISON,120,0),true);
                }
            }
        }

    }
    public static void getKitDescription(Player p) {
        p.sendMessage("§cDeixe seus inimigos envenenados quando");
    }
}
