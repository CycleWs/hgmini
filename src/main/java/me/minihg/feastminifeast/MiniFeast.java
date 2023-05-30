//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.minihg.feastminifeast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import me.minihg.api.Files;
import me.minihg.kits.Explorer;
import me.minihg.kits.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MiniFeast {
    public static Block mainBlock = null;
    private static Integer radius = 2;
    private static Logger log = Bukkit.getLogger();
    public static Boolean spawned = false;
    private static Chest[] chests = new Chest[4];
    private static ArrayList<Location> fblocks = new ArrayList();
    public static int presentEndingDataValue = 15;
    public static int presentStartingDataValue = 0;
    public static String presentName;
    public static Player[] arrayOfPlayer;
    public static Location locMF;

    public MiniFeast() {
    }

    public static Location getRespawn() {
        double borderSize = 150.0;
        Location spawn = ((World)Bukkit.getWorlds().get(0)).getSpawnLocation();
        double addX = (double)(new Random()).nextInt((int)(borderSize / 2.0)) + borderSize / 2.0;
        double addZ = (double)(new Random()).nextInt((int)(borderSize / 2.0)) + borderSize / 2.0;
        if ((new Random()).nextBoolean()) {
            addX = -addX;
        }

        if ((new Random()).nextBoolean()) {
            addZ = -addZ;
        }

        Block block = spawn.getWorld().getHighestBlockAt((int)(spawn.getX() + addX), (int)(spawn.getZ() + addZ));
        if (!block.getChunk().isLoaded()) {
            block.getChunk().load();
        }

        while(block.getRelative(BlockFace.UP).getType() != Material.AIR && !block.isLiquid()) {
            block.getRelative(BlockFace.UP);
        }

        block.getLocation().add(0.0, 2.0, 0.0);
        return block.getLocation();
    }

    public static void miniFeastAnnouncement() {
        if (mainBlock == null) {
            mainBlock = getRespawn().add(0.0, 2.0, 0.0).getBlock();
            mainBlock.setType(Material.GLASS);
            fblocks.add(mainBlock.getLocation());
            createFeast(Material.GLASS);
            spawned = true;
            locMF = mainBlock.getLocation();
            miniFeastSpawn();
        }

    }

    public static void miniFeastSpawn() {
        DecimalFormat df = new DecimalFormat("##");
        int g = (arrayOfPlayer = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;
        for(int i = 0; i < g; ++i) {
            Player pe = arrayOfPlayer[i];
            if (KitSelector.kitMap.containsKey(pe) && (Integer)KitSelector.kitMap.get(pe) == Explorer.explorerValue) {
                pe.sendMessage("§cO Mini Feast spawnou exatamente em: §fX: (" + mainBlock.getLocation().getX() + " ‚§7e ‚§f" + df.format(mainBlock.getLocation().getZ()) + ")");
            } else {
                pe.sendMessage("§cUm mini feast nasceu entre: §fX: (" + df.format(mainBlock.getLocation().getX()) + " ‚§7e ‚§f" + df.format(mainBlock.getLocation().getX()) + ") ‚§7e ‚§fZ: (" + df.format(mainBlock.getLocation().getZ()) + " ‚§7e‚§f " + df.format(mainBlock.getLocation().getZ()) + ")");
            }
        }

        spawnChests();
        List<String> items = Files.MiniFeast.getStringList("ITEMSMF");
        Iterator var3 = items.iterator();

        while(true) {
            String item;
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

                item = (String)var3.next();
                oneitem = item.split(",");
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

            if (item.contains(":")) {
                String[] it = itemid.split(":");
                id = Integer.parseInt(it[0]);
                durability = Short.parseShort(it[1]);
            } else {
                id = Integer.parseInt(itemid);
            }

            if (maxamount == minamount) {
                amount = maxamount;
            } else {
                amount = minamount + r.nextInt(maxamount - minamount + 1);
            }

            ItemStack i = new ItemStack(id, r.nextInt(amount));
            if (i.getMaxStackSize() == 1) {
                i = new ItemStack(id, 1);
            }

            if (durability != null) {
                i.setDurability(durability);
            }

            if (oneitem.length == 6) {
                i.addUnsafeEnchantment(Enchantment.getById(Integer.parseInt(oneitem[4])), Integer.parseInt(oneitem[5]));
            }

            Chest chest;
            for(; amount > 0; chest.update()) {
                chest = chests[r.nextInt(4)];
                Integer slot = r.nextInt(27);
                if (chest.getInventory().getItem(slot) != null) {
                    i.setAmount(i.getAmount() + 1);
                    chest.update();
                }

                chest.getInventory().addItem(new ItemStack[]{i});
                chest.update();
                if (i.getAmount() > 1) {
                    amount = amount - i.getAmount();
                    chest.update();
                } else {
                    amount = amount - 1;
                    chest.update();
                }
            }
        }
    }

    private static void createFeast(Material m) {
        Integer r = radius;
        log.info("Generating feast.");

        for(double x = (double)(-r); x <= (double)r; ++x) {
            for(double z = (double)(-r); z <= (double)r; ++z) {
                Location loc = mainBlock.getLocation();
                Location l = new Location((World)Bukkit.getServer().getWorlds().get(0), loc.getX() + x, loc.getY(), loc.getZ() + z);
                if (l.distance(loc) <= (double)r) {
                    l.getBlock().setType(m);
                    fblocks.add(l);
                }
            }
        }

    }

    private static void spawnChests() {
        Location loc = mainBlock.getLocation();
        loc.add(-3.0, 1.0, -3.0);
        Integer curchest = 0;
        Integer[] co = new Integer[]{5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 2, 5, 2, 5, 5, -1, 5, 5, 5, 3, 5, 5, 5, -1, 5, 5, 2, 5, 2, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -1, 5, 5, 5, 5, 5, 5, 5, -2};
        Integer[] arrayOfInteger1 = co;
        int j = co.length;

        for(int i = 0; i < j; ++i) {
            Integer f = arrayOfInteger1[i];
            Material m = Material.AIR;
            switch (f) {
                case -2:
                case -1:
                case 5:
                    break;
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
                    break;
                case 6:
                    m = Material.DIAMOND_BLOCK;
                    break;
                case 7:
                    m = Material.BEACON;
                    break;
                default:
                    log.warning("Algo ocorreu enquanto spawnava os baus: " + f.toString());
            }

            if (f == -1) {
                loc.add(0.0, 0.0, 1.0);
                loc.subtract(7.0, 0.0, 0.0);
            } else if (f == -2) {
                loc.add(0.0, 1.0, 0.0);
                loc.subtract(7.0, 0.0, 6.0);
            } else if (f == 5) {
                loc.add(1.0, 0.0, 0.0);
            } else {
                loc.getBlock().setType(m);
                if (f != 0) {
                    fblocks.add(loc.getBlock().getLocation());
                }

                if (m == Material.CHEST) {
                    chests[curchest] = (Chest)loc.getBlock().getState();
                    if (curchest < 4) {
                        curchest = curchest + 1;
                    }
                }

                loc.add(1.0, 0.0, 0.0);
            }
        }

    }

    public static Block getMainBlock() {
        return mainBlock;
    }

    static {
        presentName = ChatColor.WHITE + "Presente para kit - %s";
    }
}
