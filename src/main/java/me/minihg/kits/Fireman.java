package me.minihg.kits;

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

    public static ItemStack fisherman;
    public static boolean Fisherman(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        fisherman = new ItensConfig(Material.FISHING_ROD, 1, (short) 0)
                .setName("§eFisherman")
                .setUnbreakable()
                .getItemStack();

        p.getInventory().clear();
        p.getInventory().addItem(fisherman);
        p.getInventory().addItem(Bussola);
        return true;
    }

    @EventHandler
    public void onFireman(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if (e.getEntity() instanceof Player && ((e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)))){
            e.setCancelled(true);
        }
    }

}
