package me.minihg.api;

import me.minihg.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Files {

    public static FileConfiguration MiniFeast;

    public Files() {
        try {
            this.loadFiles();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[32];

            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.close();
            in.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    private void loadFiles() throws Exception {
        File miniFile = new File(Main.getInstance().getDataFolder(), "config.yml");
        Integer creation = 0;
        if (!miniFile.exists()) {
            miniFile.getParentFile().mkdirs();
            copy(Main.getInstance().getResource("config.yml"), miniFile);
            creation = creation + 1;
        }

        if (creation > 0) {
            Main.getInstance().getLogger().info("Arquivo " + creation + " files.");
        }

        MiniFeast = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(), "config.yml"));
    }
}
