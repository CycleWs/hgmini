package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Ninja implements Listener {

    int value = 14;

    public static HashMap<Player, Player> ninjaListTeleport = new HashMap<>();
    public static ArrayList<Player> ninjaList = new ArrayList<>();
    public static Player ninja;
    public static Player hitedPlayer;
    @EventHandler
    public void ninjaEvent(EntityDamageByEntityEvent e){
<<<<<<< Updated upstream
        ninja = (Player) e.getDamager();
        hitedPlayer = (Player) e.getEntity();
        if(ninjaList.contains(ninja) && (e.getDamager() instanceof Player && e.getEntity() instanceof Player)){
            ninjaListTeleport.remove(hitedPlayer,ninja);
            ninjaListTeleport.put(hitedPlayer, ninja);
=======
        if((e.getDamager() instanceof Player && e.getEntity() instanceof Player)){
            ninja = (Player) e.getDamager();
            hitedPlayer = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(ninja) && KitSelector.kitMap.get(ninja) == value) && Main.inGame){
                ninjaListTeleport.remove(hitedPlayer,ninja);
                ninjaListTeleport.put(hitedPlayer, ninja);
            }
>>>>>>> Stashed changes
        }
    }

    @EventHandler
    public void ninjaSneak(PlayerToggleSneakEvent e){
<<<<<<< Updated upstream
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
=======
        Player playerSneaked = e.getPlayer();
        if((KitSelector.kitMap.containsKey(playerSneaked) && KitSelector.kitMap.get(playerSneaked) == value) && Main.inGame){
            if((ninjaListTeleport.containsKey(hitedPlayer) && ninjaListTeleport.containsValue(ninja) && playerSneaked == ninja)){
                if(Cooldown.checkCooldown(playerSneaked)){
                    ninjaListTeleport.clear();
                    if(hitedPlayer.isOnline()){
                        ninja.teleport(hitedPlayer);
                        Cooldown.setCooldown(ninja,3);
                    }else{
                        ninja.sendMessage("§cJogador não encontrado!");
                    }

                }else{
                    ninja.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(ninja) + " Segundos");
                }
            }
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§aAo bater em seu inimigo, teleporte para ele ao pressionar shift");
    }
>>>>>>> Stashed changes
}
