package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class IronMan implements Listener {

    int value = 10;
    @EventHandler
    public void onIronMan(PlayerDeathEvent e){
        Player killer = e.getEntity().getKiller();
        if((KitSelector.kitMap.containsKey(killer) && KitSelector.kitMap.get(killer) == value) && Main.inGame){
            killer.getInventory().setItem(1,new ItemStack(Material.IRON_INGOT));
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit IRONMAN");
        p.sendMessage("§aA cada kill feita, receba um ferro");
    }
}
