package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class tpDenyCommand extends PlayerCommand {

    public tpDenyCommand() {
        super("tpdeny", "Anulujesz teleport", "/tpdeny <gracz>", "sky.tpdeny", "teleportdeny");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (tpaCommand.getLastSenderByReceiver.get(sender) == null) {
            return Utill.sendMsg(sender, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        Player other = tpaCommand.getLastSenderByReceiver.get(sender);
        if (other == null) {
            return Utill.sendMsg(sender,"&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        if ((System.currentTimeMillis() - tpaCommand.lastSenderRequestTime.get(other)) / 1000L >= 60L) {
            tpaCommand.denyRequest(other, sender);
            return Utill.sendMsg(sender, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        tpaCommand.denyRequest(other, sender);
        Utill.sendMsg(other,"&3" + sender.getName() + " &codrzucil twoja prosbe o teleportacje.");
        Utill.sendMsg(sender, "&cOdrzuciles prosbe o teleportacje gracza &3" + other.getDisplayName());
        return true;
    }
}
