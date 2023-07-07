package me.mati.skyblock.Managers;

import me.mati.skyblock.Main;
import me.mati.skyblock.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PremiumCaseDropFile {
    private FileConfiguration cfg;
    private List<ItemStack> items;
    private String nameInGui;
    private int slot;
    private Material drop;
    private List<String> loreInGui;
    private double chancegui;
    private int chance;
    private String msg;

    public PremiumCaseDropFile(final String name) {
        this.cfg = ConfigManager.getCfg();
        this.items = new ArrayList<ItemStack>();
        final FileConfiguration cf = Main.getPlugin(Main.class).getConfig();
        final String path = "drops." + name + ".";
        this.chance = cf.getInt(String.valueOf(String.valueOf(path)) + "chance");
        this.nameInGui = this.cfg.getString("drops." + name + ".nameInGui");
        this.drop = Material.matchMaterial(this.cfg.getString("drops." + name + ".drop"));
        this.loreInGui = (List<String>)this.cfg.getStringList("drops." + name + ".loreInGui");
        this.slot = this.cfg.getInt("drops." + name + ".slot");
        this.chancegui = this.cfg.getDouble("drops." + name + ".chance");
        this.msg = cf.getString(String.valueOf(String.valueOf(path)) + "message");
        for (final String is : cf.getStringList(String.valueOf(String.valueOf(path)) + "drops")) {
            this.items.add(ItemUtil.parseItemStack(is));
        }
    }

    public int getChance() {
        return this.chance;
    }

    public List<ItemStack> getItems() {
        return this.items;
    }

    public String getMsg() {
        return this.msg;
    }

    public double getChance1() {
        return this.chancegui;
    }

    public Material getDrop() {
        return this.drop;
    }

    public String getNameInGui() {
        return this.nameInGui;
    }

    public List<String> getLoreInGui() {
        return this.loreInGui;
    }

    public int getSlot() {
        return this.slot;
    }
}
