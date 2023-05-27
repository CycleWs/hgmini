package me.minihg.kits;

import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Grandpa implements Listener {
    public static ArrayList<Player> grandpaList = new ArrayList<>();
    public static ItemStack grandpa;
    int value = 9;

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        grandpa = new ItensConfig(Material.STICK, 1, (short) 0)
                .setName("§aGrandpa")
                .setEnchant(Enchantment.KNOCKBACK,5)
                .getItemStack();

        UndroppableItens.undroppableItens.add(grandpa);
        p.getInventory().clear();
        p.getInventory().addItem(grandpa);
        p.getInventory().addItem(Bussola);
    }

    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit GRANDPA");
        p.sendMessage("§aTenha uma bengala que joga seus inimigos para longe");
    }
}
