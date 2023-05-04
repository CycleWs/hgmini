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

    public static ArrayList<Player> achillesList = new ArrayList<>();
    //FAZER METODO PARA SELECIONAR O KIT
    @EventHandler
    public void achillesEvent (EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            double anySword = 2.0D;
            double woodSword = 6.0D;
            Player p = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            Bukkit.broadcastMessage("§3Achilles p: " + p);
            Bukkit.broadcastMessage("§3Achilles damager: " + damager);


            if(achillesList.contains(p) && Main.inGame){
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

    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }


}
