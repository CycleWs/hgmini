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

public class Snail implements Listener {
    public static ArrayList<Player> snailList = new ArrayList<>();
    int value = 17;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player damage = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(damager) && KitSelector.kitMap.get(damager) == value) && Main.inGame){
                if(new Random().nextInt(3) == 1)
                    damage.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,120,0),true);
                }
            }
        }

    public static void getKitDescription(Player p){
        p.sendMessage("§cDeixe seus inimigos lentos quando estiverem");
        p.sendMessage("§cem combate");
    }
}
