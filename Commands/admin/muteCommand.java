package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Managers.MainManagers.MuteManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Mute;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class muteCommand extends Command {

    public muteCommand() {
        super("mute", "permamentne mutowanie uzytkownikow", "/mute <gracz> [powod]", "sky.mute");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        User user = UserManager.getUser(args[0]);
        if (user == null) {
            user = new User(args[0]);
        }
        if (user.getLastName().equalsIgnoreCase(sender.getName())) {
            return Utill.sendMsg(sender, "&4Blad: &cNie mozesz myciszyc samego siebie!");
        }
        if (user.getPlayer() != null && user.getPlayer().hasPermission("sky.mute.bypass")) {
            return Utill.sendMsg(sender, "&4Blad: &cNie mozesz wyciszyc tego uzytkownika!");
        }
        String admin = sender.getName().equals("CONSOLE") ? "konsola" : sender.getName();
        String reason = "Administrator ma zawsze racje!";
        if (args.length > 1) {
            reason = StringUtils.join(args, " ", 1, args.length);
        }
        Mute mute = MuteManager.getMute(user);
        if (mute != null && !mute.isAlive()) {
            return Utill.sendMsg(sender, "&4Blad: &cTen gracz jest juz wyciszony!");
        }
        mute = MuteManager.createMute(user.getUuid(), user.getLastName(), reason, admin, 0L);
        return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + user.getLastName() + " &3zostal wyciszony przez &7" + mute.getAdmin() + "&3. Powod: &7" + reason + "&3!");
    }
}
