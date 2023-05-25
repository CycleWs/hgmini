package me.minihg;

import me.minihg.api.Files;
import me.minihg.commands.Commands;
import me.minihg.events.*;
import me.minihg.kits.*;
import me.minihg.stages.InGameStage;
import me.minihg.stages.InvincibilityStage;
import me.minihg.stages.PreGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    public static Integer InvincibilityTime = 3;
    //----------InvincibilityInfo---------------
    //----------GameInfos-----------------------
    public static boolean inGame = false;
    public static Integer InGameTime = 0;
    public static boolean finalArena = false;
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
            registerCommands();
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
            Bukkit.getPluginManager().registerEvents(new UndroppableItens(), this);
    }
    public void registerCommands(){
            getCommand("kit").setExecutor(new Commands());
    }
    public void registerKitEvents(){
              Bukkit.getPluginManager().registerEvents(new Achilles(), this);
//            Bukkit.getPluginManager().registerEvents(new Switcher(), this); //- DAR ITEM
            //  Bukkit.getPluginManager().registerEvents(new Ninja(), this);//tirar erros
              Bukkit.getPluginManager().registerEvents(new Camel(), this);
              Bukkit.getPluginManager().registerEvents(new Explorer(), this);
              Bukkit.getPluginManager().registerEvents(new Fireman(), this); //- DAR ITEM
              Bukkit.getPluginManager().registerEvents(new Grandpa(), this); //- DAR ITEM E TESTAR
              Bukkit.getPluginManager().registerEvents(new Miner(), this);
              Bukkit.getPluginManager().registerEvents(new Lumberjack(), this);
              Bukkit.getPluginManager().registerEvents(new Poseidon(), this);
//            Bukkit.getPluginManager().registerEvents(new Stomper(), this);
//            Bukkit.getPluginManager().registerEvents(new Fisherman(), this);// - FZR
           //   Bukkit.getPluginManager().registerEvents(new Reaper(), this);//tirar erros
            //  Bukkit.getPluginManager().registerEvents(new Viper(), this);//tirar erros
//            Bukkit.getPluginManager().registerEvents(new Boxer(), this); //- ARRUMAR
              Bukkit.getPluginManager().registerEvents(new Worm(), this);
              Bukkit.getPluginManager().registerEvents(new Thor(), this);
//            Bukkit.getPluginManager().registerEvents(new Digger(), this); //- FZR
              Bukkit.getPluginManager().registerEvents(new Demoman(), this);
              Bukkit.getPluginManager().registerEvents(new Cannibal(), this);
//            Bukkit.getPluginManager().registerEvents(new Cultivator(), this);
           //   Bukkit.getPluginManager().registerEvents(new Snail(), this);//tirar error
              Bukkit.getPluginManager().registerEvents(new IronMan(), this);
              Bukkit.getPluginManager().registerEvents(new Monk(), this);
              Bukkit.getPluginManager().registerEvents(new Titan(), this);
//              Bukkit.getPluginManager().registerEvents(new Turtle(), this);
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


}
