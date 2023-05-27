package me.minihg.kits;

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

import java.util.ArrayList;

public class Fisherman implements Listener {

    public static ArrayList<Player> fishermanList= new ArrayList<>();

    public static ItemStack fisherman;
    int value = 8;
    public static void getItems(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        fisherman = new ItensConfig(Material.FISHING_ROD, 1, (short) 0)
                .setName("§aFisherman")
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(fisherman);
        p.getInventory().clear();
        p.getInventory().addItem(fisherman);
        p.getInventory().addItem(Bussola);
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Entity caught = e.getCaught();
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            if (caught != null){
                caught.teleport(e.getPlayer());
            }

        }
    }
<<<<<<< Updated upstream
=======

    public static void getKitDescription(Player p){
        p.sendMessage("§aTenha uma vara de pesca que puxa instantaneamente seus inimigos para você");
    }
>>>>>>> Stashed changes
}
