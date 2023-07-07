package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Main;
import me.mati.skyblock.base.Turbo;
import me.mati.skyblock.utils.Logger;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TurboDropManager {

    private static LinkedList<Turbo> turbodrops = new LinkedList<>();

    public static void loadAll() {
        ResultSet set = Main.getStore().query("SELECT * FROM `mvti_turbodrop`");
        try {
            while (set.next()) {
                Turbo turbodrop = new Turbo(set);
                TurboDropManager.turbodrops.add(turbodrop);
            }
            Logger.info("Loaded " + TurboDropManager.turbodrops.size() + " turbodrops!");
        } catch (SQLException e) {
            Logger.warning("An error occured while loading turbodrops!");
            e.printStackTrace();
        }
    }

    public static void createTurboDrop(String nick, Long expireTime) {
        Turbo turbodrop = new Turbo();
        turbodrop.setNick(nick);
        turbodrop.setExpireTime(expireTime);
        turbodrop.insert();
        TurboDropManager.turbodrops.add(turbodrop);
    }

    public static void deleteTurboDrop(Turbo turbodrop) {
        turbodrop.delete();
        TurboDropManager.turbodrops.remove(turbodrop);
    }

    public static Turbo getByNick(String nick) {
        for (Turbo turbodrop : TurboDropManager.turbodrops) {
            if (turbodrop.getNick().equalsIgnoreCase(nick)) {
                return turbodrop;
            }
        }
        return null;
    }

    public static Turbo getByPlayer(Player player) {
        return getByNick(player.getDisplayName());
    }

    public static LinkedList<Turbo> getTurboDrops() {
        return TurboDropManager.turbodrops;
    }

}
