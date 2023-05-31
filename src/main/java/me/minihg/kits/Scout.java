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

public class Scout implements Listener {

    public static ArrayList<Player> scoutList = new ArrayList<>();
    public static ItemStack scout;
    int value = 24;
    //cooldown = 30s

    public static void getItems(Player p) {
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        scout = (new ItensConfig(Material.POTION, 1, (short)8226)).setName("§aScout").getItemStack();
        UndroppableItens.undroppableItens.add(scout);
        p.getInventory().addItem(new ItemStack[]{scout});
    }

    @EventHandler
    public void onScout(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getPlayer().getItemInHand().getType() == scout.getType()){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10,1));
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
