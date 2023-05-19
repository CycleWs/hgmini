package me.minihg.kits;

import me.minihg.Main;
import me.minihg.item.ItensConfig;
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

    public static ItemStack switcher;
    public static boolean Switcher(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        switcher = new ItensConfig(Material.SNOW_BALL, 1, (short) 0)
                .setName("§eSwitcher")
                .setUnbreakable()
                .getItemStack();

        p.getInventory().clear();
        p.getInventory().addItem(switcher);
        p.getInventory().addItem(Bussola);
        return true;
    }

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
                if(Cooldown.checkCooldown(shooter)){
                    shooter.teleport(hitPlayerLoc);
                    hitPlayer.teleport(shooterLoc);
                    shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
                    Cooldown.setCooldown(shooter, 5);
                }else{
                    shooter.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(shooter) + " Segundos");
                    shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
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
        }

    }
}
