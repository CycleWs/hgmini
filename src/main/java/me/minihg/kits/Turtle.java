package me.minihg.kits;

import me.minihg.Main;
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
    public void onDamage1(EntityDamageByEntityEvent event) {
        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
            Player p = (Player) event.getDamager();
            if(turtleList.contains(p) && Main.inGame){
                if (p.isSneaking()) event.setCancelled(true);
            }
                }
        }
    }
