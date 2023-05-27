package me.minihg.events;

import me.minihg.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ServerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.sendMessage("§2Bem vindo ao servidor");
    }

    @EventHandler
    public void cancelJoinInGame(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!Main.preGame){
            p.kickPlayer("§cPartida em andamento!");
        }
    }
}
