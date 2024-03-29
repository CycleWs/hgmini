package me.minihg.kits;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Monk implements Listener {
    public static ItemStack monk;
    int value = 13;
    public static void getItems(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        monk = new ItensConfig(Material.BLAZE_ROD, 1, (short) 0)
                .setName("§aMonk")
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(monk);
        p.getInventory().addItem(monk);
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Player){
            Player p = e.getPlayer();
            Material itemKit = p.getInventory().getItemInHand().getType();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                    && Main.inGame
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
    }
    @EventHandler
    public void onDeathDrops(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            e.getDrops().remove(monk);
        }
    }
    @EventHandler
    public void deleteItemKit(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Inventory invP = p.getInventory();
        if(Main.finalArena){
            invP.remove(monk);
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit MONK");
        p.sendMessage("§aEmbaralhe o inventário do seu inimigo ao bater nele com o monk");
    }

}
