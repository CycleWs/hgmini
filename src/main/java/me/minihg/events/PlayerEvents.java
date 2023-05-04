package me.minihg.events;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static me.minihg.Main.playersOnline;
import static me.minihg.kits.Achilles.achillesList;

public class PlayerEvents implements Listener {

    public static UUID uuid;

    //public static List<UUID> playersOnline = new ArrayList<>();
    @EventHandler
    public void onCompass(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getItemInHand().getType() == Material.COMPASS && (event.getAction() == Action.LEFT_CLICK_AIR ||
                event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                event.getAction() == Action.RIGHT_CLICK_AIR)) {
            Boolean found = false;

            for(int i = 0; i < 1000; ++i) {
                List entities = p.getNearbyEntities((double)i, 128.0D, (double)i);
                Iterator var = entities.iterator();

                while(var.hasNext()) {
                    Object e = var.next();
                    if (((Entity)e).getType().equals(EntityType.PLAYER)
                            && p.getLocation().distance(((Entity)e).getLocation()) > 25.0D
                            && !Main.Watch.contains(((Player)e).getName())) {

                        p.setCompassTarget(((Entity)e).getLocation());
                        p.sendMessage("§aBússola apontando para " + ((Player)e).getName());
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            if (!found) {
                p.sendMessage("§cERRO: Nenhum jogador encontrado, apontando para o spawn!");
                p.setCompassTarget(new Location(p.getWorld(), 0.0D, 120.0D, 0.0D));
            }
        }

    }

    @EventHandler
    public void onSoup(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                && item != null
                && item.getType() == Material.MUSHROOM_SOUP
                && (p.getHealth() < 20.0D || p.getFoodLevel() < 20)){
            int restores = 7;
            e.setCancelled(true);
            if(p.getHealth() < 20.0D){
                if (p.getHealth() + (double)restores <= 20.0D) {
                    p.setHealth(p.getHealth() + (double)restores);
                }else{
                    p.setHealth(20.0D);
                }
            }if (p.getFoodLevel() < 20) {
                if (p.getFoodLevel() + restores <= 20) {
                    p.setFoodLevel(p.getFoodLevel() + restores);
                    p.setSaturation(75.0F);
                    p.setExhaustion(0.0F);
                } else {
                    p.setFoodLevel(20);
                    p.setSaturation(75.0F);
                    p.setExhaustion(0.0F);
                }
            }if (item.getAmount() > 1) {
                p.setItemInHand(new ItemStack(Material.BOWL));
            } else {
                item = new ItemStack(Material.BOWL);
            }
            p.setItemInHand(item);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        uuid = p.getUniqueId();
        playersOnline.add(String.valueOf(p));
        Bukkit.broadcastMessage(String.valueOf(p));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        playersOnline.remove(uuid);
        Bukkit.broadcastMessage(String.valueOf(playersOnline));
    }
    @EventHandler
    public void test(BlockBreakEvent e){
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if(b.getType() == Material.GRASS){
            //Bukkit.broadcastMessage("§cinvencibilidade = " + Main.Invincibility);
            //Bukkit.broadcastMessage("§epregame = "+Main.PreGame);
            //Bukkit.broadcastMessage("§cEm jogo? = " + Main.inGame);
            achillesList.add(p);
            int teste = achillesList.size();
            Bukkit.broadcastMessage("§5PlayerEvents: "+p);
        }
    }
}
