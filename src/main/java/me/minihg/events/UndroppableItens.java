package me.minihg.events;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class UndroppableItens implements Listener {
    public static ArrayList<ItemStack> undroppableItens = new ArrayList();

    public UndroppableItens() {
    }

    @EventHandler
    public void undroppableEvent(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        if (undroppableItens.contains(item)) {
            e.setCancelled(true);
        }

    }
}