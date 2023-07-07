package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class tphereCommand extends PlayerCommand {

    public tphereCommand() {
        super("tphere", "Teleportacja gracza do Ciebie", "/tphere <gracz>", "sky.tphere", "s");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        Player other = Bukkit.getPlayer(args[0]);
        if (other == null) {
            return Utill.sendMsg(sender,"&4Blad: &cGracz o nicku: &3" + args[0] + " &cjest offline.");
        }
        other.teleport(sender.getLocation());
        return Utill.sendMsg(sender,"&7Przeteleportowales do siebie gracza: &3" + other.getName() + "&7!");
    }
}
