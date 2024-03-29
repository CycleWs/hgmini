package me.minihg.commands;

import me.minihg.Main;
import me.minihg.feastminifeast.MiniFeast;
import me.minihg.kits.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static me.minihg.feastminifeast.MiniFeast.locMF;
import static org.bukkit.Bukkit.getServer;

public class Commands implements CommandExecutor {

    private HashMap<Player, Long> commandCooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender player, Command cmd, String str, String[] args) {
        if (!(player instanceof Player)) return false;
        Player p = (Player) player;
        String command = cmd.getName();
        if(command.equalsIgnoreCase("mf")){
            return executeMfCmd(p);
        } else if (command.equalsIgnoreCase("spawn")) {
            return executeSpawnCmd(p,args);
        }

        //Admin Commands VV
        if(Main.playersAdmin.contains(((Player) player).getUniqueId())){
           if(command.equalsIgnoreCase("tempo")){
                return executeTimeCmd(p,args);
            }else if(command.equalsIgnoreCase("borda")){
                return executeWorldBorderCmd(p,args);
            } else if (command.equalsIgnoreCase("tpall")) {
                return executeTpAllCmd(p);
            }else if (command.equalsIgnoreCase("cc")){
                return executeClearChatCmd();
            }else if(command.equalsIgnoreCase("start")){
               return executeStartCmd(p);
           }else if(command.equalsIgnoreCase("toggle")){
               return excuteToggleChatCmd(args);
           }
        }else if (!(Main.playersAdmin.contains(p.getUniqueId()))) {
            p.sendMessage("§cVocê não tem permissão para usar este comando!");
        }else{
            p.sendMessage("§cOcorreu algum erro ao executar o comando!");
        }

        return false;
    }
    private boolean executeWorldBorderCmd(CommandSender player, String[] args) {
        Player p = (Player) player;
        if (args.length == 0) {
            p.sendMessage("§aDigite /borda <tamanho final em blocos, tempo em segundos> ");
        }else if (args.length == 1){
            int value = Integer.parseInt(args[0]);
            getServer().dispatchCommand(getServer().getConsoleSender(), "worldborder set "+value);
            return true;
        }else if(args.length == 2){
            int firstValue = Integer.parseInt(args[0]);
            int secondValue = Integer.parseInt(args[1]);
            getServer().dispatchCommand(getServer().getConsoleSender(), "worldborder set "+firstValue+" "+secondValue);
            return true;
        }
        return true;
        }
    private boolean executeMfCmd(CommandSender explorer){
            Player p = (Player) explorer;
            if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == Explorer.explorerValue)
                    && MiniFeast.spawned && Main.inGame){
                p.sendMessage("§aSua bússola está apontando para o mini feast");
                p.setCompassTarget(locMF);
            }else if(KitSelector.kitMap.get(p) != Explorer.explorerValue){
                p.sendMessage("§cVocê não é um Explorer!");
            }else if(!(MiniFeast.spawned)){
                p.sendMessage("§cO mini feast ainda não spawnou!");
            }else if(!(Main.inGame)){
                p.sendMessage("§cA partida ainda não iniciou!");
            }
            return true;
        }

    private boolean executeTimeCmd(CommandSender player, String[] args){
        Player p = (Player) player;
            if(args.length == 0){
                p.sendMessage("§aNumero em segundos para alterar o tempo da partida: ");
            }
            if((args.length == 1) && Main.preGame ){
                Main.startTime = Integer.parseInt(args[0]);
            }
            if((args.length == 1) && Main.invincibility){
                Main.invincibilityTime = Integer.parseInt(args[0]);
            }
            if((args.length == 1) && Main.inGame){
                Main.inGameTime = Integer.parseInt(args[0]);
            }
        return true;
    }
    private boolean executeTpAllCmd(CommandSender player){
        Player p = (Player) player;
            Location loc = p.getLocation();
            Player[] arrayOfPlayer;
            int f = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
            for(int i = 0; i < f; ++i) {
                Player px = arrayOfPlayer[i];
                px.teleport(loc);
            }
        return true;
    }
    private boolean executeClearChatCmd(){
        for(int i = 0; i < 50; ++i) {
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage("§aChat limpo");
        return true;
    }

    private boolean executeStartCmd(CommandSender player){
        Player p = (Player) player;
        if(!Main.invincibility && !Main.inGame && !Main.finalArena){
            Main.startMatch();
            Bukkit.broadcastMessage("§aA partida iniciou!");
            KitSelector.kitSelectorRandom();
        }else {
            p.sendMessage("§ca partida já iniciou!");
        }
        return true;
    }

    private boolean excuteToggleChatCmd(String[] args){
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("chat")){
                if(!Main.toggleChat){
                    Main.toggleChat = true;
                    Bukkit.broadcastMessage("§aO chat foi ativado!");
                }else{
                    Main.toggleChat = false;
                    Bukkit.broadcastMessage("§cO chat foi desativado!");
                }
            }
        }
        return true;
    }

    private boolean executeSpawnCmd(CommandSender player,String[] args){
        Player p = (Player) player;
        if((args.length == 1) && Main.playersAdmin.contains(p.getUniqueId())){
            if(args[0].equalsIgnoreCase("set")){
                double locX = p.getLocation().getX();
                double locY = p.getLocation().getY();
                double locZ = p.getLocation().getZ();
                Bukkit.getWorld("world").setSpawnLocation((int) locX, (int) locY, (int) locZ);
                Main.spawnWorld = Bukkit.getWorld("world").getSpawnLocation();
                p.sendMessage("§aSpawn foi atualizado");
            }
        }else if(args.length == 0){
            p.teleport(Main.spawnWorld);
        }else if((args.length == 1) && !(Main.playersAdmin.contains(p.getUniqueId()))){
            p.sendMessage("§cVocê não tem permissão para usar este comando!");
        }
        return true;
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
    private Long convert (Player p){
        long tempo = System.currentTimeMillis() - commandCooldown.get(p);
        return 1 + TimeUnit.MILLISECONDS.toSeconds(tempo) * -1;
    }
}
