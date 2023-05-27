package me.minihg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.minihg.api.Files;
import me.minihg.commands.Commands;
import me.minihg.events.InGameEvents;
import me.minihg.events.PlayerEvents;
import me.minihg.events.PreGameEvents;
import me.minihg.events.ServerEvents;
import me.minihg.events.UndroppableItens;
import me.minihg.kits.Achilles;
import me.minihg.kits.Boxer;
import me.minihg.kits.Camel;
import me.minihg.kits.Cannibal;
import me.minihg.kits.Cooldown;
import me.minihg.kits.Cultivator;
import me.minihg.kits.Demoman;
import me.minihg.kits.Explorer;
import me.minihg.kits.Fireman;
import me.minihg.kits.Fisherman;
import me.minihg.kits.Grandpa;
import me.minihg.kits.IronMan;
import me.minihg.kits.Lumberjack;
import me.minihg.kits.Miner;
import me.minihg.kits.Monk;
import me.minihg.kits.Ninja;
import me.minihg.kits.Poseidon;
import me.minihg.kits.Reaper;
import me.minihg.kits.Snail;
import me.minihg.kits.Stomper;
import me.minihg.kits.Thor;
import me.minihg.kits.Titan;
import me.minihg.kits.Turtle;
import me.minihg.kits.Viper;
import me.minihg.kits.Worm;
import me.minihg.stages.InGameStage;
import me.minihg.stages.InvincibilityStage;
import me.minihg.stages.PreGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin {
    public static Main plugin;
    public static Plugin instance;
    //----------PreGameInfos--------------------
    public static boolean preGame = true;
    public static Integer startTime = 30;
    public static Integer minPlayers = 1 ;
    //----------PreGameInfos--------------------
    //----------InvincibilityInfo---------------
    public static boolean invincibility = false;
    public static Integer invincibilityTime = 10;
    //----------InvincibilityInfo---------------
    //----------GameInfos-----------------------
    public static boolean inGame = false;
    public static Integer inGameTime = 0;
    public static boolean finalArena = false;
    public static boolean ending = false;
    public static List<String> playersOnline = new ArrayList<>();
    //----------GameInfos-----------------------
    //----------FeastInfos-----------------------
    public static Boolean feastChests = true;
    public static Boolean feastProtection = true;
    public static Boolean Feast = true;
    //----------FeastInfos-----------------------

    public static ArrayList<String> Watch = new ArrayList<>();
    ShapelessRecipe cocoaSoup;
    ShapelessRecipe cactusSoup;

    public Main() {
        this.cocoaSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
                .addIngredient(Material.INK_SACK, 3).addIngredient(Material.BOWL);
        this.cactusSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
                .addIngredient(Material.CACTUS, 0).addIngredient(Material.BOWL);
    }

    public static Main getInstance() {
        return (Main)getPlugin(Main.class);
    }

    public void onEnable() {
        Bukkit.getWorld("world").setSpawnLocation(0, 100, 0);
        Cooldown.setupCooldown();
        instance = this;
        this.Files();
        this.registerEvents();
        this.registerKitEvents();
        this.registerCommands();
        new PreGame();
        this.getServer().addRecipe(this.cocoaSoup);
        this.getServer().addRecipe(this.cactusSoup);
        this.worldBorder();
    }

    public void onLoad() {
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ServerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PreGameEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InGameEvents(), this);
        Bukkit.getPluginManager().registerEvents(new UndroppableItens(), this);
    }

    public void registerCommands() {
        this.getCommand("kit").setExecutor(new Commands());
    }

    public void registerKitEvents() {
        Bukkit.getPluginManager().registerEvents(new Achilles(), this);
        Bukkit.getPluginManager().registerEvents(new Ninja(), this);
        Bukkit.getPluginManager().registerEvents(new Camel(), this);
        Bukkit.getPluginManager().registerEvents(new Explorer(), this);
        Bukkit.getPluginManager().registerEvents(new Fireman(), this);
        Bukkit.getPluginManager().registerEvents(new Grandpa(), this);
        Bukkit.getPluginManager().registerEvents(new Miner(), this);
        Bukkit.getPluginManager().registerEvents(new Lumberjack(), this);
        Bukkit.getPluginManager().registerEvents(new Poseidon(), this);
        Bukkit.getPluginManager().registerEvents(new Stomper(), this);
        Bukkit.getPluginManager().registerEvents(new Fisherman(), this);
        Bukkit.getPluginManager().registerEvents(new Reaper(), this);
        Bukkit.getPluginManager().registerEvents(new Viper(), this);
        Bukkit.getPluginManager().registerEvents(new Boxer(), this);
        Bukkit.getPluginManager().registerEvents(new Worm(), this);
        Bukkit.getPluginManager().registerEvents(new Thor(), this);
        Bukkit.getPluginManager().registerEvents(new Demoman(), this);
        Bukkit.getPluginManager().registerEvents(new Cannibal(), this);
        Bukkit.getPluginManager().registerEvents(new Cultivator(), this);
        Bukkit.getPluginManager().registerEvents(new Snail(), this);
        Bukkit.getPluginManager().registerEvents(new IronMan(), this);
        Bukkit.getPluginManager().registerEvents(new Monk(), this);
        Bukkit.getPluginManager().registerEvents(new Titan(), this);
        Bukkit.getPluginManager().registerEvents(new Turtle(), this);
    }

    public static void startMatch() {
        PreGame.cancel();
        preGame = false;
        invincibility = true;
        new InvincibilityStage();
    }

    public static void finishInvencibility() {
        invincibility = false;
        InvincibilityStage.cancel();
        inGame = true;
        InGameStage.Start(0);
    }

    public void worldBorder() {
        WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
        border.setCenter(0.0, 0.0);
        border.setSize(500.0);
    }

    private void deleteWorld(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();

            for(int i = 0; i < list.length; ++i) {
                this.deleteWorld(new File(file, list[i]));
            }
        }

        file.delete();
    }

    public void Files() {
        instance = this;
        new Files();
    }

    public static String timerFormat(int i) {
        int millis = i * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(millis);
    }

    public static void updateScore() {
        Objective objective;
        for(Iterator var0 = Bukkit.getOnlinePlayers().iterator(); var0.hasNext(); objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size())) {
            Player p = (Player)var0.next();
            Scoreboard scoreboard = p.getScoreboard();
            objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
            if (objective == null) {
                objective = scoreboard.registerNewObjective("ObjectiveName", "Criteria");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            }

            if (preGame) {
                objective.setDisplayName("§cIniciando em: " + timerFormat(startTime));
            }

            if (invincibility) {
                objective.setDisplayName("§cInvencível por: " + timerFormat(invincibilityTime));
            }

            if (inGame) {
                objective.setDisplayName("§cTempo de jogo: " + timerFormat(inGameTime));
            }
        }

    }

    public static void sendScoreboard(Player p) {
        ScoreboardManager scoremanager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoremanager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("board", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cIniciando em: " + timerFormat(startTime));
        objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
        p.setScoreboard(scoreboard);
    }
}