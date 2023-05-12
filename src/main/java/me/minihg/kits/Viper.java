package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

public class Viper implements Listener {
    protected static final Random r = new Random();
    public static ArrayList<Player> viperList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        Player damager = (Player) e.getDamager();
        Player damage = (Player) e.getEntity();
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                if (!damage.getLastDamageCause().getCause().equals(damager))
                    return;
                if (r.nextInt(1) == 1) {
                    if (damage.hasPotionEffect(PotionEffectType.POISON))
                        damage.removePotionEffect(PotionEffectType.POISON);
                    damage.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 0));
                }
            }
        }
}
