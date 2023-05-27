package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
<<<<<<< Updated upstream
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
=======
import org.bukkit.entity.*;
>>>>>>> Stashed changes
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Switcher implements Listener {
<<<<<<< Updated upstream
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

=======
    public static ItemStack switcher;

    public static boolean switcher(Player p) {
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

//    private List<Snowball> balls = new ArrayList<Snowball>();
//
//    @EventHandler
//    public void onDamage(EntityDamageByEntityEvent event) {
//        if ((event.getDamager() instanceof Snowball)) {
//            if (!balls.contains((Snowball) event.getDamager()))
//                return;
//            Player thrower = (Player) ((Projectile) event.getDamager()).getShooter();
//            Location loc1 = thrower.getPlayer().getLocation().clone();
//            Location loc2 = event.getEntity().getLocation().clone();
//            thrower.getPlayer().teleport(loc2);
//            event.getEntity().teleport(loc1);
//        }
//    }

//    @EventHandler
//    public void onProjectileLaunch(ProjectileLaunchEvent event) {
//        if ((event.getEntityType() == EntityType.SNOWBALL) && ((event.getEntity().getShooter() instanceof Player))) {
//            Player p = (Player) event.getEntity().getShooter();
//            Entity ball = event.getEntity();
//            if ((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(20)) || !p.getInventory().getItemInHand().hasItemMeta())
//                return;
//
//            if (!Cooldown.checkCooldown(p)) {
//                event.setCancelled(true);
//                p.getInventory().addItem(switcher);
//                p.updateInventory();
//                p.sendMessage("§aStill on cooldown for ");
//                return;
//            }
//
//            balls.add((Snowball) ball);
//            Cooldown.setCooldown(p,5);
//        }
//    }

//    @EventHandler
//    public void switcherCooldownVerify(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        ItemStack item = p.getInventory().getItemInHand();
//        if(!(Cooldown.checkCooldown(p))){
//            e.setCancelled(true);
//            p.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(p) + " Segundos");
//        }
//        if ((e.getAction() == Action.RIGHT_CLICK_AIR) && (item.getType() == Material.SNOW_BALL
//                && KitSelector.kitMap.containsKey(p)
//                && KitSelector.kitMap.containsValue(20))){
//            p.getInventory().addItem(switcher);
//        }
//    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§aAo jogar sua bolinha de neve no seu inimigo trocará de lugar com ele");
>>>>>>> Stashed changes
    }
}
