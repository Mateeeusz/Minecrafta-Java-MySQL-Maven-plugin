package me.mati.skyblock.Commands;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;



public class svipCommand extends PlayerCommand {

    public svipCommand() {
        super("svip", "informacje o svipie", "/svip", "sky.svip", "svip", "infosvip");
    }

    public boolean onCommand(Player sender, String[] args) {
        for (String svip : Config.COMMAND_SVIP_MESSAGE) {
            Utill.sendMsg(sender, svip);
        }
        return true;
    }
}

