package me.mati.skyblock.Commands.admin;


import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Ban;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class banCommand extends Command {

    public banCommand() {
        super("ban", "permamentne banowanie uzytkownikow", "/ban <gracz> [powod]", "sky.ban");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        User user = UserManager.getUser(args[0]);
        if (user == null) {
            user = new User(args[0]);
        }
        if (user.getLastName().equalsIgnoreCase(sender.getName())) {
            return Utill.sendMsg(sender, Utill.fixColor(Config.YOU_CAN_NOT_BAN_YOURSELF));
        }
        if (user.getPlayer() != null && user.getPlayer().hasPermission("sky.ban.bypass")) {
            return Utill.sendMsg(sender, Utill.fixColor(Config.YOU_CAN_NOT_BAN_THIS_PLAYER));
        }
        String admin = sender.getName().equals("CONSOLE") ? "konsola" : sender.getName();
        String reason = Config.REASON_IF_NOT_GIVEN;
        if (args.length > 1) {
            reason = StringUtils.join(args, " ", 1, args.length);
        }
        Ban ban = BanManager.getBan(user);
        if (ban != null && !ban.isAlive()) {
            return Utill.sendMsg(sender, Utill.fixColor(Config.THIS_PLAYER_IS_ALREADY_BANED));
        }
        ban = BanManager.createBan(user.getUuid(), reason, admin, user.getLastName(), 0L);
        return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + user.getLastName() + " &3zostal zbanowany przez &7" + ban.getAdmin() + "&3. Powod: &7" + reason + "&3!");
    }
}
