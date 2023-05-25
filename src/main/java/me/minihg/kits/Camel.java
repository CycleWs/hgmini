package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Camel implements Listener {
    public static ArrayList<Player> camelList = new ArrayList<>();

    @EventHandler
    public void onPoseidon(PlayerMoveEvent e){
        Player p = e.getPlayer();
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED,120,1);
        PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION,120,0);
        PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,120,0);

        if(((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.containsValue(2)) && Main.inGame)){
            if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.DESERT)){
                p.addPotionEffect(speed);
                p.addPotionEffect(regeneration);
                p.addPotionEffect(resistance);
            }
        }
    }
    public static void getKitDescription(Player p){
        p.sendMessage("§cSeja mais forte quando estiver");
        p.sendMessage("§cNos biomas de deserto");
    }
}
