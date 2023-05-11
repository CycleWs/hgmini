package me.minihg.kits;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Camel implements Listener {
    public static ArrayList<Player> camelList = new ArrayList<>();

    @EventHandler
    public void onPoseidon(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Material m = p.getLocation().getBlock().getType();
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED,120,1);
        PotionEffect regeneracao = new PotionEffect(PotionEffectType.REGENERATION,120,0);
        PotionEffect resistencia = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,120,0);

        if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.DESERT)){
            p.addPotionEffect(speed);
            p.addPotionEffect(regeneracao);
            p.addPotionEffect(resistencia);
        }
    }
}
