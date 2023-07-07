package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.EnchantManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class enchantCommand extends PlayerCommand {

    public enchantCommand() {
        super("enchant", "nadawanie zaklec przedmiotom", "/enchant <zaklecie> [poziom]", "sky.enchant");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length < 1 || args.length > 2) {
            return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        ItemStack item = p.getItemInHand();
        String enchantmentName = args[0];
        Enchantment enchant = EnchantManager.get(enchantmentName);
        if (enchant == null) {
            return Utill.sendMsg(p, Config.ENCHANT_NOT_FOUND);
        }
        int level = enchant.getMaxLevel();
        if (args.length == 2) {
            level = Integer.parseInt(args[1]);
        }
        item.addUnsafeEnchantment(enchant, level);
        return Utill.sendMsg(p, "&7Zaklecie &3" + enchant.getName().toLowerCase().replace("_", " ") + " &7zostalo dodane do przedmiotu w Twojej rece!");
    }
}

