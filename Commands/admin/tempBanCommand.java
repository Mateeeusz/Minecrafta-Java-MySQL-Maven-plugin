package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Ban;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class tempBanCommand extends Command {

    public tempBanCommand() {
        super("tempban", "tymczasowe banowanie graczy", "/tempban <gracz> <czas> [powod]", "sky.tempban");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        User u = UserManager.getUser(args[0]);
        if (u == null) {
            return Utill.sendMsg(sender, "&4Blad: &cGracz nie istnieje w bazie danych!");
        }
        if (sender.getName().equalsIgnoreCase(u.getLastName())) {
            return Utill.sendMsg(sender, "&4Blad: &cNie mozesz zbanowac sam siebie!");
        }
        if (u.getPlayer() != null && u.getPlayer().hasPermission("sky.tempban.bypass")) {
            return Utill.sendMsg(sender, "&4Blad: &cNie mozesz zbanowac tego gracza!");
        }
        long time = Utill.parseDateDiff(args[1], true);
        Ban b = BanManager.getBan(u.getUuid());
        if (b != null && !b.isAlive()) {
            return Utill.sendMsg(sender,"&4Blad: &cTen gracz ma juz bana!");
        }
        String reason = "Administrator ma zawsze racje!";
        if (args.length > 2) {
            reason = StringUtils.join(args, " ", 2, args.length);
        }
        BanManager.createBan(u.getUuid(), reason, sender.getName(), u.getLastName(), time);
        return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + u.getLastName() + " &3zostal zbanowany przez &7" + sender.getName() + " &3do &7" + Utill.getDate(time) + "&3. Powod: &7" + reason + "&3.");
    }
}
