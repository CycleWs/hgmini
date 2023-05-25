package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class Boxer implements Listener {
    public static ArrayList<Player> boxerList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player)) {
            Player p = (Player) event.getDamager();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(1))){
                if ((p.getItemInHand() == null))
                    event.setDamage(5);
            }
        }
        if ((event.getEntity() instanceof Player)) {
            Player damaged = (Player) event.getEntity();
            if((KitSelector.kitMap.containsKey(damaged) && KitSelector.kitMap.containsValue(1))){
                if ((event.getDamage() > 1))
                    event.setDamage(event.getDamage() - 1);
            }
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§cReceba meio coração de dano a menos");
        p.sendMessage("§cDê meio coração de dano a mais");
    }
}
