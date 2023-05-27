package me.minihg.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

public class KitSelector {
    public static HashMap<Player, Integer> kitMap = new HashMap<>();
    static Player[] arrayOfPlayer;

    private static void verifyPlayerKit(Player p){
        if((KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 0)){
            Achilles.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 1){
            Boxer.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 2){
            Camel.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 3){
            Cannibal.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 4){
            Cultivator.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 5){
            Demoman.getKitDescription(p);
            Demoman.getItems(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 6){
            Explorer.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 7){
            Fireman.getItems(p);
            Fireman.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 8){
            Fisherman.getItems(p);
            Fisherman.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 9){
            Grandpa.getItems(p);
            Grandpa.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 10){
            IronMan.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 11){
            Lumberjack.getItems(p);
            Lumberjack.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 12){
            Miner.getItems(p);
            Miner.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 13){
            Monk.getItems(p);
            Monk.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 14){
            Ninja.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 15){
            Poseidon.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 16){
            Reaper.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 17){
            Snail.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 18){
            Stomper.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 19){
            Thor.getItems(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 20){
            Titan.getItems(p);
            Titan.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 21){
            Turtle.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 22){
            Viper.getKitDescription(p);
        }else if(KitSelector.kitMap.containsKey(p) && KitSelector.kitMap.get(p) == 23){
            Worm.getKitDescription(p);
        }

    }

    public static void kitSelectorRandom(){
        Random random = new Random();
        int f = (arrayOfPlayer = Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
        for(int i = 0; i < f; ++i) {
            int randomKit = random.nextInt(23);
            Player p = arrayOfPlayer[i];
            if(kitMap.containsValue(randomKit)){
                Bukkit.broadcastMessage("");
            }else{
                kitMap.put(p,randomKit);
                verifyPlayerKit(p);
            }
            Bukkit.broadcastMessage(kitMap.toString());
        }

    }
}
