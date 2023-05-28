package me.minihg.kits;

import java.util.ArrayList;
import java.util.HashMap;
import me.minihg.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Ninja implements Listener {
    int value = 14;
    public static HashMap<Player, Player> ninjaListTeleport = new HashMap();
    public static ArrayList<Player> ninjaList = new ArrayList();
    public static Player ninja;
    public static Player hitedPlayer;

    public Ninja() {
    }

    @EventHandler
    public void ninjaEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            ninja = (Player)e.getDamager();
            hitedPlayer = (Player)e.getEntity();
            if (KitSelector.kitMap.containsKey(ninja) && (Integer)KitSelector.kitMap.get(ninja) == this.value && Main.inGame) {
                ninjaListTeleport.remove(hitedPlayer, ninja);
                ninjaListTeleport.put(hitedPlayer, ninja);
            }
        }

    }

    @EventHandler
    public void ninjaSneak(PlayerToggleSneakEvent e) {
        Player playerSneaked = e.getPlayer();
        if (KitSelector.kitMap.containsKey(playerSneaked) && (Integer)KitSelector.kitMap.get(playerSneaked) == this.value && Main.inGame && ninjaListTeleport.containsKey(hitedPlayer) && ninjaListTeleport.containsValue(ninja) && playerSneaked == ninja) {
            if (Cooldown.checkCooldown(playerSneaked)) {
                ninjaListTeleport.clear();
                if (hitedPlayer.isOnline()) {
                    ninja.teleport(hitedPlayer);
                    Cooldown.setCooldown(ninja, 3);
                } else {
                    ninja.sendMessage("§cJogador não encontrado!");
                }
            } else {
                ninja.sendMessage("§cVocê não pode utilizar o kit por: " + Cooldown.getCooldown(ninja) + " Segundos");
            }
        }

    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit NINJA");
        p.sendMessage("§aAo bater em seu inimigo, teleporte para ele ao pressionar shift");
    }
}