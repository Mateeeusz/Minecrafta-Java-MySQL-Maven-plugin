package me.mati.skyblock.Commands.premiumRanks;

import me.mati.skyblock.Managers.TimerManagers.TimerUtil;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class homeCommand extends PlayerCommand {

    public homeCommand() {
        super("home", "Teleport na home", "/home", "sky.home");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        User u = UserManager.getUser(sender);
        if (u == null) return Utill.sendMsg(sender, "&4Blad: &cNie istniejesz w bazie danych");
        TimerUtil.teleport(sender, u.getHome(), 10);
        return true;
    }
}
