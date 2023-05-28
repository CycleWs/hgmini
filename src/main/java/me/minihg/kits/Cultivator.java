package me.minihg.kits;

import org.bukkit.CropState;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
<<<<<<< Updated upstream
=======
import org.bukkit.material.Dye;
import org.bukkit.material.MaterialData;
>>>>>>> Stashed changes

import java.util.ArrayList;

public class Cultivator implements Listener {
    public static ArrayList<Player> cultivatorList = new ArrayList<>();
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
<<<<<<< Updated upstream
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
=======
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            if(p.getItemInHand().getType() == Material.SAPLING){
                e.getBlock().setType(Material.AIR);
                e.getPlayer().getWorld().generateTree(e.getBlock().getLocation(), TreeType.TREE);
            }
            if(p.getItemInHand().getType() == Material.SEEDS){
                e.getBlock().setData((byte) 7);
            }
            if(p.getItemInHand().getType() == Material.INK_SACK){
                e.getBlock().setData((byte)11);
            }
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit CULTIVATOR");
        p.sendMessage("§aPlante uma semente e faça ela crescer instantaneamente");
>>>>>>> Stashed changes
    }
}
