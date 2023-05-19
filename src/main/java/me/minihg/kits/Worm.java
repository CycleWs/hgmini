package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
        boolean act = e.getAction().equals(Action.LEFT_CLICK_BLOCK);
        if((act && e.getClickedBlock().getType() == Material.DIRT) && wormList.contains(p)){
            e.getClickedBlock().breakNaturally();
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,  100,1));
        }

    }
    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            Material blockFall = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
            if(e.getEntity() instanceof Player && (e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && blockFall == Material.DIRT) && wormList.contains(p)){
                e.setCancelled(true);
                }
            }
        }

}
