package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class tpaCommand extends PlayerCommand {
    public static HashMap<Player, Player> getLastReceiverBySender = new HashMap<>();
    public static HashMap<Player, Player> getLastSenderByReceiver = new HashMap<>();
    public static HashMap<Player, Long> lastSenderRequestTime = new HashMap<>();

    public tpaCommand() {
        super("tpa", "Wysylasz zapytanie o teleportacje do gracza", "/tpa <gracz>", "sky.tpa", "teleporta");
    }

    public static void acceptRequest(Player sender, Player receiver) {
        getLastReceiverBySender.remove(sender);
        getLastSenderByReceiver.remove(receiver);
        lastSenderRequestTime.remove(sender);
    }

    public static void denyRequest(Player sender, Player receiver) {
        getLastReceiverBySender.remove(sender);
        getLastSenderByReceiver.remove(receiver);
        lastSenderRequestTime.remove(sender);
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        Player player = sender;
        if (args.length < 1) {
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        Player other = Bukkit.getPlayer(args[0]);
        if (other == null) {
            return Utill.sendMsg(sender, "&4Blad: &cPodany gracz nie jest online!");
        }
        sentRequest(player, other);
        Utill.sendMsg(other, "&3" + player.getDisplayName() + " &7chce sie przeteleportowac do ciebie.");
        Utill.sendMsg(other, "&7Wpisz &3/tpaccept " + player.getDisplayName() + "&7aby zaakceptowac.");
        Utill.sendMsg(other, "&7Wpisz &3/tpdeny " + player.getDisplayName() + " &7aby odrzucic.");
        return Utill.sendMsg(sender, "&7Wyslano prosbe o teleportacje do &3" + other.getDisplayName());
    }

    public void sentRequest(Player sender, Player receiver) {
        getLastReceiverBySender.put(sender, receiver);
        getLastSenderByReceiver.put(receiver, sender);
        lastSenderRequestTime.put(sender, System.currentTimeMillis());
    }
}
