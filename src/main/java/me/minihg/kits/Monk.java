package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Monk implements Listener {
    public static ItemStack monk;

    public static boolean Monk(Player p){
        monk = new ItensConfig(Material.BLAZE_ROD,1,(short) 0).setName("&2Monk")
                .setEnchant(Enchantment.DIG_SPEED,0).setUnbreakable().getItemStack();
        return true;
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        if(p.getItemInHand() == null || p.getItemInHand().getType() != Material.BLAZE_ROD|| !(e.getRightClicked() instanceof Player)){
            return;
        }else {
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
