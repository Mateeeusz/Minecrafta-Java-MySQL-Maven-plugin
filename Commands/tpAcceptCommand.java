package me.mati.skyblock.Commands;

import me.mati.skyblock.Managers.TimerManagers.TimerUtil;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class tpAcceptCommand extends PlayerCommand {

    public tpAcceptCommand() {
        super("tpaccept", "Akceptujesz teleport", "/tpaccept <gracz>", "sky.tpaccept", "tpaaccept");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(player,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (tpaCommand.getLastSenderByReceiver.get(player) == null) {
            return Utill.sendMsg(player,"&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        Player other = tpaCommand.getLastSenderByReceiver.get(player);
        if (other == null) {
            return Utill.sendMsg(player, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        if ((System.currentTimeMillis() - tpaCommand.lastSenderRequestTime.get(other)) / 1000L >= 60L) {
            tpaCommand.denyRequest(other, player);
            return Utill.sendMsg(player, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        }
        tpaCommand.acceptRequest(other, player);
        Utill.sendMsg(other, "&3" + player.getName() + "&7 zaakceptowal twoja prosbe o teleportacje.");
        Utill.sendMsg(player, "&7Zaakceptowales prosbe o teleportacje gracza &3" + other.getDisplayName());
        Utill.sendMsg(other, "&aZa &310 sekund &azostanie on przeteleportowany...");
        TimerUtil.teleport(other, player.getLocation(), 10);
        return true;

    }
}
