package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class Boxer implements Listener {
    int value = 1;

    public Boxer() {
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player)e.getDamager();
            Player damaged = (Player)e.getEntity();
            double damageHand = 5.0;
            if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && Main.inGame && p.getInventory().getItemInHand().getType() == Material.AIR) {
                e.setDamage(damageHand);
                if (this.isCritical(p)) {
                    e.setDamage(damageHand + 1.0);
                }
            }

            if (KitSelector.kitMap.containsKey(damaged) && (Integer)KitSelector.kitMap.get(damaged) == this.value && e.getDamage() > 1.0) {
                e.setDamage(e.getDamage() - 1.0);
                if (this.isCritical(p)) {
                    e.setDamage(e.getDamage() - 1.0);
                }
            }
        }

    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§aReceba meio coração de dano a menos e dê dano de uma espada de pedra usando a mão");
    }

    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0.0F && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }
}
