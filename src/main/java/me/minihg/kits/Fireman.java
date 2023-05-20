package me.minihg.kits;

import me.minihg.Main;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Fireman implements Listener{

    public static ArrayList<Player> firemanList = new ArrayList<>();

    public static ItemStack fireman;
    public static boolean fireman(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        fireman = new ItensConfig(Material.WATER_BUCKET, 1, (short) 0)
                .getItemStack();

        p.getInventory().clear();
        p.getInventory().addItem(fireman);
        p.getInventory().addItem(Bussola);
        return true;
    }

    @EventHandler
    public void onFireman(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if(firemanList.contains(p) && Main.inGame){
            if (e.getEntity() instanceof Player && ((e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                    || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                    || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)))){
                e.setCancelled(true);
            }
        }
    }
}
