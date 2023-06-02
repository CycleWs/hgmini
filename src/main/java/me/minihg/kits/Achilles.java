package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class Achilles implements Listener {
    int value = 0;

    public Achilles() {
    }



    @EventHandler
    public void achillesEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            double anySword = 2.0;
            double woodSword = 6.0;
            Player p = (Player)e.getEntity();
            Player damager = (Player)e.getDamager();
            if (KitSelector.kitMap.containsKey(p) && (Integer)KitSelector.kitMap.get(p) == this.value && Main.inGame) {
                if (damager.getInventory().getItemInHand().getType() != Material.WOOD_SWORD
                        && damager.getInventory().getItemInHand().getType() != Material.WOOD_AXE
                        && damager.getInventory().getItemInHand().getType() != Material.WOOD_HOE
                        && damager.getInventory().getItemInHand().getType() != Material.WOOD_PICKAXE
                        && damager.getInventory().getItemInHand().getType() != Material.WOOD_SPADE) {
                    e.setDamage(anySword);
                    damager.sendMessage("§cO jogador que você está lutando é §aACHILLES §cuse algum item de madeira para ser mais efetivo");
                    if (this.isCritical(damager)) {
                        e.setDamage(anySword + 1.0);
                        damager.sendMessage("§cO jogador que você está lutando é §aACHILLES §cuse algum item de madeira para ser mais efetivo");
                    }
                } else {
                    e.setDamage(woodSword);
                    if (this.isCritical(damager)) {
                        e.setDamage(woodSword + 1.0);
                    }
                }
            }
        }
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit ACHILLES");
        p.sendMessage("§aSeja quase imune a qualquer tipo de espada, porém receberá um dano mortal de itens de madeira");
    }

    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0.0F && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }
}
