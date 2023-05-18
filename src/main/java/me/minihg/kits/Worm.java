package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.map.MinecraftFont;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Worm implements Listener {
    public static ArrayList<Player> wormList = new ArrayList<>();


    @EventHandler
    public void wormEvent(PlayerInteractEvent e ){
        Player p = e.getPlayer();
        boolean act =e.getAction().equals(Action.LEFT_CLICK_BLOCK);
        if(act && e.getClickedBlock().getType() == Material.DIRT ){
            e.getClickedBlock().breakNaturally();
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,  100,1));
        }

    }
//    @EventHandler
//    public void onFall(EntityDamageEvent e){
//        Player p = (Player) e.getEntity();
////        Block block = p.getLocation().getBlock();
////        Location loc =block.getLocation();
//        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)
//                && p.getLocation().getBlock().getType() == Material.DIRT){
//            Bukkit.broadcastMessage(p.getLocation().getBlock().toString());
//        }
//
//        }

}
