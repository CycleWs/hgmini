package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;

public class Fisherman implements Listener {

    public static ArrayList<Player> fishermanList= new ArrayList<>();

    @EventHandler
    public void onFish(PlayerFishEvent e){
        Player hooked = (Player) e.getCaught();
        Player hooker = (Player) e.getHook();
    }
}
