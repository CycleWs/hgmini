package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Achilles implements Listener {

    int value = 0;
    @EventHandler
    public void achillesEvent (EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            double anySword = 2.0D;
            double woodSword = 6.0D;
            Player p = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();

<<<<<<< Updated upstream
            if(achillesList.contains(p) && Main.inGame){
=======
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
>>>>>>> Stashed changes
                if(damager.getInventory().getItemInHand().getType() == Material.WOOD_SWORD||
                        damager.getInventory().getItemInHand().getType() == Material.WOOD_AXE ||
                        damager.getInventory().getItemInHand().getType() == Material.WOOD_HOE ||
                        damager.getInventory().getItemInHand().getType() == Material.WOOD_PICKAXE ||
                        damager.getInventory().getItemInHand().getType() == Material.WOOD_SPADE){
                    e.setDamage(woodSword);
                    if(isCritical(damager)){
                        e.setDamage(woodSword + 1);
                    }
                }
                else{
                    e.setDamage(anySword);
                    if(isCritical(damager)){
                        e.setDamage(anySword + 1);
                    }
                }
            }
        }
    }

<<<<<<< Updated upstream
=======

    public static void getKitDescription(Player p){
        p.sendMessage("§aSeja quase imune a qualquer tipo de espada, porém receberá um dano mortal de itens de madeira");
    }

>>>>>>> Stashed changes
    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }


}
