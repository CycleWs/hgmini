package me.minihg.kits;

import me.minihg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class KitSelector {
    public static HashMap<Player, Integer> kitMap = new HashMap<>();
    private static List<Integer> kits = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
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
            Thor.getKitDescription(p);
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
        int f = (arrayOfPlayer = Main.playersOnline.toArray(new Player[0])).length;
            for(int i = 0; i < f; ++i) {
                int randomKit = random.nextInt(kits.size() - 1);
                Player p = arrayOfPlayer[i];
                int kitForPlayer = kits.get(randomKit);
                kitMap.put(p,kitForPlayer);
                verifyPlayerKit(p);
                kits.remove(randomKit);
            }
    }
}
