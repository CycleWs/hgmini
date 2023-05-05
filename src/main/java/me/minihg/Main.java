package me.minihg;

import me.minihg.events.InGameEvents;
import me.minihg.events.PlayerEvents;
import me.minihg.events.PreGameEvents;
import me.minihg.events.ServerEvents;
import me.minihg.kits.Achilles;
import me.minihg.kits.Ninja;
import me.minihg.kits.Switcher;
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

import java.io.File;
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
    public static Integer InvincibilityTime = 10;
    //----------InvincibilityInfo---------------
    //----------GameInfos-----------------------
    public static boolean inGame = false;
    public static Integer InGameTime = 0;
    public static boolean Ending = false;
    public static List<String> playersOnline = new ArrayList<>();
    //----------GameInfos-----------------------
    public static ArrayList<String> Watch = new ArrayList<>();
    public static List<Player> Cooldown = new ArrayList<>();

        @Override
        public void onEnable(){
            instance = this;
            Bukkit.getWorld("world").setSpawnLocation(0,100,0);
            registerEvents();
            registerKitEvents();
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
            Bukkit.getPluginManager().registerEvents(new Achilles(), this);
            Bukkit.getPluginManager().registerEvents(new Switcher(), this);
            Bukkit.getPluginManager().registerEvents(new Ninja(), this);
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


}
