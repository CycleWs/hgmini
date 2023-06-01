package me.minihg.events;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import me.minihg.Main;
import me.minihg.kits.KitSelector;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Material.INK_SACK;

public class PlayerEvents implements Listener {

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
        e.setDeathMessage(null);
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
        if(Main.playersOnline.size() == 20){
            getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist on");
        }
//        if(uuid.toString().equals("0d88d7ba-fad3-425d-8ce8-ee83be9e706b")
//                || uuid.toString().equals("8876ca6c-814d-47f1-bb0e-4253456de83c")
//                || uuid.toString().equals("61af26df-d7c2-4201-8a48-1f8c7f821250")){
//            Main.playersAdmin.add(uuid);
//        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Main.playersOnline.remove(p);
        if(Main.playersOnline.size() < 20){
            getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist off");
        }
    }

    @EventHandler
    public void deathMessage(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
            Player dead = (Player) e.getEntity();
            Player killer = e.getEntity().getKiller();
            ItemStack itemHand = killer.getInventory().getItemInHand();
            if (e.getEntity() == dead && !(itemHand.getType().name().equals("AIR"))) {
                Bukkit.broadcastMessage("§e" + dead.getName() + "§b foi morto por §e" + killer.getName() + "§b utilizando " + itemHand.getType().name().replace("_", " ").toLowerCase());
                Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogadores restantes");
            } else if (e.getEntity() == dead && itemHand.getType().name().equals("AIR")) {
                Bukkit.broadcastMessage("§e" + dead.getName() + "§b foi morto por §e" + killer.getName() + "§b utilizando" + itemHand.getType().name().replace("AIR", " o soco ").toLowerCase());
                Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogadores restantes");
            }
        }
    }


    @EventHandler
    public void test(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (b.getType() == Material.GRASS) {
            getServer().dispatchCommand(getServer().getConsoleSender(), "command");
        }
    }

    private ItemStack lapis;
    public PlayerEvents() {
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