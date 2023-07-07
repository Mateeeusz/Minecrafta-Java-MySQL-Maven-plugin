package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class clearCoammand extends PlayerCommand {
    public clearCoammand() {
        super("clear", "Czysci ekwipunek", "clear nick", "sky.clear", "clear");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length != 1) {
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().setHeldItemSlot(0);
            p.updateInventory();
            return Utill.sendMsg(p, Config.INVENTORY_CLEARED);
        }
        if (!p.hasPermission("sky.clear")) {
            return Utill.sendMsg(p, "&cNie masz uprawnien. &7" +this.getPermission());
        }
        Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return Utill.sendMsg(p, Config.PLAYER_IS_NOT_ONLINE);
        }
        o.getInventory().clear();
        o.getInventory().setHelmet(null);
        o.getInventory().setChestplate(null);
        o.getInventory().setLeggings(null);
        o.getInventory().setBoots(null);
        o.getInventory().setHeldItemSlot(0);
        o.updateInventory();
        Utill.sendMsg(p,Utill.fixColor(Config.PLAYER_INVENTORY_CLEARED).replace("{PLAYER}", o.getDisplayName()));
        return Utill.sendMsg(o, Utill.fixColor(Config.INVENTORY_CLEARED_BY).replace("{PLAYER", p.getDisplayName()));
    }
}
