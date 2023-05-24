package me.minihg.kits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Demoman implements Listener {
    public static ArrayList<Player> demomanList = new ArrayList<>();
    private static List<Block> traps = new ArrayList<Block>();
    //@Override
    public ItemStack[] getItems(){
        return new ItemStack[]{
                new ItemStack(Material.GRAVEL,8), new ItemStack(Material.STONE_PLATE,8)
        };
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Block placed = e.getBlock();
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(5))){
            if(placed.getType() != Material.STONE_PLATE)
                return;
            if(placed.getRelative(BlockFace.DOWN).getType() != Material.GRAVEL)
                return;
            traps.add(placed);
        }

    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(traps.contains(e.getBlock()))
            traps.remove(e.getBlock());
    }
    @EventHandler
    public void onExplode(BlockExplodeEvent e){
        if(traps.contains(e.getBlock()))
            traps.remove(e.getBlock());
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!(e.getAction() == Action.PHYSICAL))
            return;
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        Location ex = b.getRelative(BlockFace.UP).getLocation();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(5))){
            p.sendMessage("§cVocê não pode ativar sua propria armadilha");
        }else{
            if(b.getType() == Material.STONE_PLATE && traps.contains(b)){
                traps.remove(b);
                b.setType(Material.AIR);
                b.getWorld().createExplosion(ex,5F);
            }
        }

    }
    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cPlante armadilhas no chão que explodirão");
        list.add("§cquando seus inimigos pisarem nela");
        return list;
    }

}
