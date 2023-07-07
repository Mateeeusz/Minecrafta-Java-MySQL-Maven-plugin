package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class enderCommand extends PlayerCommand {
    public enderCommand() {
        super("ender", "otwieranie enderchest gracza", "/ender <nick>", "sky.enser", "");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
            if(args.length >= 2 || args.length <= 0){
                Utill.sendMsg(p, "&4Blad: &cPoprawne uzycie to: " + this.getUsage());
            } else {
                Player other = Bukkit.getPlayer(args[0]);
                p.openInventory(other.getEnderChest());
        }
        return false;
    }
}
