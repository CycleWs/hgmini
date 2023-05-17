package me.minihg.kits;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Crops;

import java.util.ArrayList;

public class Cultivator implements Listener {
    public static ArrayList<Player> cultivatorList = new ArrayList<>();
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.SAPLING){
            e.getBlock().setType(Material.AIR);
            e.getPlayer().getWorld().generateTree(e.getBlock().getLocation(), TreeType.TREE);
        }
        else{
            e.getBlock().setData((byte) 7);
        }
    }
    @EventHandler
    public void onInt(PlayerInteractEvent e){
        if(e.getClickedBlock() == null)
            return;
        Block b = e.getClickedBlock().getRelative(BlockFace.UP);
        if(b.getType() == Material.CROPS){
            Crops c = (Crops)b.getState();
            c.setState(CropState.RIPE);
        }
    }
}