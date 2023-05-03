package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class Achilles implements Listener {

    public static ArrayList<Player> achillesList = new ArrayList<>();

  //Arrumar o evento do kit achilles
    @EventHandler
    public void achillesEvent (EntityDamageByEntityEvent e){
        Player p = (Player) e.getDamager();
        Player enitity = (Player) e.getEntity();
        if(achillesList.contains(p) && Main.inGame){
            if(enitity.getItemInHand().equals(Material.WOOD_SWORD)){
                p.sendMessage("TESTE TESTE");

            }
        }
    }


}
