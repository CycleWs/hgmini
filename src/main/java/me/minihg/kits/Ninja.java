package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ninja implements Listener {

    public static HashMap<Player, Player> ninjaListTeleport = new HashMap<>();
    public static ArrayList<Player> ninjaList = new ArrayList<>();
    public static Player ninja;
    public static Player hitedPlayer;
    @EventHandler
    public void ninjaEvent(EntityDamageByEntityEvent e){
        ninja = (Player) e.getDamager();
        hitedPlayer = (Player) e.getEntity();
        if(ninjaList.contains(ninja)){
            if((e.getDamager() instanceof Player && e.getEntity() instanceof Player)){
                ninjaListTeleport.remove(hitedPlayer,ninja);
                ninjaListTeleport.put(hitedPlayer, ninja);
            }
        }
    }

    @EventHandler
    public void ninjaSneak(PlayerToggleSneakEvent e){
        Player PlayerSneaked = e.getPlayer();
        if(ninjaList.contains(PlayerSneaked) && Main.inGame){
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
    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cAo bater em seu inimigo, teleporte");
        list.add("§cpara ele ao pressionar shift");
        return list;
    }
}
