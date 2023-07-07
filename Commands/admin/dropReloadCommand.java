package me.mati.skyblock.Commands.admin;


import me.mati.skyblock.Managers.DropFile;
import me.mati.skyblock.Managers.DropManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.command.CommandSender;

public class dropReloadCommand extends Command {

    public dropReloadCommand() {
        super("dropreload", "Przeladowanie konfiguracji dropu.", "/dropreload", "easyagedrop.reload");
    }

    public boolean onExecute(CommandSender sender, String[] args) {
        DropFile.reloadConfig();
        DropManager.setup();
        return Utill.sendMsg(sender, "&aPrzeladowano plik drops.yml! Przeladowano konfiguracje dropow!");
    }
}
