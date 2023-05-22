package me.minihg;

import me.minihg.api.Files;
import me.minihg.events.InGameEvents;
import me.minihg.events.PlayerEvents;
import me.minihg.events.PreGameEvents;
import me.minihg.events.ServerEvents;
import me.minihg.kits.*;
import me.minihg.stages.InGameStage;
import me.minihg.stages.InvincibilityStage;
import me.minihg.stages.PreGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin{

    public static Main plugin;
    public static Plugin instance;

    public static Main getInstance(){
        return getPlugin(Main.class);
    }



    //----------PreGameInfos--------------------
    public static boolean PreGame = true;
    public static Integer StartTime = 15;
    public static Integer MinPlayers = 1 ;
    //----------PreGameInfos--------------------
    //----------InvincibilityInfo---------------
    public static boolean Invincibility = false;
    public static Integer InvincibilityTime = 5;
    //----------InvincibilityInfo---------------
    //----------GameInfos-----------------------
    public static boolean inGame = false;
    public static Integer InGameTime = 0;
    public static boolean Ending = false;
    public static List<String> playersOnline = new ArrayList<>();
    //----------GameInfos-----------------------
    public static ArrayList<String> Watch = new ArrayList<>();
    //----------FeastInfos-----------------------
    public static Boolean feastChests = true;
    public static Boolean feastProtection = true;
    public static Boolean Feast = true;
    //----------FeastInfos-----------------------
        @Override
        public void onEnable(){
            instance = this;
            this.Files();
            Bukkit.getWorld("world").setSpawnLocation(0,100,0);
            registerEvents();
            registerKitEvents();
            Cooldown.setupCooldown();
            new PreGame();

            ShapelessRecipe cocoaSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
                    .addIngredient(Material.INK_SACK, 3)
                    .addIngredient(Material.BOWL);

            ShapelessRecipe cactusSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
                    .addIngredient(Material.CACTUS, 0)
                    .addIngredient(Material.BOWL);
            this.getServer().addRecipe(cocoaSoup);
            this.getServer().addRecipe(cactusSoup);
    }

    public void onLoad(){
            //deleteWorld(new File("world"));
    }

    public void registerEvents(){
            Bukkit.getPluginManager().registerEvents(new ServerEvents(),this);
            Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
            Bukkit.getPluginManager().registerEvents(new PreGameEvents(), this);
            Bukkit.getPluginManager().registerEvents(new InGameEvents(), this);
    }
    public void registerKitEvents(){
            //Bukkit.getPluginManager().registerEvents(new Achilles(), this);
            //Bukkit.getPluginManager().registerEvents(new Switcher(), this); //- DAR ITEM
            //Bukkit.getPluginManager().registerEvents(new Ninja(), this);
            //Bukkit.getPluginManager().registerEvents(new Camel(), this);
            //Bukkit.getPluginManager().registerEvents(new Explorer(), this);
            //Bukkit.getPluginManager().registerEvents(new Fireman(), this); - DAR ITEM
            //Bukkit.getPluginManager().registerEvents(new Grandpa(), this); - DAR ITEM E TESTAR
            //Bukkit.getPluginManager().registerEvents(new Miner(), this); - FZR
            //Bukkit.getPluginManager().registerEvents(new Lumberjack(), this); - AJEITAR MADEIRAS QUEBRADAS
            //Bukkit.getPluginManager().registerEvents(new Poseidon(), this);
            //Bukkit.getPluginManager().registerEvents(new Stomper(), this);
            //Bukkit.getPluginManager().registerEvents(new Fisherman(), this); - FZR
            //Bukkit.getPluginManager().registerEvents(new Reaper(), this);
            //Bukkit.getPluginManager().registerEvents(new Viper(), this);
            //Bukkit.getPluginManager().registerEvents(new Boxer(), this); - ARRUMAR
            Bukkit.getPluginManager().registerEvents(new Worm(), this);
            //Bukkit.getPluginManager().registerEvents(new Thor(), this);
            //Bukkit.getPluginManager().registerEvents(new Digger(), this); - FZR
            //Bukkit.getPluginManager().registerEvents(new Demoman(), this);
            //Bukkit.getPluginManager().registerEvents(new Cannibal(), this);
            //Bukkit.getPluginManager().registerEvents(new Cultivator(), this);
            //Bukkit.getPluginManager().registerEvents(new Snail(), this);
            //Bukkit.getPluginManager().registerEvents(new Monk(), this);
            //Bukkit.getPluginManager().registerEvents(new Turtle(), this);
            //Bukkit.getPluginManager().registerEvents(new IronMan(), this); - ARRUMAR
    }

    public static void startMatch(){
        me.minihg.stages.PreGame.cancel();
        Main.PreGame = false;
        Main.Invincibility = true;
        new InvincibilityStage();
    }

    public static void finishInvencibility(){
            Main.Invincibility = false;
            InvincibilityStage.cancel();
            Main.inGame = true;
            InGameStage.Start(0);
    }



    private void deleteWorld(File file){
            if(file.isDirectory()){
                String[] list = file.list();

                for (int i = 0; i< list.length; i++){
                    deleteWorld(new File(file, list[i]));
                }
            }
            file.delete();
    }

    public void Files(){
        instance = this;
        new Files();
    }

    public static String timerFormat(int i){
            int millis = i * 1000;
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
            return dateFormat.format(Integer.valueOf(millis));
    }

    public static void updateScore(){
            for(Player p : Bukkit.getOnlinePlayers()){
                Scoreboard scoreboard = p.getScoreboard();
                Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
                if(Main.PreGame){
                    objective.setDisplayName("§cIniciando em: "+timerFormat(Main.StartTime));
                }
                if (Main.Invincibility){
                    objective.setDisplayName("§cInvencível por: "+timerFormat(Main.InvincibilityTime));
                }
                if (Main.inGame){
                    objective.setDisplayName("§cTempo de jogo: "+timerFormat(Main.InGameTime));
                }

                objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
            }
    }

    public static void sendScoreboard(Player p){
        ScoreboardManager scoremanager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoremanager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("board","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cIniciando em: "+timerFormat(Main.StartTime));
        objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
        p.setScoreboard(scoreboard);
    }
}
