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
import org.bukkit.WorldBorder;
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

<<<<<<< Updated upstream
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
=======
    //----------PreGameInfos--------------------
    public static boolean preGame = true;
    public static Integer startTime = 120;
    public static Integer minPlayers = 5;
    //----------PreGameInfos--------------------
    //----------InvincibilityInfo---------------
    public static boolean invincibility = false;
    public static Integer invincibilityTime = 120;
>>>>>>> Stashed changes
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
            this.worldBorder();
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
    }

    public static void startMatch(){
        me.minihg.stages.PreGame.cancel();
        Main.PreGame = false;
        Main.Invincibility = true;
        new InvincibilityStage();
    }

    public void worldBorder(){
        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);
        wb.setSize(400);
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
<<<<<<< Updated upstream

    public static void sendScoreboard(Player p){
=======
    public static void sendScoreboard(Player p) {
>>>>>>> Stashed changes
        ScoreboardManager scoremanager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoremanager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("board","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cIniciando em: "+timerFormat(Main.StartTime));
        objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
        p.setScoreboard(scoreboard);
    }
<<<<<<< Updated upstream
}
=======


}
>>>>>>> Stashed changes
