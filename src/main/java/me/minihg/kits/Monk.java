package me.minihg.kits;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monk implements Listener {
    public static ItemStack monk;
    public static boolean Monk(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        monk = new ItensConfig(Material.BLAZE_ROD, 1, (short) 0)
                .setName("§aMonk")
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(monk);
        p.getInventory().clear();
        p.getInventory().addItem(monk);
        p.getInventory().addItem(Bussola);
        return true;
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        Material itemKit = p.getInventory().getItemInHand().getType();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(14))
                && Main.inGame
                && (e.getRightClicked() instanceof Player)
                && itemKit == monk.getType()){
                if(Cooldown.checkCooldown(p)){
                    Player vic = (Player) e.getRightClicked();
                    Inventory inv = vic.getInventory();
                    int slot = new Random().nextInt(36);
                    ItemStack slis = inv.getItem(slot), heldis = vic.getItemInHand();
                    vic.setItemInHand(slis);
                    inv.setItem(slot, heldis);
                    Cooldown.setCooldown(p,12);
                }else{
                    p.sendMessage("§cCooldown por: "+Cooldown.getCooldown(p));
                }
        }
    }
    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cEmbaralhe o inventário do seu inimigo");
        list.add("§cao bater nele com o monk");
        return list;
    }

}
