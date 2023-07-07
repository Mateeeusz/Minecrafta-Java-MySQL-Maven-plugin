package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Main;
import me.mati.skyblock.base.Ban;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Logger;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class BanManager {
    public static HashMap<UUID, Ban> bans = new HashMap<>();

    public static Ban createBan(UUID uuid, String reason, String admin, String username, long expireTime) {
        Ban b = new Ban(uuid, reason, admin, username, expireTime);
        BanManager.bans.put(uuid, b);
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
        if (op.isOnline()) {
            String kick = "&7Zostales zbanowany!\n" + "&7Przez: &3" + b.getAdmin() + "&7, dnia: &3" + Utill.getDate(b.getCreateTime()) + "&7!\n" + "&7Powod: &3" + b.getReason() + "&7.";
            if (b.getExpireTime() > 0L) {
                kick = kick + "\n" + "&7Wygasa: &3" + Utill.getDate(b.getExpireTime()) + "&8(&e" + Utill.secondsToString((int) (b.getExpireTime() - System.currentTimeMillis()) / 1000) + "&8)";
            }
            op.getPlayer().kickPlayer(Utill.fixColor(kick));
        }
        return b;
    }

    public static void deleteBan(Ban b) {
        b.delete();
        bans.remove(b.getUuid());
    }

    public static Ban getBan(User paramUser) {
        return bans.get(paramUser.getUuid());
    }

    public static Ban getBan(UUID paramUuid) {
        return bans.get(paramUuid);
    }

    public static void setup() {
        ResultSet rs = Main.getStore().query("SELECT * FROM `mvti_bans` WHERE `unban`=0 AND (`expireTime`=0 OR `expireTime`> " + System.currentTimeMillis() + ")");
        try {
            while (rs.next()) {
                Ban b = new Ban(rs);
                bans.put(b.getUuid(), b);
            }
            Logger.info("Loaded " + bans.size() + " bans!");
        } catch (SQLException e) {
            Logger.warning("An error occurred while loading bans!", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HashMap<UUID, Ban> getBans() {
        return bans;
    }
}
