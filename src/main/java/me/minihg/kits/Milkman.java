package me.minihg.kits;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Milkman implements Listener {

    public static ItemStack milkman;
    int value = 25;

    public static void getItems(Player p) {
        milkman = (new ItensConfig(Material.MILK_BUCKET, 1, (short)0))
                .setName("§aMilkman")
                .setUnbreakable()
                .getItemStack();
        UndroppableItens.undroppableItens.add(milkman);
        p.getInventory().addItem(milkman);
    }

    @EventHandler
    public void onMilkman(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Material itemKit = p.getInventory().getItemInHand().getType();
            if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                    && KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value
                    && (Main.inGame || Main.invincibility)
                    && itemKit == Material.MILK_BUCKET){
                if(Cooldown.checkCooldown(p)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,200,4));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,200,0));
                    Cooldown.setCooldown(p,45);
                    e.setCancelled(true);
                }else if(KitSelector.kitMap.get(p) == value){
                    p.sendMessage("§cVocê está em cooldown, aguarde: "+Cooldown.getCooldown(p));
                    e.setCancelled(true);
                }
            }
        }

    @EventHandler
    public void onDeathDrops(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            e.getDrops().remove(milkman);
        }
    }
    @EventHandler
    public void deleteItemKit(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Inventory invP = p.getInventory();
        if(Main.finalArena){
            invP.remove(milkman);
        }
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit MILKMAN");
        p.sendMessage("§aClique no leite para receber perks por 30 segundos");
    }
}