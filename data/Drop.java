package me.mati.skyblock.data;

import me.mati.skyblock.Managers.DropFile;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class Drop {
    private final String name;
    private final double chance;
    private final int exp;
    private final String message;
    private final boolean fortune;
    private final List<Biome> biomes;
    private final List<Material> tools;
    private final int minHeight;
    private final int maxHeight;
    private final int minAmount;
    private final int maxAmount;
    private final ItemStack what;
    private final Material from;
    private final Set<UUID> disabled;

    public Drop(final String name) {
        super();
        this.biomes = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.disabled = new HashSet<>();
        this.name = name;
        this.chance = DropFile.getConfig().getDouble("random-drops." + name + ".chance");
        this.exp = DropFile.getConfig().getInt("random-drops." + name + ".exp");
        this.message = DropFile.getConfig().getString("random-drops." + name + ".message");
        this.fortune = DropFile.getConfig().getBoolean("random-drops." + name + ".fortune");
        this.minHeight = DropFile.getConfig().getInt("random-drops." + name + ".height.min");
        this.maxHeight = DropFile.getConfig().getInt("random-drops." + name + ".height.max");
        this.minAmount = DropFile.getConfig().getInt("random-drops." + name + ".amount.min");
        this.maxAmount = DropFile.getConfig().getInt("random-drops." + name + ".amount.max");
        this.what = Utill.getItemStackFromString(DropFile.getConfig().getString("random-drops." + name + ".drop.what"));
        this.from = Material.getMaterial(DropFile.getConfig().getString("random-drops." + name + ".drop.from"));
        this.biomes.addAll(DropFile.getConfig().getStringList("random-drops." + name + ".biome").stream().map(Biome::valueOf).collect(Collectors.toList()));
        this.tools.addAll(DropFile.getConfig().getStringList("random-drops." + name + ".tool").stream().map(Material::getMaterial).collect(Collectors.toList()));
        if (this.biomes.size() == 0) {
            this.biomes.addAll(Arrays.asList(Biome.values()));
        }
        if (this.tools.size() == 0) {
            this.tools.addAll(Arrays.asList(Material.values()));
        }
    }

    public void changeStatus( UUID uuid) {
        if (this.disabled.contains(uuid)) {
            this.disabled.remove(uuid);
        } else {
            this.disabled.add(uuid);
        }
    }

    public boolean isDisabled( UUID uuid) {
        return this.disabled.contains(uuid);
    }

    public String getName() {
        return this.name;
    }

    public double getChance() {
        return this.chance;
    }

    public int getExp() {
        return this.exp;
    }

    public boolean isFortune() {
        return this.fortune;
    }

    public List<Biome> getBiomes() {
        return this.biomes;
    }

    public String getMessage() {
        return this.message;
    }

    public List<Material> getTools() {
        return this.tools;
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMinAmount() {
        return this.minAmount;
    }

    public int getMaxAmount() {
        return this.maxAmount;
    }

    public ItemStack getWhat() {
        return this.what;
    }

    public Material getFrom() {
        return this.from;
    }
}
