//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Explorer implements Listener {
    int value = 6;

    public Explorer() {
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§cSaiba as localizações exatas de mini-feasts e");
        p.sendMessage("§cdo bonus Feast");
    }
}
