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
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Switcher implements Listener {

    public static ArrayList<Player> switcherList = new ArrayList<>();

    @EventHandler
    public void switcherEvent(EntityDamageByEntityEvent e){
        Entity thrownEntity = e.getEntity();
        Bukkit.broadcastMessage("FORA DOS DOIS IFS");
        Cooldown.setupCooldown();
        if (e.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) e.getDamager();
            Player shooter = (Player) snowball.getShooter();
            Player hitPlayer = (Player) e.getEntity();
            Bukkit.broadcastMessage("FORA DO IF");

            if (snowball.getShooter() instanceof Player) {
                Bukkit.broadcastMessage("DENTRO DO IF instanceof Player");
                Location shooterLoc = shooter.getLocation();
                Location hitPlayerLoc = hitPlayer.getLocation();
                if(Cooldown.checkCooldown(shooter)){
                    Bukkit.broadcastMessage("§aTEM QUE APARECER ISSO");
                    Bukkit.broadcastMessage(String.valueOf(shooter.getUniqueId()));
                    shooter.teleport(hitPlayerLoc);
                    hitPlayer.teleport(shooterLoc);
                    e.setCancelled(true);
                    shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                    Cooldown.setCooldown(hitPlayer,20);
                }else{
                    Bukkit.broadcastMessage("teste");

            }
            }
        }

    }
}
