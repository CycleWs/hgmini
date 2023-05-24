package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Turtle implements Listener {
    public static ArrayList<Player> turtleList = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if (KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(23) && Main.inGame) {
            if ((e.getEntity() instanceof Player)) {
                if ((p.isSneaking()) && (e.getDamage() > 1))
                    e.setDamage(1);
            }
        }
    }
    @EventHandler
    public void onDamage1(EntityDamageByEntityEvent e) {
        if( Main.inGame){
            if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
                Player p = (Player) e.getDamager();
                if (p.isSneaking())
                    p.damage(0);
            }
        }
    }
    public static List<String> getKitDescription() {
        List<String> list = new ArrayList<>();
        list.add("§cReceba menos dano quando estiver agachado ");
        return list;
    }
}
