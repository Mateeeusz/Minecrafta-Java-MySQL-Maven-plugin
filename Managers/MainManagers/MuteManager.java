package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Main;
import me.mati.skyblock.base.Mute;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Logger;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class MuteManager {

    private static HashMap<UUID, Mute> mutes = new HashMap<>();

    public static Mute createMute(UUID uuid, String nickname, String reason, String admin, long expireTime) {
        Mute m = new Mute(uuid, nickname, reason, admin, expireTime);
        MuteManager.mutes.put(uuid, m);
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
        if (op.isOnline()) {
            Utill.sendMsg(op.getPlayer(), "&7Zostales zmutowany!");
            Utill.sendMsg(op.getPlayer(), "&7Przez: &3" + m.getAdmin() + "&7, dnia: &3" + Utill.getDate(m.getCreateTime()) + "&7!");
            Utill.sendMsg(op.getPlayer(), "&7Powod: &3" + m.getReason() + "&7.");
            if (m.getExpireTime() > 0L) {
                Utill.sendMsg(op.getPlayer(), "&7Wygasa: &3" + Utill.getDate(m.getExpireTime()) + " &8(&e" + Utill.secondsToString((int) (m.getExpireTime() - System.currentTimeMillis()) / 1000) + "&8)");
            }
        }
        return m;
    }

    public static void deleteMute(Mute m) {
        m.delete();
        MuteManager.mutes.remove(m.getUuid());
    }

    public static Mute getMute(User paramUser) {
        return MuteManager.mutes.get(paramUser.getUuid());
    }

    public static Mute getMute(UUID paramUuid) {
        return MuteManager.mutes.get(paramUuid);
    }

    public static void setup() {
        ResultSet rs = Main.getStore().query("SELECT * FROM `mvti_mutes` WHERE `unmute`=0 AND (`expireTime`=0 OR `expireTime`> " + System.currentTimeMillis() + ")");
        try {
            while (rs.next()) {
                Mute m = new Mute(rs);
                MuteManager.mutes.put(m.getUuid(), m);
            }
            Logger.info("Loaded " + MuteManager.mutes.size() + " mutes!");
        } catch (SQLException e) {
            Logger.warning("An error occurred while loading mutes!", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HashMap<UUID, Mute> getMutes() {
        return MuteManager.mutes;
    }
}

