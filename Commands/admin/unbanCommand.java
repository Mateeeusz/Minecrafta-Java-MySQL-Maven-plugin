package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Ban;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class unbanCommand extends Command {

    public unbanCommand() {
        super("unban", "Odbanowywanie graczy", "/unban <gracz>", "sky.unban");
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
        Ban b = BanManager.getBan(u);
        if (b == null) {
            return Utill.sendMsg(sender, "&4Blad: &c" + u.getLastName() + " nie ma bana!");
        }
        b.setUnban(true);
        BanManager.deleteBan(b);
        return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + u.getLastName() + " &3zostal odbanowany przez &7" + sender.getName() + "&3.");
    }
}