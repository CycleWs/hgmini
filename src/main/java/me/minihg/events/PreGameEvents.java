package me.minihg.events;

import me.minihg.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PreGameEvents implements Listener {

    public PreGameEvents(){
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(Main.preGame){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(Main.preGame){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void getItem(PlayerPickupItemEvent e){
        if(Main.preGame){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(Main.preGame || Main.invincibility){
            e.setCancelled(true);
        }
    }
}
