package me.minihg.commands;

import me.minihg.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Commands implements CommandExecutor {

    private HashMap<Player, Long> commandCooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender player, Command cmd, String str, String[] args) {
        if (!(player instanceof Player)) return false;
        Player p = (Player) player;
        return false;
    }
//        if(cmd.getName().equalsIgnoreCase("kit")){
//            if(commandCooldown.containsKey(p) && !(System.currentTimeMillis() >= commandCooldown.get(p))){
//                p.sendMessage("§cAguarde para usar o comando novamente! "+convert(p)+" Segundos");
//                return false;
//            }else commandCooldown.remove(p);
//
//            commandCooldown.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(2));
//
//            if(args.length == 0){
//                p.sendMessage("");
//                p.sendMessage("§aAchilles - Boxer - Camel - Cannibal - Cultivator - Demoman");
//                p.sendMessage("§aExplorer - Fisherman - Grandpa - Iroman - Lumberjack - Miner");
//                p.sendMessage("§aMonk - Ninja - Poseidon - Reaper - Snail - Stomper - Switcher");
//                p.sendMessage("§aThor - Titan - Turtle - Viper - Worm");
//                p.sendMessage("");
//                return true;
//            }
//            if(args.length == 1){
//                if(args[0].equalsIgnoreCase("grandpa")){
//                    KitSelector.kitMap.put(p,9);
//                    Grandpa.getItems(p);
//                    Grandpa.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("boxer")){
//                    KitSelector.kitMap.put(p,2);
//                    Boxer.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("camel")){
//                    Camel.camelList.add(p);
//                    KitSelector.kitMap.put(p,3);
//                    Camel.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("cannibal")){
//                    KitSelector.kitMap.put(p,4);
//                    Cannibal.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("cultivator")){
//                    KitSelector.kitMap.put(p,4);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("demoman")){
//                    KitSelector.kitMap.put(p,6);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("explorer")){
//                    KitSelector.kitMap.put(p,8);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("fireman")){
//                    KitSelector.kitMap.put(p,9);
//                    Fireman.fireman(p);
//                    Fireman.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("fisherman")){
//                    KitSelector.kitMap.put(p,10);
//                    Fisherman.fisherman(p);
//                    Fisherman.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("grandpa")){
//                    KitSelector.kitMap.put(p,11);
//                    Grandpa.grandpa(p);
//                    Grandpa.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("iroman")){
//                    KitSelector.kitMap.put(p,12);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("lumberjack")){
//                    KitSelector.kitMap.put(p,13);
//                    Lumberjack.lumberjack(p);
//                    Lumberjack.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("miner")){
//                    KitSelector.kitMap.put(p,14);
//                    Miner.miner(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("monk")){
//                    KitSelector.kitMap.put(p,15);
//                    Monk.monk(p);
//                    Monk.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("ninja")){
//                    KitSelector.kitMap.put(p,16);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("poseidon")){
//                    KitSelector.kitMap.put(p,17);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("reaper")){
//                    KitSelector.kitMap.put(p,18);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("snail")){
//                    KitSelector.kitMap.put(p,19);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("stomper")){
//                    KitSelector.kitMap.put(p,20);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("thor")){
//                    KitSelector.kitMap.put(p,21);
//                    Thor.thor(p);
//                    Thor.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("titan")){
//                    KitSelector.kitMap.put(p,22);
//                    Titan.titan(p);
//                    Titan.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("turtle")){
//                    KitSelector.kitMap.put(p,23);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("viper")){
//                    KitSelector.kitMap.put(p,24);
//                    Cultivator.getKitDescription(p);
//                    return true;
//                }
//                if(args[0].equalsIgnoreCase("worm")){
//                    KitSelector.kitMap.put(p,25);
//                    Worm.getKitDescription(p);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    private Long convert (Player p){
//        long tempo = System.currentTimeMillis() - commandCooldown.get(p);
//        return 1 + TimeUnit.MILLISECONDS.toSeconds(tempo) * -1;
//    }
}
