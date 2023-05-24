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
            if ((p.getItemInHand() == null))
                event.setDamage(5);
        }
        if ((event.getEntity() instanceof Player)) {
            Player damaged = (Player) event.getEntity();
            if ((event.getDamage() > 1))
                event.setDamage(event.getDamage() - 1);
        }
    }
    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cReceba meio coração de dano a menos");
        list.add("§cDê meio coração de dano a mais");
        return list;
    }
}
