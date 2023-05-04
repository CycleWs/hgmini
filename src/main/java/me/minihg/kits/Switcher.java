package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Switcher implements Listener {
    private static Integer shed_id = null;
    public static ArrayList<Player> switcherList = new ArrayList<>();

    @EventHandler
    public void switcherEvent(EntityDamageByEntityEvent e){
        /*Entity thrownEntity = e.getEntity();
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
                if(!switcherList.contains(shooter)){
                    Bukkit.broadcastMessage("§aTEM QUE APARECER ISSO");
                    Bukkit.broadcastMessage(String.valueOf(switcherList.size()));
                    shooter.teleport(hitPlayerLoc);
                    hitPlayer.teleport(shooterLoc);
                    shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                    switcherList.add(shooter);
                }else{
                    Bukkit.broadcastMessage("YES GAMERS");
                    shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                    switcherList.remove(shooter);
                    e.setCancelled(true);

            }
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
        }*/

    }
}
