package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
public class Switcher implements Listener {

    public static ArrayList<Player> switcherList = new ArrayList<>();

    @EventHandler
    public void switcherEvent(EntityDamageByEntityEvent e){
        Entity thrownEntity = e.getEntity();
        if (e.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) e.getDamager();
            if (snowball.getShooter() instanceof Player) {
                Player shooter = (Player) snowball.getShooter();
                Player hitPlayer = (Player) e.getEntity();

                Location shooterLoc = shooter.getLocation();
                Location hitPlayerLoc = hitPlayer.getLocation();
                shooter.teleport(hitPlayerLoc);
                hitPlayer.teleport(shooterLoc);


                e.setCancelled(true);
                shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
            }
        }

    }
}
