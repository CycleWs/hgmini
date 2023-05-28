package me.minihg.events;

import me.minihg.Main;
<<<<<<< Updated upstream
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
=======
import me.minihg.kits.KitSelector;
import org.bukkit.*;
>>>>>>> Stashed changes
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
<<<<<<< Updated upstream
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
=======
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
>>>>>>> Stashed changes
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import static me.minihg.Main.plugin;
import static org.bukkit.Material.INK_SACK;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static me.minihg.Main.playersOnline;
import static me.minihg.kits.Worm.wormList;

public class PlayerEvents implements Listener {
    private Main plugin;

<<<<<<< Updated upstream
    public static UUID uuid;
=======
>>>>>>> Stashed changes

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
        Main.sendScoreboard(p);
    }

    @EventHandler
<<<<<<< Updated upstream
    public void onLeave(PlayerQuitEvent e){
=======
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            e.getDamager();
        }
    }


    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
>>>>>>> Stashed changes
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
            wormList.add(p);
            Bukkit.broadcastMessage("§5PlayerEvents: "+p);
        }
    }
<<<<<<< Updated upstream
}
=======

    @EventHandler
    public void test(BlockBreakEvent e) {
//        Player p = e.getPlayer();
//        Block b = e.getBlock();
//        if (b.getType() == Material.GRASS) {
//            Bukkit.broadcastMessage(String.valueOf(Main.inGame));
//            Bukkit.broadcastMessage(String.valueOf(Main.ending));
//        }
    }

    private ItemStack lapis;
    public PlayerEvents() {
        this.plugin = plugin;
        Dye d = new Dye();
        d.setColor(DyeColor.BLUE);
        this.lapis = d.toItemStack();
        this.lapis.setAmount(32);
        this.lapis.setType(INK_SACK);
    }
    @EventHandler
    public void openInventoryEvent(InventoryOpenEvent e) {
        if (e.getInventory() instanceof EnchantingInventory) {
            e.getInventory().setItem(1, lapis);
            e.getInventory().addItem(lapis);
        }
    }

    @EventHandler
    public void closeInventoryEvent(InventoryCloseEvent e) {
        if(e.getInventory() instanceof EnchantingInventory){
            if (e.getInventory().contains(INK_SACK)){
                e.getInventory().remove(INK_SACK);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {
        if(e.getInventory() instanceof EnchantingInventory){
            if (e.getCurrentItem().isSimilar(lapis) && lapis.getAmount() <= 32){
                e.setCancelled(true) ;
            }
        }
    }

    @EventHandler
    public void enchantItemEvent(EnchantItemEvent e) {
        if(e.getInventory() instanceof EnchantingInventory){
            if (e.getInventory().contains((ItemStack) e.getInventory())) {
                e.getInventory().setItem(1, this.lapis);
            }
        }

    }
}
>>>>>>> Stashed changes
