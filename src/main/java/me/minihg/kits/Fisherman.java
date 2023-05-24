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

    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cTenha uma vara de pesca que puxa instantaneamente");
        list.add("§cseus inimigos para você");
        return list;
    }
}
