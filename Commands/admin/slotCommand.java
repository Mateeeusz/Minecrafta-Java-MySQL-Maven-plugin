package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.command.CommandSender;

public class slotCommand extends Command {

    public slotCommand() {
        super("slot", "ustawianie liczby slotow", "/slot <liczba>", "sky.slot");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return Utill.sendMsg(sender,"&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (!Utill.isInteger(args[0])) {
            return Utill.sendMsg(sender, "&4Blad: &cPodana wartosc nie jest liczba!");
        }
        int slots = Config.SLOTMANAGER_SLOTS = Integer.parseInt(args[0]);
        return Utill.sendMsg(sender, "&7Pomyslnie ustawiono liczbe slotow na &3" + slots + "&7!");
    }
}

