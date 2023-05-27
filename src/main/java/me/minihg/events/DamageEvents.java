package me.minihg.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class DamageEvents implements Listener {

    @EventHandler
    public void damageControl(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            ItemStack sword = p.getItemInHand();
            double damage = e.getDamage();
            double diamond = 6;
            double iron = 5;
            double stone = 4;
            double wood = 3;
            double gold = 3;

            //If sword has any Enchantment beside sharpness (Se tiver qualquer encantamento sem ser sharpness)
            if(sword.getEnchantments() != Enchantment.DAMAGE_ALL){
                if(damage == 8){
                    e.setDamage(diamond);
                }else if(damage == 12){
                    e.setDamage(diamond+ 1);
                }
                if(damage == 7){
                    e.setDamage(iron);
                }else if(damage == 10.5){
                    e.setDamage(iron + 1);
                }
                if(damage == 6){
                    e.setDamage(stone);
                }else if(damage == 9){
                    e.setDamage(stone + 1 );
                }
                if(damage == 5){
                    e.setDamage(wood);
                }else if(damage == 7.5){
                    e.setDamage(wood + 1);
                }
            }

            //If sword don't have any enchantment (Se a espada não tiver nenhum encantamento)
            if(p.getItemInHand().equals( new ItemStack(Material.DIAMOND_SWORD))){
                e.setDamage(diamond);
                if(isCritical(p)){
                    e.setDamage(diamond+ 1);
                }
            }
            if(p.getItemInHand().equals( new ItemStack(Material.IRON_SWORD))){
                e.setDamage(iron);
                if(isCritical(p)){
                    e.setDamage(iron + 1);
                }
            }
            if(p.getItemInHand().equals( new ItemStack(Material.STONE_SWORD))){
                e.setDamage(stone);
                if(isCritical(p)){
                    e.setDamage(stone + 1 );
                }
            }
            if(p.getItemInHand().equals( new ItemStack(Material.WOOD_SWORD))){
                e.setDamage(wood);
                if(isCritical(p)){
                    e.setDamage(wood + 1);
                }
            }
            if(p.getItemInHand().equals( new ItemStack(Material.GOLD_SWORD))){
                e.setDamage(gold);
                if(isCritical(p)){
                    e.setDamage(gold + 1);
                }
            }

            //If sword has sharpness(Se a espada tiver sharpness)
            if(sword.containsEnchantment(Enchantment.DAMAGE_ALL)){
                e.setDamage(damage - 2);
                if(isCritical(p)){
                    e.setDamage(damage*(1/1.5)-(5/6));
                }
            }
        }

    }

    @SuppressWarnings("deprecation")
    private boolean isCritical(Player p) {
        return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }
}
