package me.minihg.feastminifeast;

import me.minihg.Main;
import me.minihg.api.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FeastManager {

    public static Block mainBlock = null;
    private static Integer radius = 20;
    public static Boolean spawned = false;
    private static Chest[] chests = new Chest[12];
    private static ArrayList<Location> fblocks = new ArrayList();
    public static boolean protectFeast;


    public FeastManager() {
    }

    public static void feastAnnouncement(Integer tempo){
        if (mainBlock == null){
            do{
                do{
                    int min = -20;
                    int max = 20;
                    Random r = new Random();
                    int x = r.nextInt(max - min + 1)+ max;
                    int z = r.nextInt(max - min + 1)+ max;
                    Block highBlock = ((World) Bukkit.getServer().getWorlds().get(0)).getHighestBlockAt(x, z);
                    Block loc = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(x, highBlock.getY(), z);
                    mainBlock = loc;
                }while(mainBlock.getType() == Material.LOG);
            }while(mainBlock.getType() == Material.LEAVES);
            mainBlock.setType(Material.GRASS);
            fblocks.add(mainBlock.getLocation());
            removeAbove(mainBlock);
            createFeast(Material.GRASS);
            spawned = true;;
        }
    }

    public static void spawnFeast() {
        if (mainBlock == null) {
            feastAnnouncement(0);
        }


        DecimalFormat df = new DecimalFormat("##.#");
        Bukkit.broadcastMessage("§bO Feast nasceu em §e" + df.format(mainBlock.getLocation().getX()) + "‚" + df.format(mainBlock.getLocation().getY()) + "," + df.format(mainBlock.getLocation().getZ()));
        Bukkit.getWorld("world").setTime(1000L);
        protectFeast = false;
        spawned = false;
        if (Main.feastChests) {
            spawnChests();
        }

        List<?> items = Files.MiniFeast.getStringList("ITEMSF");
        Iterator<?> var3 = items.iterator();

        while(true) {
            Object item;
            String[] oneitem;
            Random r;
            String itemid;
            Integer minamount;
            Integer maxamount;
            Integer amount;
            Boolean spawn;
            Integer id;
            Short durability;
            do {
                if (!var3.hasNext()) {
                    return;
                }

                item = var3.next();
                oneitem = ((String)item).split(",");
                r = new Random();
                itemid = oneitem[0];
                minamount = Integer.parseInt(oneitem[1]);
                maxamount = Integer.parseInt(oneitem[2]);
                amount = 0;
                Boolean force = Boolean.parseBoolean(oneitem[3]);
                spawn = force;
                id = null;
                durability = null;
                if (!force) {
                    spawn = r.nextBoolean();
                }
            } while(!spawn);

            if (((String)item).contains(":")) {
                String[] it = itemid.split(":");
                id = Integer.parseInt(it[0]);
                durability = Short.parseShort(it[1]);
            } else {
                id = Integer.parseInt(itemid);
            }

            ItemStack i = new ItemStack(id, 1);
            if (durability != null) {
                i.setDurability(durability);
            }

            if (oneitem.length == 6) {
                i.addUnsafeEnchantment(Enchantment.getById(Integer.parseInt(oneitem[4])), Integer.parseInt(oneitem[5]));
            }

            Integer ra = radius;
            if (maxamount == minamount) {
                amount = maxamount;
            } else {
                amount = minamount + r.nextInt(maxamount - minamount + 1);
            }

            if (Main.feastChests) {
                while(amount > 0) {
                    Chest chest = chests[r.nextInt(12)];
                    Integer slot = r.nextInt(27);

                    for(byte maxtry = 0; chest.getInventory().getItem(slot) != null && !chest.getInventory().getItem(slot).getType().equals(i.getType()) && maxtry < 1000; slot = r.nextInt(27)) {
                    }

                    if (chest.getInventory().getItem(slot) != null) {
                        i.setAmount(i.getAmount() + 1);
                    }

                    chest.getInventory().setItem(slot, i);
                    chest.update();
                    amount = amount - 1;
                }
            }

            Location c = mainBlock.getLocation();
            c.add((double)(-(ra / 2) + r.nextInt(ra)), 1.0D, (double)(-(ra / 2) + r.nextInt(ra)));

            while(amount > 0) {
                ((World)Bukkit.getServer().getWorlds().get(0)).dropItemNaturally(c, i).setPickupDelay(100);
                int var20 = amount = amount - 1;
            }
        }
    }

    private static void spawnChests() {
        Location loc = mainBlock.getLocation();
        loc.add(-3.0D, 1.0D, -3.0D);
        //integer vv
        int curchest = 0;
        Main.feastProtection = false;
        Integer[] co = new Integer[]{5, 5, 5, 5, 5, 5, 5, -1, 5, 2, 5, 2, 5, 2, 5, -1, 5, 5, 2, 5, 2, 5, 5, -1, 5, 2, 5, 3, 5, 2, 5, -1, 5, 5, 2, 5, 2, 5, 5, -1, 5, 2, 5, 2, 5, 2, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1};
        Integer[] arrayOfInteger1 = co;
        int j = co.length;

        for(int i = 0; i < j; ++i) {
            Integer f = arrayOfInteger1[i];
            Material m = Material.AIR;
            switch(f) {
                case 0:
                    m = Material.AIR;
                    break;
                case 1:
                    m = Material.OBSIDIAN;
                    break;
                case 2:
                    m = Material.CHEST;
                    break;
                case 3:
                    m = Material.ENCHANTMENT_TABLE;
                    break;
                case 4:
                    m = Material.FENCE;
                case 5:
                default:
                    break;
                case 6:
                    m = Material.WOOD;
                    break;
                case 7:
                    m = Material.GLOWSTONE;
            }

            if (f == -1) {
                loc.add(0.0D, 0.0D, 1.0D);
                loc.subtract(7.0D, 0.0D, 0.0D);
            } else if (f == -2) {
                loc.add(0.0D, 1.0D, 0.0D);
                loc.subtract(7.0D, 0.0D, 6.0D);
            } else if (f == 5) {
                loc.add(1.0D, 0.0D, 0.0D);
            } else {
                loc.getBlock().setType(m);
                if (f != 0) {
                    fblocks.add(loc.getBlock().getLocation());
                }

                if (m == Material.CHEST) {
                    chests[curchest] = (Chest)loc.getBlock().getState();
                    if (curchest < 12) {
                        curchest = curchest + 1;
                    }
                }
                loc.add(1.0D, 0.0D, 0.0D);
            }
        }
    }
    public static void removeAbove(Block block) {
        Location loc = block.getLocation();
        loc.setY(loc.getY() + 0.5D);

        for(Block newBlock = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(loc); loc.getY() < (double)((World)Bukkit.getServer().getWorlds().get(0)).getMaxHeight(); newBlock = ((World)Bukkit.getServer().getWorlds().get(0)).getBlockAt(loc)) {
            newBlock.setType(Material.AIR);
            loc.setY(loc.getY() + 0.5D);
        }
    }
    private static void createFeast(Material m) {
        Location loc = mainBlock.getLocation();
        Integer r = radius;

        for(double x = (double)(-r); x <= (double)r; ++x) {
            for(double z = (double)(-r); z <= (double)r; ++z) {
                Location l = new Location((World)Bukkit.getServer().getWorlds().get(0), loc.getX() + x, loc.getY(), loc.getZ() + z);
                if (l.distance(loc) <= (double)r && l.getBlock().getType() != Material.NETHERRACK) {
                    removeAbove(l.getBlock());
                    l.getBlock().setType(m);
                    fblocks.add(l);
                }
            }
        }
    }
}
