package me.minihg.kits;

import me.minihg.Main;
import me.minihg.item.ItensConfig;
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
    int value = 5;
    private static final List<Block> traps = new ArrayList<Block>();

    public static void getItems(Player p){
        ItemStack Bussola = new ItemStack(Material.COMPASS);
        ItemStack demoman = new ItensConfig(Material.GRAVEL, 8, (short) 0)
                .setName("§aBomba")
                .getItemStack();

        ItemStack demomanPlates = new ItensConfig(Material.STONE_PLATE, 8, (short)0)
                .setName("§aAtivador")
                .getItemStack();

        p.getInventory().clear();
        p.getInventory().addItem(demoman);
        p.getInventory().addItem(demomanPlates);
        p.getInventory().addItem(Bussola);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Block placed = e.getBlock();
        Player p = e.getPlayer();
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
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
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == value) && Main.inGame){
            p.sendMessage("§cVocê não pode ativar sua propria armadilha");
        }else{
            if(b.getType() == Material.STONE_PLATE && traps.contains(b)){
                traps.remove(b);
                b.setType(Material.AIR);
                b.getWorld().createExplosion(ex,5F);
            }
        }

    }
    public static void getKitDescription(Player p){
        p.sendMessage("§l§6Você recebeu o kit DEMOMAN");
        p.sendMessage("§aColoque armadilhas no chão que explodirão quando seus inimigos pisarem nela");
    }

}
