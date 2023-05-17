package me.minihg.kits;

import me.minihg.item.ItensConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Thor implements Listener {
    public static ArrayList<Player> thorList = new ArrayList<>();

    public static ItemStack thor;

    public static boolean Thor(Player p){
        thor = new ItensConfig(Material.WOOD_AXE,1,(short) 0).setName("&2Thor")
                .setUnbreakable().getItemStack();
        return true;
    }

    @EventHandler
    public void onThor(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        Location loc = b.getLocation();
        Block hb = p.getWorld().getHighestBlockAt(loc);
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.WOOD_AXE){
            p.getWorld().strikeLightning(p.getWorld().getHighestBlockAt(e.getClickedBlock().getLocation()).getLocation());
            for (Entity entity : Bukkit.getWorld("world").getEntities()){
                if(entity.getLocation().distance(e.getClickedBlock().getLocation()) <= 3.0D){
                    if ((entity instanceof LivingEntity) && (entity != p)){
                        LivingEntity enti = (LivingEntity) entity;
                        enti.damage(4,p);
                    }
                }
            }
            if((hb.getType() == Material.FIRE) && (hb.getRelative(BlockFace.DOWN).getType() == Material.NETHERRACK)){
                createExplosion(hb.getRelative(BlockFace.DOWN).getLocation());
            }
            else if (hb.getRelative(BlockFace.DOWN).getType() == Material.NETHERRACK){
                createExplosion(hb.getRelative(BlockFace.DOWN).getLocation());
            }
            else if (hb.getLocation().getY() >= 80.0D){
                hb.setType(Material.NETHERRACK);
                hb.getRelative(BlockFace.UP).setType(Material.FIRE);
            }
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        List<Block> explodeblocks = e.blockList();
        for(Block b : explodeblocks)
            if(b.getType() == Material.NETHERRACK)
                createExplosion(b.getLocation());
    }

    @EventHandler
    public void createExplosion(Location loc){
        TNTPrimed tnt = (TNTPrimed) loc.getWorld().spawn(loc, TNTPrimed.class);
        tnt.setFuseTicks(0);
        tnt.setYield(2.5F);
    }

}
