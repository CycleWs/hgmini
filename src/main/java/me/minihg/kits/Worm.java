package me.minihg.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class Worm implements Listener {
    public static ArrayList<Player> wormList = new ArrayList<>();

@EventHandler
public void onWorm(BlockBreakEvent e){
    Player p = e.getPlayer();
    if(e.getBlock().getType() == Material.DIRT ){
        e.getBlock().breakNaturally();
        }
    }
    @EventHandler
public void onFall(EntityDamageEvent e){
    Player p = (Player) e.getEntity();
    if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)
            && e.getEntity().getLocation().getBlock().getType() == Material.DIRT){
        e.setCancelled(true);
        }
    }
}
