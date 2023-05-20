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
import java.util.Random;

public class Reaper implements Listener {
    protected static final Random r = new Random();
    public static ArrayList<Player> reaperList = new ArrayList<>();

    @EventHandler
        public void onDamage(EntityDamageByEntityEvent e){
            Player damager = (Player) e.getDamager();
            Player damage = (Player) e.getEntity();
            if(reaperList.contains(damage) && Main.inGame){
                if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                    if(new Random().nextInt(3) == 1)
                        damage.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,120,0),true);
                }
            }
        }
    }
