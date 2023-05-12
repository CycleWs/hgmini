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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Switcher implements Listener {
    private static Integer shed_id = null;
    public static ArrayList<Player> switcherList = new ArrayList<>();

    @EventHandler
    public void switcherEvent(EntityDamageByEntityEvent e){
        Bukkit.broadcastMessage("FORA DOS DOIS IFS");
        if (e.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) e.getDamager();
            Player shooter = (Player) snowball.getShooter();
            Player hitPlayer = (Player) e.getEntity();
            if (snowball.getShooter() instanceof Player) {
                Location shooterLoc = shooter.getLocation();
                Location hitPlayerLoc = hitPlayer.getLocation();
                Cooldown.setCooldown(shooter, 5);
                shooter.teleport(hitPlayerLoc);
                hitPlayer.teleport(shooterLoc);
            }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Player p = (Player) event.getEntity().getShooter();
        if ((event.getEntityType() == EntityType.SNOWBALL) && ((event.getEntity().getShooter() instanceof Player))) {
            if(Cooldown.checkCooldown(p)){
                Cooldown.setCooldown(p, 5);
                p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
            }else{
                event.setCancelled(true);
                p.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(p) + " Segundos");
                p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
            }
        }
    }

    @EventHandler
    public void switcherCooldownVerify(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();
        if((e.getAction() == Action.RIGHT_CLICK_AIR) && (item.getType() == Material.SNOW_BALL) && (switcherList.contains(p))){
            e.setCancelled(true);
            Bukkit.broadcastMessage("UEU");
        }

    }
}
