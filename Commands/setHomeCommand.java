package me.mati.skyblock.Commands;

import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class setHomeCommand extends PlayerCommand {

    public setHomeCommand() {
        super("sethome", "Ustawia home", "/sethome", "sky.sethome");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        User u = UserManager.getUser(sender);
        if (u == null) return Utill.sendMsg(sender, "&cNie istniejesz w bazie danych");
        u.setHome(sender.getLocation());
        return Utill.sendMsg(sender, "&aUstawiono home!");
    }
}
