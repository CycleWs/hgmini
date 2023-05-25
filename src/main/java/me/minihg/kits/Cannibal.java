package me.minihg.kits;

import me.minihg.Main;
import me.minihg.stages.InGameStage;
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
    protected static final Random r = new Random();
    public static ArrayList<Player> cannibalList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player damage = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(damage) && KitSelector.kitMap.containsValue(3)) && Main.inGame){
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
        p.sendMessage("§cRegenere sua fome ao hitar seus inimigos e");
        p.sendMessage("§cos deixe com fome");
    }
}
