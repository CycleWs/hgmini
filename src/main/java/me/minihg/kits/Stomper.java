package me.minihg.kits;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class Stomper implements Listener {

    public static ArrayList<Player> stomperList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player p = (Player) event.getEntity();
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL)
            return;
        for (Entity e : p.getNearbyEntities(3, 3, 3)) {
            if (e instanceof LivingEntity)
                ((LivingEntity) e).damage(e instanceof Player
                        ? (((Player) e).isSneaking() ? (((Player) e).isBlocking() ? 6 : 12) : event.getDamage())
                        : event.getDamage(), p);
            event.setDamage(event.getDamage() > 4 ? 4 : event.getDamage());
        }
    }
}