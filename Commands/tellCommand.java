package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class tellCommand extends PlayerCommand {
    private static HashMap<UUID, UUID> lastMsg = new HashMap<>();

    public tellCommand() {
        super("tell", "prywatne wiadomosci do graczy", "/tell <gracz> <wiadomosc>", "sky.tell", "whisper", "t", "m", "msg");
    }

    public static HashMap<UUID, UUID> getLastMsg() {
        return tellCommand.lastMsg;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return Utill.sendMsg(player, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return Utill.sendMsg(player,"&4Blad: &cNie znaleziono gracza: &7" + args[0] + "&c!");
        }
        if (o.hasPermission("sky.tell.nomsg") && !player.hasPermission("sky.tell.nomsg")) {
            return Utill.sendMsg(player,"&4Blad: &cNie znaleziono gracza: &7" + args[0] + "&c!");
        }
        String message = ChatColor.stripColor(Utill.fixColor(StringUtils.join(args, " ", 1, args.length)));
        tellCommand.lastMsg.put(player.getUniqueId(), o.getUniqueId());
        tellCommand.lastMsg.put(o.getUniqueId(), player.getUniqueId());
        Utill.sendMsg(player, "&8[&3Ja &8-> &e" + o.getName() + "&8] &7" + message);
        return Utill.sendMsg(o, "&8[&e" + player.getName() + " &8-> &3Ja&8] &7" + message);
    }
}
