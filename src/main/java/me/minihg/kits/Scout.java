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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Scout implements Listener {

    public static ItemStack scout;
    int value = 24;

    public static void getItems(Player p) {
        //TENTAR MUDAR O ITEM PARA POÇÃO DE VELOCIDADE2
        scout = new ItensConfig(Material.FEATHER, 1, (short)0)
                .setName("§aScout")
                .setUnbreakable()
                .getItemStack();

        UndroppableItens.undroppableItens.add(scout);
        p.getInventory().addItem(scout);
    }

    @EventHandler
    public void onScout(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material itemKit = p.getInventory().getItemInHand().getType();
            if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                    && KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value
                    && (Main.inGame || Main.invincibility)
                    && itemKit == Material.FEATHER) {
                if (Cooldown.checkCooldown(p)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 250, 1));
                    Cooldown.setCooldown(p, 30);
                    e.setCancelled(true);
                }else if(KitSelector.kitMap.get(p) == value){
                    p.sendMessage("§cVocê está em cooldown, aguarde " + Cooldown.getCooldown(p));
                    e.setCancelled(true);
                }
            }
    }
    @EventHandler
    public void onDeathDrops(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            e.getDrops().remove(scout);
        }
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit Scout");
        p.sendMessage("§aClique na poção e ganhe velocidade por 10 segundos");
    }
}