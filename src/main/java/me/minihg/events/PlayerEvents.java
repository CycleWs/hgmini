package me.minihg.events;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import me.minihg.Main;
import me.minihg.kits.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerEvents implements Listener {

    public PlayerEvents() {
    }

    @EventHandler
    public void onCompass(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getItemInHand().getType() == Material.COMPASS && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
            Boolean found = false;

            for(int i = 0; i < 1000; ++i) {
                List entities = p.getNearbyEntities((double)i, 128.0, (double)i);
                Iterator var = entities.iterator();

                while(var.hasNext()) {
                    Object e = var.next();
                    if (((Entity)e).getType().equals(EntityType.PLAYER) && p.getLocation().distance(((Entity)e).getLocation()) > 25.0 && !Main.Watch.contains(((Player)e).getName())) {
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
                p.setCompassTarget(new Location(p.getWorld(), 0.0, 120.0, 0.0));
            }
        }

    }

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && item != null && item.getType() == Material.MUSHROOM_SOUP && (p.getHealth() < 20.0 || p.getFoodLevel() < 20)) {
            int restores = 7;
            e.setCancelled(true);
            if (p.getHealth() < 20.0) {
                if (p.getHealth() + (double)restores <= 20.0) {
                    p.setHealth(p.getHealth() + (double)restores);
                } else {
                    p.setHealth(20.0);
                }
            }

            if (p.getFoodLevel() < 20) {
                if (p.getFoodLevel() + restores <= 20) {
                    p.setFoodLevel(p.getFoodLevel() + restores);
                    p.setSaturation(75.0F);
                    p.setExhaustion(0.0F);
                } else {
                    p.setFoodLevel(20);
                    p.setSaturation(75.0F);
                    p.setExhaustion(0.0F);
                }
            }

            if (item.getAmount() > 1) {
                p.setItemInHand(new ItemStack(Material.BOWL));
            } else {
                item = new ItemStack(Material.BOWL);
            }

            p.setItemInHand(item);
        }

    }

    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();
        Main.playersOnline.remove(p);
        if(Main.playersAdmin.contains(p.getUniqueId())){
            p.setGameMode(GameMode.SPECTATOR);
        }
        if(!(Main.playersAdmin.contains(p.getUniqueId()))){
            p.kickPlayer("§cVocê foi eliminado");
            Main.playersOnline.remove(p);
        }
        Inventory inventory = p.getInventory();
        for (ItemStack item : UndroppableItens.undroppableItens) {
            if (inventory.contains(item)) {
                inventory.removeItem(item);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Main.sendScoreboard(p);
        UUID uuid = p.getUniqueId();
        if(!(Main.inGame || Main.finalArena)){
            Main.playersOnline.add(p);
        }
        if(uuid.toString().equals("0d88d7ba-fad3-425d-8ce8-ee83be9e706b")
                || uuid.toString().equals("8876ca6c-814d-47f1-bb0e-4253456de83c")
                || uuid.toString().equals("61af26df-d7c2-4201-8a48-1f8c7f821250")){
            Main.playersAdmin.add(uuid);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        Main.playersOnline.remove(p);
        Main.playersAdmin.remove(uuid);
    }

    @EventHandler
    public void test(BlockBreakEvent e) {
//        Player p = e.getPlayer();
//        Block b = e.getBlock();
//        if (b.getType() == Material.GRASS) {
//            Bukkit.broadcastMessage(String.valueOf(Main.inGame));
//            Bukkit.broadcastMessage(String.valueOf(Main.ending));
//        }
    }
}