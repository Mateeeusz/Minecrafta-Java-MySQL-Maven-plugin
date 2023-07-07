package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class replyCommand extends PlayerCommand {

    public replyCommand() {
        super("reply", "odpowiedz na prywatna wiadomosc", "/reply <wiadomosc>", "easyagecore.reply", "r");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 1) {
            return Utill.sendMsg(player,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        UUID last = tellCommand.getLastMsg().get(player.getUniqueId());
        if (last == null) {
            return Utill.sendMsg(player,"&4Blad: &cNie masz komu odpisac!");
        }
        Player o = Bukkit.getPlayer(last);
        if (o == null) {
            return Utill.sendMsg(player,"&4Blad: &cNie znaleziono gracza: &7" + args[0] + "&c!");
        }
        String message = ChatColor.stripColor(Utill.fixColor(StringUtils.join(args, " ")));
        tellCommand.getLastMsg().put(player.getUniqueId(), o.getUniqueId());
        tellCommand.getLastMsg().put(o.getUniqueId(), player.getUniqueId());
        Utill.sendMsg(player, "&8[&3Ja &8-> &e" + o.getName() + "&8] &7" + message);
        return Utill.sendMsg(o, "&8[&e" + player.getName() + " &8-> &3Ja&8] &7" + message);
    }
}
