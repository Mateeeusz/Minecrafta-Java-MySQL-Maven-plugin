package me.mati.skyblock.Managers;

import me.mati.skyblock.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager
{
    public static FileConfiguration cfg;
    public static String inventory_name;
    public static String onDrops_item_name;
    public static List<String> onDrops_item_lore;

    static {
        cfg = Main.getPlugin(Main.class).getConfig();
    }

    public static FileConfiguration getCfg() {
        return ConfigManager.cfg;
    }

    public static void load() {
        ConfigManager.inventory_name = ConfigManager.cfg.getString("inventory-name");
        ConfigManager.onDrops_item_name = ConfigManager.cfg.getString("item-onDrops.name");
        ConfigManager.onDrops_item_lore = (List<String>)ConfigManager.cfg.getStringList("item-onDrops.lore");
    }
}
