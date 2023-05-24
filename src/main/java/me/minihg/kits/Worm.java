package me.minihg.kits;

import me.minihg.Main;
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

import java.util.ArrayList;
import java.util.List;

public class Worm implements Listener {
    public static ArrayList<Player> wormList = new ArrayList<>();


    @EventHandler
    public void wormEvent(PlayerInteractEvent e ){
        Player p = e.getPlayer();
        boolean act = e.getAction().equals(Action.LEFT_CLICK_BLOCK);
        if(wormList.contains(p) && Main.inGame){
            if((act && e.getClickedBlock().getType() == Material.DIRT) && wormList.contains(p)){
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
            if(wormList.contains(p) && Main.inGame){
                if(e.getEntity() instanceof Player && (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)
                        && blockFall == Material.DIRT) && wormList.contains(p)){
                    e.setCancelled(true);
                }
            }

        }
    }
    public static List<String> getKitDescription() {
        List<String> list = new ArrayList<>();
        list.add("§cQuebre blocos de terra instantaneamente,");
        list.add("§creceba regeneração e não tome dano de");
        list.add("§cqueda caso caia em um bloco de terra");
        return list;
    }

}
