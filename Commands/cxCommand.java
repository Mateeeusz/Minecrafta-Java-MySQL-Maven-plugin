package me.mati.skyblock.Commands;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.MainCraftingsProvider;
import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class cxCommand extends PlayerCommand {
    public cxCommand() {
        super("cx", "Robi cx", "/cx", "sky.cx", "cobblex");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        String it = Config.COST_CX;
        List<ItemStack> items = ItemUtil.getItems(it, 1);
        if (!ItemUtil.checkAndRemove(items, p)) {
            Utill.sendMsg(p, "&4Blad: &cPotrzebujesz&8: &3Cobblestone&8(&e576&8)&7.");
            return false;
        }
        ItemStack ender = MainCraftingsProvider.cx;
        p.getInventory().addItem(ender);
        Utill.sendMsg(p, "&2Udalo ci sie stworzyc cobblex!");




            return false;
    }
}
