package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Explorer implements Listener {

    public static ArrayList<Player> explorerList = new ArrayList<>();

    public static List<String> getKitDescription(){
        List<String> list = new ArrayList<>();
        list.add("§cSaiba as localizações exatas de mini-feasts e");
        list.add("§cdo bonus Feast");
        return list;
    }
}
