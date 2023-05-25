package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Explorer implements Listener {

    public static ArrayList<Player> explorerList = new ArrayList<>();

    public static void getKitDescription(Player p){
        p.sendMessage("§cSaiba as localizações exatas de mini-feasts e");
        p.sendMessage("§cdo bonus Feast");
    }
}
