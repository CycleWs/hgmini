package me.minihg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import me.minihg.api.Files;
import me.minihg.commands.Commands;
import me.minihg.events.*;
import me.minihg.kits.*;
import me.minihg.stages.InGameStage;
import me.minihg.stages.InvincibilityStage;
import me.minihg.stages.PreGame;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static org.bukkit.Bukkit.getServer;

public class Main extends JavaPlugin {
    public static Main plugin;
    public static Plugin instance;
    //----------PreGameInfos--------------------
    public static boolean preGame = true;
    public static Integer startTime = 300;
    public static Integer minPlayers = 5 ;
    public static Integer maxPlayers = 20;
    //----------PreGameInfos--------------------
    //----------InvincibilityInfo---------------
    public static boolean invincibility = false;
    public static Integer invincibilityTime = 120;
    //----------InvincibilityInfo---------------
    //----------GameInfos-----------------------
    public static boolean inGame = false;
    public static Integer inGameTime = 120;
    public static boolean finalArena = false;
    public static boolean ending = false;
    public static HashMap<UUID, Integer> playerKills = new HashMap<>();
    public static List<Player> playersOnline = new ArrayList<>();
    public static List<UUID> playersAdmin = new ArrayList<>();
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
        getServer().dispatchCommand(getServer().getConsoleSender(), "worldborder center 0 0");
        getServer().dispatchCommand(getServer().getConsoleSender(), "worldborder damage buffer 1");
        getServer().dispatchCommand(getServer().getConsoleSender(), "worldborder damage amount 3");
        getServer().dispatchCommand(getServer().getConsoleSender(), "gamerule commandBlockOutput false");
        UUID yuki = UUID.fromString("61af26df-d7c2-4201-8a48-1f8c7f821250");
        UUID cycleWs = UUID.fromString("0d88d7ba-fad3-425d-8ce8-ee83be9e706b");
        UUID etcloide = UUID.fromString("8876ca6c-814d-47f1-bb0e-4253456de83c");
        if(!(((Main.playersAdmin.contains(yuki))
                || Main.playersAdmin.contains(cycleWs))
                || Main.playersAdmin.contains(etcloide))){
            Main.playersAdmin.add(yuki);
            Main.playersAdmin.add(cycleWs);
            Main.playersAdmin.add(etcloide);
        }

    }

    public void onLoad() {
        plugin = this;
        deleteWorld(new File ("world"));
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ServerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PreGameEvents(), this);
        Bukkit.getPluginManager().registerEvents(new UndroppableItens(), this);
        Bukkit.getPluginManager().registerEvents(new DamageEvents(), this);
    }

    public void registerCommands() {
        this.getCommand("tempo").setExecutor(new Commands());
        this.getCommand("tpall").setExecutor(new Commands());
        this.getCommand("mf").setExecutor(new Commands());
        this.getCommand("borda").setExecutor(new Commands());
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
        Bukkit.getPluginManager().registerEvents(new Scout(), this);
        Bukkit.getPluginManager().registerEvents(new Milkman(), this);
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
        World world = Bukkit.getWorld("world");
        world.setDifficulty(Difficulty.NORMAL);
        border.setCenter(0.0, 0.0);
        border.setSize(400.0);
    }

    private void deleteWorld(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();

            for(int i = 0; i < Objects.requireNonNull(list).length; ++i) {
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
        for(Iterator var0 = Bukkit.getOnlinePlayers().iterator(); var0.hasNext(); objective.getScore("Jogadores: ").setScore(playersOnline.size())) {
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
        objective.getScore("Jogadores: ").setScore(playersOnline.size());
        p.setScoreboard(scoreboard);
    }
}