package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class Boxer implements Listener {
    public static ArrayList<Player> boxerList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            if(damager.getItemInHand() == null)
                e.setDamage(5);
        }
        if(e.getEntity() instanceof Player){
            if(e.getDamage() > 1)
                e.setDamage(e.getDamage() - 1);
        }
    }
}
