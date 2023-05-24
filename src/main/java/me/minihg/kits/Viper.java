package me.minihg.kits;

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
        Player damager = (Player) e.getDamager();
        Player damage = (Player) e.getEntity();
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                if(new Random().nextInt(3) == 1)
                    damage.addPotionEffect(new PotionEffect(PotionEffectType.POISON,120,0),true);
            }
        }
    public static List<String> getKitDescription() {
        List<String> list = new ArrayList<>();
        list.add("§cDeixe seus inimigos envenenados quando");
        list.add("§cestiverem em combate");
        return list;
    }
}
