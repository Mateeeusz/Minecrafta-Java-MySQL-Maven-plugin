package me.mati.skyblock.Commands;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class youtubeCommand extends PlayerCommand {

    public youtubeCommand() {
        super("help", "Pomoc", "/help", "sky.yt", "pomoc", "bukkit:help");
    }

    public boolean onCommand(Player sender, String[] args) {
        for (String yt : Config.COMMAND_YT_MESSAGE) {
            Utill.sendMsg(sender, "&4" + yt);
        }
        return true;
    }
}
