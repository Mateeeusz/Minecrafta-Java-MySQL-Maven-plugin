package me.mati.skyblock.api.commands;

import me.mati.skyblock.utils.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.util.HashMap;

public class CommandManager
{
    private static final HashMap<String, Command> commands;
    private static final Reflection.FieldAccessor<SimpleCommandMap> f;
    private static CommandMap cmdMap;

    public static void register(final Command cmd) {
        if (CommandManager.cmdMap == null) {
            CommandManager.cmdMap = CommandManager.f.get(Bukkit.getServer().getPluginManager());
        }
        CommandManager.cmdMap.register(cmd.getName(), cmd);
        CommandManager.commands.put(cmd.getName(), cmd);
    }

    static {
        commands = new HashMap<>();
        f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
        CommandManager.cmdMap = CommandManager.f.get(Bukkit.getServer().getPluginManager());
    }
}
