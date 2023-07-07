package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Ticking;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class gcCommand extends PlayerCommand
{
    public gcCommand() {
        super("gc", "Statystki serwera", "/gc", "core.command.gc", new String[0]);
    }

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        Utill.sendMsg((CommandSender)player, "&6Serwer:");
        Utill.sendMsg(player, "&8Graczy online: " + Bukkit.getOnlinePlayers().size());
        Utill.sendMsg((CommandSender)player, "&8» &7TPS: &a" + Ticking.getTPS());
        Utill.sendMsg((CommandSender)player, "&8» &7Online serwer: &a" + Utill.secondsToString((int)((System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime()) / 1000L)));
        Utill.sendMsg((CommandSender)player, "&8» &7Max RAM: &a" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "MB");
        Utill.sendMsg((CommandSender)player, "&7Total RAM: &a" + Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB");
        Utill.sendMsg((CommandSender)player, "&7Free RAM: &a" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + "MB");
        Utill.sendMsg((CommandSender)player, "");
        Utill.sendMsg((CommandSender)player, "&6Worlds:");
        final List<World> worlds = (List<World>) Bukkit.getWorlds();
        for (final World w : worlds) {
            int tileEntities = 0;
            Chunk[] loadedChunks;
            for (int length = (loadedChunks = w.getLoadedChunks()).length, i = 0; i < length; ++i) {
                final Chunk chunk = loadedChunks[i];
                tileEntities += chunk.getTileEntities().length;
            }
            Utill.sendMsg((CommandSender)player, " &8» &7" + w.getName() + ": &a" + w.getLoadedChunks().length + " chunks, " + w.getEntities().size() + " entities, " + tileEntities + " tilies");
        }
        Utill.sendMsg((CommandSender)player, "");
        Utill.sendMsg((CommandSender)player, "&6Watki:");
        final ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
        long full = 0L;
        for (final Thread t : Thread.getAllStackTraces().keySet()) {
            full += tmxb.getThreadCpuTime(t.getId());
        }
        for (final Thread t : Thread.getAllStackTraces().keySet()) {
            if (tmxb.getThreadCpuTime(t.getId()) > 0L) {
                final long l = tmxb.getThreadCpuTime(t.getId()) * 100L / full;
                if (l <= 0.0) {
                    continue;
                }
                Utill.sendMsg((CommandSender)player, " &8» &7" + t.getName() + ": &a" + l + "%");
            }
        }
        return true;
    }
}
