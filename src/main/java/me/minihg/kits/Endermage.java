package me.minihg.kits;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Endermage implements Listener {
    private static HashMap<String, Long> cooldown = new HashMap<String, Long>();
    private static List<String> invincible = new ArrayList<String>();

    private static HashMap<String, Integer> times = new HashMap<String, Integer>();
    private static HashMap<String, Integer> task = new HashMap<String, Integer>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerEndermage(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if ((event.getPlayer().getItemInHand().getType() == Material.ENDER_PORTAL_FRAME)
                && (event.getAction() == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock() != null)) {
            event.setCancelled(true);
            p.updateInventory();
            Block b = event.getClickedBlock();
            if ((b.getRelative(BlockFace.UP).getType() != Material.AIR)
                    || (b.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() != Material.AIR)) {
                return;
            }
            if ((!cooldown.containsKey(p.getName()))
                    || (((Long) cooldown.get(p.getName())).longValue() < System.currentTimeMillis())) {
                Material mat = event.getClickedBlock().getType();
                b.setType(Material.ENDER_PORTAL_FRAME);
                b.setData((byte) 1);

                Location loc2 = b.getLocation();
                loc2.add(0.0D, 1.0D, 0.0D);
                checker(p, loc2, b, mat, event.getClickedBlock().getData());
                cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + 5500L));
            }
        }
    }

    public void checker(final Player p, final Location loc2, final Block b, final Material mat, final byte data) {
        times.put(p.getName(), 10);
        task.put(p.getName(), Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
            @SuppressWarnings("deprecation")
            public void run() {
                if (times.get(p.getName()) == 0) {
                    b.setTypeIdAndData(mat.getId(), data, false);
                    Bukkit.getScheduler().cancelTask(task.get(p.getName()));
                    task.remove(p.getName());
                    times.remove(p.getName());
                    return;
                }

                List<Entity> nearby = p.getNearbyEntities(3.0D, 256.0D, 3.0D);
                List<Entity> teleported = new ArrayList<Entity>();
                for (Entity e : nearby) {
                    if (e.getLocation().distance(b.getLocation()) < 4.0D)
                        return;
                    if ((e instanceof Player)) {
                        Player tp = (Player) e;
                        /*if (!hasAbillity(tp)) {
                            p.teleport(loc2);
                            tp.teleport(loc2);
                            tp.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD
                                    + "You got teleported by an endermage. You are invincible for 5 seconds. Prepare to fight!!!");
                            invincible.add(tp.getName());
                            remove(tp);
                            if (!teleported.contains(tp))
                                teleported.add(tp);
                        }*/
                    }
                }
                if (teleported.size() >= 1) {
                    b.setTypeIdAndData(mat.getId(), data, false);
                    p.sendMessage(ChatColor.RED
                            + "Teleport succesful. You are invincible for 5 seconds. Prepare to fight!!!");
                    invincible.add(p.getName());
                    remove(p);
                    Bukkit.getScheduler().cancelTask(task.get(p.getName()));
                    task.remove(p.getName());
                    times.remove(p.getName());
                    return;
                }
                times.put(p.getName(), times.get(p.getName()) - 1);
            }
        }, 0L, 10L));
    }

    public void remove(final Player p) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
            public void run() {
                invincible.remove(p.getName());
                p.sendMessage(ChatColor.RED + "You are no longer invincible.");
            }
        }, 100L);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player)) {
            Player p = (Player) event.getEntity();
            if (invincible.contains(p))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))
                && ((invincible.contains(((Player) event.getEntity()).getName()))
                || (invincible.contains(((Player) event.getDamager()).getName()))))
            event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.ENDER_PORTAL_FRAME)
            event.setCancelled(true);
    }
}
