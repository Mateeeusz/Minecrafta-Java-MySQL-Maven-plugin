package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.command.CommandSender;

public class slowmodeCommand extends Command {

    public slowmodeCommand() {
        super("slowmode", "ustawianie slowmode czatu", "/slowmode <czas>", "easyagecore.slowmode", "slow");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return Utill.sendMsg(sender,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (!Utill.isInteger(args[0])) {
            return Utill.sendMsg(sender,"&4Blad: Podana wartosc nie jest liczba!");
        }
        int slowmode = Config.CHAT_SLOWMODE = Integer.parseInt(args[0]);
        return Utill.sendMsg(sender, "&7Pomyslnie ustawiono slowmode czatu na &3" + Utill.secondsToString(slowmode) + "&7!");
    }

}
