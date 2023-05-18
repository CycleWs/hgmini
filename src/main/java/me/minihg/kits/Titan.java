package me.minihg.kits;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.UUID;

public class Titan implements Listener {

    public static HashMap<UUID, Double> titanTimer;

    public static void setupCooldown(){
        titanTimer = new HashMap<>();
    }


    @EventHandler
    public void titanEvent(PlayerInteractEvent e){
        Player p =  e.getPlayer();
        double delay = System.currentTimeMillis() + (10*1000);
        if(e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.BEDROCK){
            titanTimer.put(p.getUniqueId(), delay);
            }
        }

    @EventHandler
    public void titanDamage(EntityDamageEvent e){
        Player p = (Player) e.getEntity();
        if(titanTimer.containsKey(p.getUniqueId()) && e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
}
