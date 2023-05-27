package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class Cultivator implements Listener {
    public static ArrayList<Player> cultivatorList = new ArrayList<>();
    int value = 4;
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            if(e.getBlock().getType() == Material.SAPLING){
                e.getBlock().setType(Material.AIR);
                e.getPlayer().getWorld().generateTree(e.getBlock().getLocation(), TreeType.TREE);
            }
            else{
                e.getBlock().setData((byte) 7);
            }
        }

    }
    @EventHandler
    public void onInt(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            if(e.getClickedBlock() == null)
                return;
            Block b = e.getClickedBlock().getRelative(BlockFace.UP);
            if(b.getType() == Material.CROPS){
                BlockState blockState = b.getState();
                MaterialData data = blockState.getData();
                Crops crops = new Crops(data.getItemType(), data.getData());
                crops.setState(CropState.RIPE);
                blockState.setData(crops);
                blockState.update(true);
                }
            }
        }
    public static void getKitDescription(Player p){
        p.sendMessage("§aPlante uma semente e faça ela crescer instantaneamente");
    }

}
