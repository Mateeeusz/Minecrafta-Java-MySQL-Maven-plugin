package me.mati.skyblock.utils;


import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropUtil {

    public static void recalculateDurability(final Player player, final ItemStack item) {
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        final int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        final short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > RandomUtil.getRandInt(0, 100)) {
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                } else {
                    item.setDurability((short) (d + 1));
                }
            }
        } else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
        } else {
            item.setDurability((short) (d + 1));
        }
    }

    public static int addFortuneEnchant(final int amount, final ItemStack tool) {
        int a = amount;
        if (RandomUtil.getChance(30.0) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 1) {
            ++a;
        } else if (RandomUtil.getChance(20.0) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 2) {
            a += 2;
        } else if (RandomUtil.getChance(10.0) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 3) {
            a += 3;
        }
        return a;
    }

    public static void addItemsToPlayer(final Player player, final List<ItemStack> items, final Block b) {
        final PlayerInventory inv = player.getInventory();
        final HashMap<Integer, ItemStack> notStored = inv.addItem((ItemStack[]) items.toArray(new ItemStack[items.size()]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            b.getWorld().dropItemNaturally(b.getLocation(), en.getValue());
        }
    }
}
