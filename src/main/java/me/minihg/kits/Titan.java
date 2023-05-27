package me.minihg.kits;

import me.minihg.Main;
import me.minihg.events.UndroppableItens;
import me.minihg.item.ItensConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Titan implements Listener {

    private HashMap<Player, Long> titanTimer = new HashMap<>();
    public static ItemStack titan;
    private Player p;
    private Player p2;
    int value = 20;
    public static void getItems(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        titan = new ItensConfig(Material.BEDROCK, 1, (short) 0)
                .setName("§aTitan")
                .setUnbreakable()
                .getItemStack();
        UndroppableItens.undroppableItens.add(titan);
        p.getInventory().addItem(titan);
    }

    @EventHandler
    public void onPlaceTitan(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getItemInHand().getType() == Material.BEDROCK)
            e.setCancelled(true);
    }
    @EventHandler
    public void titanEvent(PlayerInteractEvent e){
        p =  e.getPlayer();
        ItemStack itemKit = p.getInventory().getItemInHand();
        if(Cooldown.checkCooldown(p)){
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                    && (e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && (itemKit.getType() == Material.BEDROCK)){
                titanTimer.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10));
                Cooldown.setCooldown(p,40);
            }
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value
                && itemKit.getType() == Material.BEDROCK){
            p.sendMessage("§cVocê nao pode usar o titan por: "+Cooldown.getCooldown(p)+ "Segundos");
        }
    }

    @EventHandler
    public void titanDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(Main.inGame){
                p = (Player) e.getEntity();
                if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                        && titanTimer.containsKey(p)
                        && !(System.currentTimeMillis() >= titanTimer.get(p))
                        && e.getEntity() instanceof Player){
                    e.setCancelled(true);
                }else if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)
                        && titanTimer.containsKey(p)
                        && (System.currentTimeMillis() >= titanTimer.get(p))
                        && e.getEntity() instanceof Player){
                    e.setCancelled(false);
                }
            }

        }
    }

    @EventHandler
    public void titanHitting(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            p = (Player) e.getDamager();
            p2 = (Player) e.getEntity();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && titanTimer.containsKey(p)
                    && !(System.currentTimeMillis() >= titanTimer.get(p))){
                e.setCancelled(true);
            }else if((KitSelector.kitMap.containsKey(p2) && KitSelector.kitMap.get(p2) == value) && titanTimer.containsKey(p2)
                    && !(System.currentTimeMillis() >= titanTimer.get(p2))){
                e.setCancelled(true);
            }
        }
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit TITAN");
        p.sendMessage("§aFique imortal por 10 segundos");
    }
}
