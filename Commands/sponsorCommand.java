package me.mati.skyblock.Commands;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class sponsorCommand extends PlayerCommand {

    public sponsorCommand() {
        super("sponsor", "informacje o sponsorze", "/sponsor", "sky.sponsor", "sponsor", "inforsponsor");
    }

    public boolean onCommand(Player sender, String[] args) {
        for (String svip : Config.COMMAND_SPONSOR_MESSAGE) {
            Utill.sendMsg(sender, svip);
        }
        return true;
    }
}
