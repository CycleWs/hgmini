package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Stomper implements Listener {
    int value = 18;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setDamage(4);
                    for (Entity e : p.getNearbyEntities(3, 3, 3)) {
                        if (e instanceof LivingEntity)
                            ((LivingEntity) e).damage(e instanceof Player
                                    ? (((Player) e).isSneaking() ? (((Player) e).isBlocking() ? 2 : 4) : event.getDamage())
                                    : event.getDamage(), p);
                        event.setDamage(event.getDamage() > 4 ? 4 : event.getDamage());
                    }
                }else{
                    return;
                }
            }
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit STOMPER");
        p.sendMessage("§aReceba apenas 2 corações de dano de queda de qualquer altura e transfira todo o dano de queda para os inimigos próximos ");
    }
}
