package me.minihg.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import me.minihg.Main;
import me.minihg.feastminifeast.FeastManager;
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
import org.bukkit.event.player.*;
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

    private final HashMap<Player, Long> chatCooldown = new HashMap<>();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if((chatCooldown.containsKey(p)
                && !(System.currentTimeMillis() >= chatCooldown.get(p))
                && !Main.playersAdmin.contains(p.getUniqueId()))){
            e.setCancelled(true);
            p.sendMessage("§cAguarde para usar o comando novamente! "+convert(p)+" Segundos");
            }else{
            chatCooldown.remove(p);
            if(Main.playersAdmin.contains(p.getUniqueId())){
                e.setFormat("§4"+ p.getDisplayName() + " §7» §f" + e.getMessage());
            }else{
                e.setFormat(p.getDisplayName() + " §7» §f" + e.getMessage());
            }
            if(!(Main.playersAdmin.contains(p.getUniqueId())) && !Main.toggleChat){
                e.setCancelled(true);
                p.sendMessage("§co chat está desativado!");
            }else if(!Main.toggleChat && Main.playersAdmin.contains(p.getUniqueId())){
                e.setFormat("§4"+ p.getDisplayName() + " §7» §f" + e.getMessage());
            }
            chatCooldown.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(2));
        }
    }
    private Long convert (Player p){
        long tempo = System.currentTimeMillis() - chatCooldown.get(p);
        return 1 + TimeUnit.MILLISECONDS.toSeconds(tempo) * -1;
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
            if(Main.playerKills.get(p.getUniqueId()) == null){
                p.kickPlayer("§cVocê foi eliminado\n Você não fez nenhuma kill!");
            }else if(Main.playerKills.get(p.getUniqueId()) > 1){
                p.kickPlayer("§cVocê foi eliminado\n Você fez: "+ Main.playerKills.get(p.getUniqueId()) + " kills");
            }else if(Main.playerKills.get(p.getUniqueId()) == 1){
                p.kickPlayer("§cVocê foi eliminado\n Você fez: "+ Main.playerKills.get(p.getUniqueId()) + " kill");
            }

        }
        Inventory inventory = p.getInventory();
        for (ItemStack item : UndroppableItens.undroppableItens) {
            if (inventory.contains(item)) {
                inventory.removeItem(item);
            }
        }
    }
    @EventHandler
    public void onVoidPlayer (PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location locP = e.getPlayer().getLocation();
        Location locSpawn = new Location(Bukkit.getWorld("world"), 0.0, 80.0, 0.0, 0.0F, 0.0F);
        if(locP.getY() < 1){
            p.teleport(locSpawn);
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        if((!(Main.inGame || Main.finalArena || Main.invincibility)) && Main.playersOnline.size() < Main.maxPlayers){
            Main.playersOnline.add(p);
        }else if(!(Main.playersAdmin.contains(p.getUniqueId()))){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cPartida em andamento!");
            Main.playersOnline.remove(p);
        }else if(Main.playersOnline.size() == Main.maxPlayers){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cPartida está lotada!");
            Main.playersOnline.remove(p);
        }
        if(Main.playersOnline.size() == Main.maxPlayers){
            Bukkit.setWhitelist(true);
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cPartida está lotada!");
            Main.playersOnline.remove(p);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Main.sendScoreboard(p);
        UUID uuid = p.getUniqueId();
        if(Main.playersAdmin.contains(uuid) && (Main.inGame || Main.finalArena || Main.invincibility)){
            p.setGameMode(GameMode.SPECTATOR);
        }
//        if(Main.playersOnline.size() == 20){
//            getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist on");
//        }
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
//        if(Main.preGame &&(Main.playersOnline.size() < 20)){
//            getServer().dispatchCommand(getServer().getConsoleSender(), "whitelist off");
//        }
    }
    @EventHandler
    public void deathMessage(PlayerDeathEvent e) {
            Player dead = e.getEntity().getPlayer();
            Main.playersOnline.remove(dead);
            Player killer = null;
            ItemStack itemHand = null;
            if(e.getEntity().getKiller() != null){
                killer = e.getEntity().getKiller();
                itemHand = killer.getInventory().getItemInHand();
                if(Main.playerKills.containsKey(killer.getUniqueId())){
                    int kills = Main.playerKills.get(killer.getUniqueId());
                    kills++;
                    Main.playerKills.put(killer.getUniqueId(),kills);
                }else{
                    Main.playerKills.put(killer.getUniqueId(),1);
                }
                if (e.getEntity().getPlayer() == dead && !(itemHand.getType().name().equals("AIR"))) {
                    Bukkit.broadcastMessage("§e" + dead.getName() + "§b foi morto por §e" + killer.getName() + "§b utilizando " + itemHand.getType().name().replace("_", " ").toLowerCase());
                    if(Main.playersOnline.size() > 1){
                        Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogadores restantes");
                    }else{
                        Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogador restantes");
                    }
                }else if (e.getEntity().getPlayer() == dead && itemHand.getType().name().equals("AIR")) {
                    Bukkit.broadcastMessage("§e" + dead.getName() + "§b foi morto por §e" + killer.getName() + "§b no " + itemHand.getType().name().replace("AIR", "murro ").toLowerCase());
                    if(Main.playersOnline.size() > 1){
                        Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogadores restantes");
                    }else{
                        Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " Jogador restantes");
                    }
                }
                killer.sendMessage("§aSuas kills: "+Main.playerKills.get(killer.getUniqueId()));
            }else{
                Bukkit.broadcastMessage("§e"+ dead.getName() + "§b morreu sozinho!");
                Bukkit.broadcastMessage("§b" + Main.playersOnline.size() + " jogadores restantes");
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