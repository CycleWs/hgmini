package me.minihg.kits;

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

public class Milkman implements Listener {

    public static ArrayList<Player> milkmanList = new ArrayList<>();

    public static ItemStack milkman;
    int value = 25;
    //cooldown = 45s

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        milkman = (new ItensConfig(Material.MILK_BUCKET, 1, (short)0)).setName("§aMilkman").getItemStack();
        UndroppableItens.undroppableItens.add(milkman);
        p.getInventory().addItem(new ItemStack[]{milkman});
    }

    @EventHandler
    public void onMilkman(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getPlayer().getItemInHand().getType() == milkman.getType()){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,30,0));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,30,4));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,30,0));
        }
    }

    @EventHandler
    public void onDeathDrops(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            e.getDrops().remove(milkman);
        }
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit MILKMAN");
        p.sendMessage("§aClique no leite para receber perks por 30 segundos");
    }


}
