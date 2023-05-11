package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;

public class Ninja implements Listener {

    public static HashMap<Player, Player> ninjaListTeleport = new HashMap<>();
    public static Player ninja;
    public static Player hitedPlayer;
    @EventHandler
    public void ninjaEvent(EntityDamageByEntityEvent e){
        ninja = (Player) e.getDamager();
        hitedPlayer = (Player) e.getEntity();
        if((e.getDamager() instanceof Player && e.getEntity() instanceof Player)){
            ninjaListTeleport.remove(hitedPlayer,ninja);
            ninjaListTeleport.put(hitedPlayer, ninja);
           // Bukkit.broadcastMessage(ninjaListTeleport.keySet().toString());
           // Bukkit.broadcastMessage(ninjaListTeleport.entrySet().toString());
           // Bukkit.broadcastMessage("-----------------------------------------");
        }

    }

    @EventHandler
    public void ninjaSneak(PlayerToggleSneakEvent e){
        Player PlayerSneaked = e.getPlayer();
        if((ninjaListTeleport.containsKey(hitedPlayer) && ninjaListTeleport.containsValue(ninja) && PlayerSneaked == ninja)){
            if(Cooldown.checkCooldown(PlayerSneaked)){
                Location LocPlayerTP = hitedPlayer.getLocation();
                ninjaListTeleport.clear();
                ninja.teleport(LocPlayerTP);
                Cooldown.setCooldown(ninja,3);
            }else{
                ninja.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(ninja) + " Segundos");
            }
        }
    }
}
