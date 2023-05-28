package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.List;

public class Turtle implements Listener {
    public static ArrayList<Player> turtleList = new ArrayList<>();

    int value = 21;

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        boolean isSneaking = e.isSneaking();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            if(Cooldown.checkCooldown(p)){
                if (!isSneaking) {
                    Cooldown.setCooldown(p,5);
                }
            }else{
                p.sendMessage("§cVocê está em cooldown por: "+Cooldown.getCooldown(p));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame) {
                if (p.isSneaking() && Cooldown.checkCooldown(p)){
                    e.setDamage(1.0D);
                }
            }
        }
    }
    @EventHandler
    public void onDamage1(EntityDamageByEntityEvent event) {
        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
            Player p = (Player) event.getDamager();
            if (p.isSneaking()){
                if ((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame) {
                    if(Cooldown.checkCooldown(p)){
                        p.sendMessage("§cTurtle's não podem atacar quando estão de shift!");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }


    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit TURTLE");
        p.sendMessage("§aReceba menos dano quando estiver agachado ");
    }
}
