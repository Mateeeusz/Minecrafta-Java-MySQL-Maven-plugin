package me.mati.skyblock.Commands;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;


public class vipCommand extends PlayerCommand {

    public vipCommand() {
        super("vip", "informacje o vipie", "/vip", "sky.vip", "vip", "infovip");
    }

    public boolean onCommand(Player sender, String[] args) {
        for (String vip : Config.COMMAND_VIP_MESSAGE) {
            Utill.sendMsg(sender, vip);
        }
        return true;
    }
}

