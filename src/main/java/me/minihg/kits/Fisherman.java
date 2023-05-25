package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;
import java.util.List;

public class Fisherman implements Listener {

    public static ArrayList<Player> fishermanList= new ArrayList<>();

    @EventHandler
    public void onFish(PlayerFishEvent e){
        Player hooked = (Player) e.getCaught();
        Player hooker = (Player) e.getHook();
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§cTenha uma vara de pesca que puxa instantaneamente");
        p.sendMessage("§cseus inimigos para você");
    }
}
