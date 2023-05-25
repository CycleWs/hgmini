package me.minihg.kits;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Titan implements Listener {

    private HashMap<Player, Long> titanTimer = new HashMap<>();
    public static ItemStack titan;
    public static boolean titan(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        titan = new ItensConfig(Material.BEDROCK, 1, (short) 0)
                .setName("§aTitan")
                .setUnbreakable()
                .getItemStack();
        UndroppableItens.undroppableItens.add(titan);

        p.getInventory().clear();
        p.getInventory().addItem(titan);
        p.getInventory().addItem(Bussola);
        return true;
    }
    @EventHandler
    public void titanEvent(PlayerInteractEvent e){
        Player p =  e.getPlayer();
        ItemStack itemKit = p.getInventory().getItemInHand();
        if(Cooldown.checkCooldown(p)){
            if( KitSelector.kitMap.containsKey(p)
                    && KitSelector.kitMap.containsValue(22)
                    && (e.getAction() == Action.RIGHT_CLICK_AIR)
                    && (itemKit.getType() == Material.BEDROCK)){
                titanTimer.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10));
                Cooldown.setCooldown(p,40);
            }
        }else if(KitSelector.kitMap.containsKey(p)
                && KitSelector.kitMap.containsValue(22)){
            p.sendMessage("§cCooldown por: "+Cooldown.getCooldown(p));
        }
    }

    @EventHandler
    public void titanDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)){
            //the sender is not a Player
            return;
        }else{
            Player p = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(p)
                    && KitSelector.kitMap.containsValue(22))
                    && titanTimer.containsKey(p)
                    && !(System.currentTimeMillis() >= titanTimer.get(p))
                    && e.getEntity() instanceof Player){
                e.setCancelled(true);
            }else{
                e.setCancelled(false);
            }
        }

    }

    public static List<String> getKitDescription() {
        List<String> list = new ArrayList<>();
        list.add("§cFique imortal por 10 segundos quando");
        list.add("§cusar o seu item");
        return list;
    }
}
