package me.minihg.kits;

import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Grandpa implements Listener {
    public static ArrayList<Player> grandpaList = new ArrayList<>();
    public static ItemStack grandpa;

    public static boolean grandpa(Player p) {
        grandpa = new ItensConfig(Material.STICK, 1, (short) 0)
                .setName("§aGrandpa")
                .setEnchant(Enchantment.KNOCKBACK, 2)
                .getItemStack();

        UndroppableItens.undroppableItens.add(grandpa);
        return true;
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§cTenha uma bengala que joga");
        p.sendMessage("§cseus inimigos para longe");
    }
}
