package me.mati.skyblock.api.commands;

import me.mati.skyblock.utils.Utill;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends Command
{
    public PlayerCommand(final String name, final String desc, final String usage, final String permission, final String... aliases) {
        super(name, desc, usage, permission, aliases);
    }

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player)) {
            return Utill.sendMsg(sender, "&4Blad: &cMusisz byc graczem by to uzyc!");
        }
        return this.onCommand((Player)sender, args);
    }

    public abstract boolean onCommand(final Player p0, final String[] p1);
}