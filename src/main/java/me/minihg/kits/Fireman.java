package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.ArrayList;

public class Fireman implements Listener{

    public static ArrayList<Player> firemanList = new ArrayList<>();

    @EventHandler
    public void onFireman(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if (e.getEntity() instanceof Player && ((e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)))){
            e.setCancelled(true);
        }
    }

}
