package me.minihg.commands;

import me.minihg.kits.*;
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
        if(!(player instanceof Player)) return false;
        Player p = (Player) player;

        if(cmd.getName().equalsIgnoreCase("kit")){
            if(commandCooldown.containsKey(p) && !(System.currentTimeMillis() >= commandCooldown.get(p))){
                p.sendMessage("§cAguarde para usar o comando novamente! "+convert(p)+" Segundos");
                return false;
            }else commandCooldown.remove(p);

            commandCooldown.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(2));

            if(args.length == 0){
                p.sendMessage("");
                p.sendMessage("§cAchilles - Boxer - Camel - Cannibal - Cultivator - Demoman");
                p.sendMessage("§cExplorer - Fisherman - Grandpa - Iroman - Lumberjack - Miner");
                p.sendMessage("§cMonk - Ninja - Poseidon - Reaper - Snail - Stomper - Switcher");
                p.sendMessage("§cThor - Titan - Turtle - Viper - Worm");
                p.sendMessage("");
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("achilles")){
                    Achilles.achillesList.add(p);
                    KitSelector.kitMap.put(p,1);
                    Achilles.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("boxer")){
                    Boxer.boxerList.add(p);
                    KitSelector.kitMap.put(p,2);
                    Boxer.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("camel")){
                    Camel.camelList.add(p);
                    KitSelector.kitMap.put(p,3);
                    Camel.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("cannibal")){
                    Cannibal.cannibalList.add(p);
                    KitSelector.kitMap.put(p,4);
                    Cannibal.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("Cultivator")){
                    Cultivator.cultivatorList.add(p);
                    KitSelector.kitMap.put(p,5);
                    Cultivator.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("Demoman")){
                    Demoman.demomanList.add(p);
                    KitSelector.kitMap.put(p,6);
                    Demoman.getKitDescription();
                    return true;
                }
                if(args[0].equalsIgnoreCase("snail")){
                    KitSelector.kitMap.put(p,18);
                    Miner.miner(p);
                    return true;
                }
                if(args[0].equalsIgnoreCase("turtle")){
                    KitSelector.kitMap.put(p,23);
                    return true;
                }
            }
        }
        return false;
    }
    private Long convert (Player p){
        long tempo = System.currentTimeMillis() - commandCooldown.get(p);
        return 1 + TimeUnit.MILLISECONDS.toSeconds(tempo) * -1;
    }
}
