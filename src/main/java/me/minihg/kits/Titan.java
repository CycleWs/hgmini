package me.minihg.kits;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Titan implements Listener {

    public static HashMap<UUID, Double> titanTimer;

    public static void setupCooldown(){
        titanTimer = new HashMap<>();
    }

    public static ItemStack titan;
    public static boolean Titan(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        titan = new ItensConfig(Material.BEDROCK, 1, (short) 0)
                .setName("§aTitan")
                .setUnbreakable()
                .getItemStack();
        UndroppableItens.undroppableItens.add(titan);

        p.getInventory().clear();
        p.getInventory().addItem(titan);
        p.getInventory().addItem(Bussola);
        return true;
    }
    @EventHandler
    public void titanEvent(PlayerInteractEvent e){
        Player p =  e.getPlayer();
        ItemStack itemKit = p.getInventory().getItemInHand();

        if(e.getAction() == Action.RIGHT_CLICK_AIR && (itemKit.getItemMeta().getDisplayName().equalsIgnoreCase("§aTitan"))){
            Cooldown.setCooldown(p,5);
            }
        }

    @EventHandler
    public void titanDamage(EntityDamageEvent e){
        Player p = (Player) e.getEntity();
        if(titanTimer.containsKey(p.getUniqueId()) && e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
    public static List<String> getKitDescription() {
        List<String> list = new ArrayList<>();
        list.add("§cFique imortal por 10 segundos quando");
        list.add("§cusar o seu item");
        return list;
    }
}
