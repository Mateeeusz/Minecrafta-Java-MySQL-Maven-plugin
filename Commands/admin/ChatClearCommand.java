package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand extends Command {
    public ChatClearCommand() {
        super("chatcleat", "czyszczenie czatu", "/chatclear", "sky.cl", "cc");
    }

    @Override
    public boolean onExecute(CommandSender sender, String[] args) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; ++i) {
                p.sendMessage("");
            }
        }
        Utill.broadcast(Config.COMMAND_CHAT_CLEAR.replace("<GRACZ>", sender.getName()));
        return true;
    }
}