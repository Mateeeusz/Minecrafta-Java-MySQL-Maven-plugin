package me.mati.skyblock.Managers.TimerManagers;

import me.mati.skyblock.utils.Utill;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TimerUtil {
    public static void teleport(final Player p, final Location location, final int delay) {
        if (!p.hasPermission("easyagetimer.bypass")) {
            Utill.sendMsg(p, "&7Teleport nastapi za &3" + Utill.secondsToString(delay) + "&7!");
        }
        TimerManager.addTask(p, new TimerCallback<Player>() {
            @Override
            public void success(final Player player) {
                player.teleport(location);
                Utill.sendMsg(player, "&aPrzeteleportowano!");
            }

            @Override
            public void error(final Player player) {
                Utill.sendMsg(player, "&4Blad: &cTeleport zostal przerwany!");
            }
        }, delay);
    }
}