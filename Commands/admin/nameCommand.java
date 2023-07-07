package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class nameCommand extends PlayerCommand {
    public nameCommand() {
        super("name", "Zmiana nazwy przedmiotu", "/name <nazwa>", "sky.name", "changename", "chname", "cname");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length == 0) {
            return Utill.sendMsg(p,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (p.getInventory().getItemInHand().getType() == Material.AIR) {
            return Utill.sendMsg(p,"&4Blad: &cMusisz trzymac przedmiot aby zmienic mu nazwe!");
        }
        StringBuilder mb = new StringBuilder();
        for (String a : args) {
            if (mb.length() > 0) {
                mb.append(" ");
            }
            mb.append(a);
        }
        ItemStack item = p.getInventory().getItemInHand();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utill.fixColor(mb.toString()));
        item.setItemMeta(meta);
        return Utill.sendMsg(p, "&7Poprawnie zmieniono nazwe na: &3" + Utill.fixColor(mb.toString()));
    }
}
