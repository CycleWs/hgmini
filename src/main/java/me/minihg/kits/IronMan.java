package me.minihg.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;

public class IronMan implements Listener {

    public static ArrayList<Player> ironmanList = new ArrayList<>();

    @EventHandler
    public void onIronMan(PlayerDeathEvent e){
        Player death = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        if((death.getKiller() == killer) /*&& (killer : ironmanList)*/){
            killer.getInventory().addItem().equals(Material.IRON_INGOT);
        }
    }
}
