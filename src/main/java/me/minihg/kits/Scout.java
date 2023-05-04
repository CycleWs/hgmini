package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Scout implements Listener {

    @EventHandler
    public void scoutKit(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action action = e.getAction();
        Material item = p.getInventory().getItemInHand().getType();

        if(((action == Action.RIGHT_CLICK_AIR) || (action == Action.RIGHT_CLICK_BLOCK)) && item == Material.GOLD_NUGGET){
            p.setWalkSpeed(30.0F);
        }
    }
}
