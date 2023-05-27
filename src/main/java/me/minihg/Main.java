package me.minihg;

import me.minihg.api.Files;
import me.minihg.events.InGameEvents;
import me.minihg.events.PlayerEvents;
import me.minihg.events.PreGameEvents;
import me.minihg.events.ServerEvents;
import me.minihg.kits.Achilles;
import me.minihg.kits.Cooldown;
import me.minihg.kits.Ninja;
import me.minihg.kits.Switcher;
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
<<<<<<< Updated upstream
    public static Integer InGameTime = 0;
    public static boolean Ending = false;
=======
    public static Integer inGameTime = 0;
    public static boolean finalArena = false;
    public static boolean ending = false;
>>>>>>> Stashed changes
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
            Bukkit.getWorld("world").setSpawnLocation(0,100,0);
            Cooldown.setupCooldown();
            instance = this;
            this.Files();
            registerEvents();
            registerKitEvents();
<<<<<<< Updated upstream
            Cooldown.setupCooldown();
=======
            registerCommands();
>>>>>>> Stashed changes
            new PreGame();
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
<<<<<<< Updated upstream
            Bukkit.getPluginManager().registerEvents(new Achilles(), this);
            Bukkit.getPluginManager().registerEvents(new Switcher(), this);
            Bukkit.getPluginManager().registerEvents(new Ninja(), this);
=======
              Bukkit.getPluginManager().registerEvents(new Achilles(), this);
//              Bukkit.getPluginManager().registerEvents(new Switcher(), this); //- DAR ITEM
              Bukkit.getPluginManager().registerEvents(new Ninja(), this);//tirar erros
              Bukkit.getPluginManager().registerEvents(new Camel(), this);
              Bukkit.getPluginManager().registerEvents(new Explorer(), this);
              Bukkit.getPluginManager().registerEvents(new Fireman(), this); //- DAR ITEM
              Bukkit.getPluginManager().registerEvents(new Grandpa(), this); //- DAR ITEM E TESTAR
              Bukkit.getPluginManager().registerEvents(new Miner(), this);
              Bukkit.getPluginManager().registerEvents(new Lumberjack(), this);
              Bukkit.getPluginManager().registerEvents(new Poseidon(), this);
              Bukkit.getPluginManager().registerEvents(new Stomper(), this);
              Bukkit.getPluginManager().registerEvents(new Fisherman(), this);// - FZR
              Bukkit.getPluginManager().registerEvents(new Reaper(), this);//tirar erros
              Bukkit.getPluginManager().registerEvents(new Viper(), this);//tirar erros
              Bukkit.getPluginManager().registerEvents(new Boxer(), this); //- ARRUMAR
              Bukkit.getPluginManager().registerEvents(new Worm(), this);
              Bukkit.getPluginManager().registerEvents(new Thor(), this);
//              Bukkit.getPluginManager().registerEvents(new Digger(), this); //- FZR
              Bukkit.getPluginManager().registerEvents(new Demoman(), this);
              Bukkit.getPluginManager().registerEvents(new Cannibal(), this);
              Bukkit.getPluginManager().registerEvents(new Cultivator(), this);
              Bukkit.getPluginManager().registerEvents(new Snail(), this);//tirar error
              Bukkit.getPluginManager().registerEvents(new IronMan(), this);
              Bukkit.getPluginManager().registerEvents(new Monk(), this);
              Bukkit.getPluginManager().registerEvents(new Titan(), this);
              Bukkit.getPluginManager().registerEvents(new Turtle(), this);
>>>>>>> Stashed changes
    }

    public static void startMatch(){
        me.minihg.stages.PreGame.cancel();
        Main.preGame = false;
        Main.invincibility = true;
        new InvincibilityStage();
    }

    public static void finishInvencibility(){
            Main.invincibility = false;
            InvincibilityStage.cancel();
            Main.inGame = true;
            InGameStage.Start(0);
    }

    public void worldBorder(){
        WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
        border.setCenter(0, 0);
        border.setSize(500);
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

            if (objective == null) {
                objective = scoreboard.registerNewObjective("ObjectiveName", "Criteria");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            }

            if(Main.preGame){
                objective.setDisplayName("§cIniciando em: "+timerFormat(Main.startTime));
            }
            if (Main.invincibility){
                objective.setDisplayName("§cInvencível por: "+timerFormat(Main.invincibilityTime));
            }
            if (Main.inGame){
                objective.setDisplayName("§cTempo de jogo: "+timerFormat(Main.inGameTime));
            }

            objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
        }
    }

    public static void sendScoreboard(Player p){
        ScoreboardManager scoremanager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoremanager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("board","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§cIniciando em: "+timerFormat(Main.startTime));
        objective.getScore("Jogadores: ").setScore(Bukkit.getOnlinePlayers().size());
        p.setScoreboard(scoreboard);
    }

    ShapelessRecipe cocoaSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
            .addIngredient(Material.INK_SACK, 3)
            .addIngredient(Material.BOWL);

    ShapelessRecipe cactusSoup = (new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1)))
            .addIngredient(Material.CACTUS, 0)
            .addIngredient(Material.BOWL);


}
