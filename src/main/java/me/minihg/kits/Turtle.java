package me.minihg.kits;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class Turtle implements Listener {
    public static ArrayList<Player> turtleList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player)) {
            Player p = (Player) event.getEntity();
            if (p.getLocation().getBlock().getType() == Material.STONE_PLATE
                    || p.getLocation().getBlock().getRelative(BlockFace.NORTH).getType() == Material.STONE_PLATE
                    || p.getLocation().getBlock().getRelative(BlockFace.EAST).getType() == Material.STONE_PLATE
                    || p.getLocation().getBlock().getRelative(BlockFace.SOUTH).getType() == Material.STONE_PLATE
                    || p.getLocation().getBlock().getRelative(BlockFace.WEST).getType() == Material.STONE_PLATE)
                return;
            if ((p.isSneaking()) && (event.getDamage() > 1))
                    event.setDamage(1);
        }
    }

    @EventHandler
    public void onDamage1(EntityDamageByEntityEvent event) {
        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
            Player p = (Player) event.getDamager();
            if (p.isSneaking())
                    event.setCancelled(true);
                }
        }
    }
