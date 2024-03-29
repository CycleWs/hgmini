package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Worm implements Listener {
    int value = 23;

    @EventHandler
    public void wormEvent(PlayerInteractEvent e ){
        Player p = e.getPlayer();
        boolean act = e.getAction().equals(Action.LEFT_CLICK_BLOCK);
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)){
            if((act && e.getClickedBlock().getType() == Material.DIRT)){
                e.getClickedBlock().breakNaturally();
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,  100,1));
            }
        }
    }
    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            Material blockFall = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value)){
                if(e.getEntity() instanceof Player && (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)
                        && blockFall == Material.DIRT)){
                    e.setCancelled(true);
                }
            }
        }
    }
    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit WORM");
        p.sendMessage("§aQuebre blocos de terra instantaneamente, " +
                        "receba regeneração e não tome dano de queda" +
                        " caso caia em um bloco de terra");
    }
}
