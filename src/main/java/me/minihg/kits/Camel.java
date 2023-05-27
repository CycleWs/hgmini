package me.minihg.kits;

import java.util.ArrayList;
import me.minihg.Main;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Camel implements Listener {
    public static ArrayList<Player> camelList = new ArrayList();
    int value = 2;

    public Camel() {
    }

    @EventHandler
    public void onCamel(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 120, 1);
        PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 120, 0);
        PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0);
        if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && Main.inGame && p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.DESERT)) {
            p.addPotionEffect(speed);
            p.addPotionEffect(regeneration);
            p.addPotionEffect(resistance);
        }

    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit CAMEL");
        p.sendMessage("§aSeja mais forte quando estiver nos biomas de deserto");
    }
}
