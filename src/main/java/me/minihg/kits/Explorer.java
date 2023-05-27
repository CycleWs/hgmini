//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.minihg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Explorer implements Listener {
    public static int explorerValue = 6;

    public Explorer() {
    }

    public static void getKitDescription(Player p) {
        p.sendMessage("§l§6Você recebeu o kit EXPLORER");
        p.sendMessage("§aSaiba as localizações exatas de mini-feasts e do bonus Feast");
    }
}
