package me.minihg.kits;

import java.util.ArrayList;
import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Fisherman implements Listener {
    public static ArrayList<Player> fishermanList = new ArrayList();
    public static ItemStack fisherman;
    int value = 8;

    public Fisherman() {
    }

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        fisherman = (new ItensConfig(Material.FISHING_ROD, 1, (short)0)).setName("§aFisherman").setUnbreakable().getItemStack();
        UndroppableItens.undroppableItens.add(fisherman);
        p.getInventory().addItem(fisherman);
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Entity caught = e.getCaught();
        Player p = e.getPlayer();
        if(e.getCaught() instanceof Player){
            if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && Main.inGame && caught != null) {
                caught.teleport(e.getPlayer());
            }
        }


    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit FISHERMAN");
        p.sendMessage("§aTenha uma vara de pesca que puxa instantaneamente seus inimigos para você");
    }
}