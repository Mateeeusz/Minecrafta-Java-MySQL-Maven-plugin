package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class KickAllCommand extends Command {

    public KickAllCommand() {
        super("kickall", "Wyrzucanie wszystkich graczy z serwera", "/kickall", "sky.kickall");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        String reason = "&3KickAll\n\n" + StringUtils.join(args, " ");
        Bukkit.getOnlinePlayers().stream().filter(p -> !p.hasPermission("sky.kickall.bypass")).forEach(p -> p.kickPlayer(Utill.fixColor(reason)));
        return Utill.sendMsg(sender, Config.KICK_ALL_MESSAGE);
    }
}

