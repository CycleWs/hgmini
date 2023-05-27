package me.minihg.kits;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
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
import java.util.List;

public class Switcher implements Listener {
    private static Integer shed_id = null;
    public static ArrayList<Player> switcherList = new ArrayList<>();

    public static ItemStack switcher;

    public static boolean Switcher(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        switcher = new ItensConfig(Material.SNOW_BALL, 1, (short) 0)
                .setName("§aSwitcher")
                .setUnbreakable()
                .getItemStack();
        UndroppableItens.undroppableItens.add(switcher);

        p.getInventory().clear();
        p.getInventory().addItem(switcher);
        p.getInventory().addItem(Bussola);
        return true;
    }

//    @EventHandler
//    public void switcherEvent(EntityDamageByEntityEvent e) {
//        Player p = (Player) e.getDamager();
//        if (switcherList.contains(p) && Main.inGame) {
//            if (e.getDamager() instanceof Snowball) {
//                Snowball snowball = (Snowball) e.getDamager();
//                Player shooter = (Player) snowball.getShooter();
//                Player hitPlayer = (Player) e.getEntity();
//                ItemStack itemKit = shooter.getInventory().getItemInHand();
//                if (snowball.getShooter() instanceof Player && (itemKit.getItemMeta().getDisplayName().equalsIgnoreCase("§aSwitcher"))) {
//                    Location shooterLoc = shooter.getLocation();
//                    Location hitPlayerLoc = hitPlayer.getLocation();
//                    if (Cooldown.checkCooldown(shooter)) {
//                        shooter.teleport(hitPlayerLoc);
//                        hitPlayer.teleport(shooterLoc);
//                        shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
//                        Cooldown.setCooldown(shooter, 5);
//                    } else {
//                        shooter.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(shooter) + " Segundos");
//                        shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 1));
//                        e.setCancelled(true);
//                    }
//                }
//            }
//        }
//
//    }
//
//    @EventHandler
//    public void switcherCooldownVerify(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        ItemStack item = p.getInventory().getItemInHand();
//        if ((e.getAction() == Action.RIGHT_CLICK_AIR) && (item.getType() == Material.SNOW_BALL) && (switcherList.contains(p))) {
//            e.setCancelled(true);
//            Bukkit.broadcastMessage("UEU");
//        }
//    }
//
//    public static void getKitDescription(Player p) {
//        p.sendMessage("§cAo jogar sua bolinha de neve no seu inimigo");
//        p.sendMessage("§ctrocará de lugar com ele");
//    }
}