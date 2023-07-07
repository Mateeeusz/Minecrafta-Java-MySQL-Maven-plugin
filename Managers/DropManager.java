package me.mati.skyblock.Managers;

import me.mati.skyblock.Main;
import me.mati.skyblock.data.Drop;
import me.mati.skyblock.data.DropData;
import me.mati.skyblock.data.drops.CancelDropData;
import me.mati.skyblock.data.drops.NormalDropData;
import me.mati.skyblock.data.drops.RandomDropData;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DropManager {
    private static HashMap<Material, DropData> drops = new HashMap<>();
    private static HashMap<Material, Integer> exps = new HashMap<>();

    public static List<PremiumCaseDropFile> drops2;

    static {
        drops2 = new ArrayList<>();
    }

    public static void load() {
        for ( String sectionName : Main.getPlugin(Main.class).getConfig().getConfigurationSection("drops").getKeys(false)) {
            PremiumCaseDropFile newPremiumCaseDrop = new PremiumCaseDropFile(sectionName);
            DropManager.drops2.add(newPremiumCaseDrop);
        }
        Main.getPlugin(Main.class).getLogger().info("Loaded " + DropManager.drops2.size() + " drops to case.");
    }

    public static List<PremiumCaseDropFile> getPremiumCaseDrops() {
        return DropManager.drops2;
    }

    public static void setup() {
        DropManager.drops.clear();
        DropManager.exps.clear();
        for ( String s : DropFile.getConfig().getStringList("cancel-drops")) {
            DropManager.drops.put(Material.getMaterial(s), new CancelDropData());
        }
        RandomDropData data = new RandomDropData();
        for ( Drop d : RandomDropData.getDrops()) {
            DropManager.drops.put(d.getFrom(), data);
        }
        for ( String s2 : DropFile.getConfig().getConfigurationSection("exp-drops").getKeys(false)) {
            DropManager.exps.put(Material.getMaterial(s2), DropFile.getConfig().getInt("exp-drops." + s2, 1));
        }
    }

    public static DropData getDropData( Material mat) {
        DropData drop = new NormalDropData();
        if (DropManager.drops.containsKey(mat)) {
            drop = DropManager.drops.get(mat);
        }
        return drop;
    }

    public static int getExp( Material mat) {
        int exp = 0;
        if (DropManager.exps.containsKey(mat)) {
            exp = DropManager.exps.get(mat);
        }
        return exp;
    }

    public static HashMap<Material, DropData> getDrops() {
        return DropManager.drops;
    }

    public static HashMap<Material, Integer> getExps() {
        return DropManager.exps;
    }
}
