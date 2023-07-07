package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand extends Command {

    public KickCommand() {
        super("kick", "wyrzucanie graczy z serwera", "/kick <gracz> [powod]", "sky.kick");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            return Utill.sendMsg(sender, Config.PLAYER_IS_NOT_ONLINE);
        }
        if (p.hasPermission("sky.kick.bypass")) {
            return Utill.sendMsg(sender, Config.YOU_CAN_NOT_KICK_THIS_PLAYER);
        }
        if (sender.getName().equalsIgnoreCase(p.getName())) {
            return Utill.sendMsg(sender, Config.YOU_CAN_NOT_KICK_YOURSELF);
        }
        String reason = Config.KICK_REASON_IF_NOT_GIVEN;
        if (args.length > 1) {
            reason = StringUtils.join(args, " ", 1, args.length);
        }
        String kickMsg = "&7Zostales wyrzucony z serwera przez &3" + sender.getName() + "&7.\n" + "&6Powod: &3" + reason + "&7!";
        p.kickPlayer(Utill.fixColor(kickMsg));
        return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + p.getName() + " &3zostal wyrzucony przez &7" + sender.getName() + "&3. Powod: &7" + reason + ".");
    }
}

