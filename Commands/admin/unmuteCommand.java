package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Managers.MainManagers.MuteManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Mute;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class unmuteCommand extends Command {

    public unmuteCommand() {
        super("unmute", "Odmutowanie graczy", "/unmute <gracz>", "sky.unmute");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        User u = UserManager.getUser(args[0]);
        if (u == null) {
            return Utill.sendMsg(sender, "&4Blad: &cGracz nie istnieje w bazie danych!");
        }
        Mute m = MuteManager.getMute(u);
        if (m == null) {
            return Utill.sendMsg(sender, "&4Blad: &c" + u.getLastName() + " nie ma mute!");
        }
        m.setUnmute(true);
        MuteManager.deleteMute(m);
        return Utill.sendMsg(Bukkit.getOnlinePlayers(),"&3Gracz &7" + u.getLastName() + " &3zostal odciszony przez &7" + sender.getName() + "&7.");
    }
}
