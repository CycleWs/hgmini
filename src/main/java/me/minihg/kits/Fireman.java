package me.minihg.kits;

import me.minihg.Main;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class Fireman implements Listener {
    public static ItemStack fireman;
    int value = 7;

    public Fireman() {
    }

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        fireman = (new ItensConfig(Material.WATER_BUCKET, 1, (short)0)).getItemStack();
        p.getInventory().clear();
        p.getInventory().addItem(new ItemStack[]{fireman});
        p.getInventory().addItem(new ItemStack[]{Bussola});
    }

    @EventHandler
    public void onFireman(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && Main.inGame && (e.getCause().equals(DamageCause.LAVA) || e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.FIRE))) {
                e.setCancelled(true);
            }
        }

    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§cSeja imune à lava e receba um");
        p.sendMessage("§cBalde de água");
    }
}
