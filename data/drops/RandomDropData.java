package me.mati.skyblock.data.drops;
import me.mati.skyblock.Commands.admin.turboDropCommand;
import me.mati.skyblock.Managers.DropFile;
import me.mati.skyblock.Managers.MainManagers.TurboDropManager;
import me.mati.skyblock.base.Turbo;
import me.mati.skyblock.data.Drop;
import me.mati.skyblock.data.DropData;
import me.mati.skyblock.data.DropType;
import me.mati.skyblock.utils.DropUtil;
import me.mati.skyblock.utils.RandomUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RandomDropData implements DropData {
    private static List<Drop> drops = new ArrayList<>();
    private static Set<UUID> noCobble = new HashSet<>();

    public RandomDropData() {
        super();
        RandomDropData.drops.clear();
        for (String s : DropFile.getConfig().getConfigurationSection("random-drops").getKeys(false)) {
            Drop d = new Drop(s);
            RandomDropData.drops.add(d);
        }
    }

    public static void changeNoCobble(UUID uuid) {
        if (RandomDropData.noCobble.contains(uuid)) {
            RandomDropData.noCobble.remove(uuid);
        } else {
            RandomDropData.noCobble.add(uuid);
        }
    }

    public static boolean isNoCobble(UUID uuid) {
        return RandomDropData.noCobble.contains(uuid);
    }

    public static Drop getDropByName(String name) {
        for (Drop d : RandomDropData.drops) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    public static List<Drop> getDrops() {
        return RandomDropData.drops;
    }

    @Override
    public void breakBlock(Block block, Player player, ItemStack item) {
        List<ItemStack> drop = new ArrayList<>();
        Turbo turbodrop = TurboDropManager.getByPlayer(player);
        for (Drop d : RandomDropData.drops) {
            if (d.isDisabled(player.getUniqueId())) {
                continue;
            }
            ItemStack itemDrop = d.getWhat().clone();
            int expDrop = d.getExp();
            if (!d.getFrom().equals(block.getType())) {
                continue;
            }
            if (!d.getTools().contains(item.getType())) {
                continue;
            }
            if (!d.getBiomes().contains(block.getBiome())) {
                continue;
            }
            int y = block.getLocation().getBlockY();
            if (y < d.getMinHeight()) {
                continue;
            }
            if (y > d.getMaxHeight()) {
                continue;
            }
            double cdrop;
            double chance = cdrop = d.getChance();
            if (player.hasPermission("skydrop.vip") && turbodrop != null || player.hasPermission("skydrop.vip") && turboDropCommand.TurboDrop) {
                cdrop = chance * 4.00;
            } else if (player.hasPermission("skydrop.vip") || !player.hasPermission("skydrop.vip") && turbodrop != null || !player.hasPermission("skydrop.vip") && turboDropCommand.TurboDrop) {
                cdrop = chance * 2.00;
            }
            if (!RandomUtil.getChance(cdrop)) {
                continue;
            }
            if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && d.isFortune()) {
                int a = DropUtil.addFortuneEnchant((d.getMinAmount() == d.getMaxAmount()) ? d.getMinAmount() : RandomUtil.getRandInt(d.getMinAmount(), d.getMaxAmount()), item);
                itemDrop.setAmount(a);
                expDrop *= a;
            }
            drop.add(itemDrop);
            player.giveExp(expDrop);
            Utill.sendMsg((CommandSender)player, d.getMessage().replace("{AMOUNT}", Integer.toString(itemDrop.getAmount())));
        }
        if (!RandomDropData.noCobble.contains(player.getUniqueId())) {
            drop.add(new ItemStack(item.containsEnchantment(Enchantment.SILK_TOUCH) ? Material.STONE : Material.COBBLESTONE, 1));
        }
        DropUtil.addItemsToPlayer(player, drop, block);
        DropUtil.recalculateDurability(player, item);
        block.setType(Material.AIR);
    }


    @Override
    public DropType getDropType() {
        return DropType.RANDOM_DROP;
    }
}
