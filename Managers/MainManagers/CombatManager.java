package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Config;
import me.mati.skyblock.utils.TimeUtil;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public class CombatManager {

    private static HashMap<UUID, Long> combats = new HashMap<>();
    private static DecimalFormat df = new DecimalFormat("#.###");


    public static void createPlayer(Player p){
        combats.put(p.getUniqueId(), 0L);
    }
    public static void removePlayer(Player p){
        combats.remove(p.getUniqueId());
    }
    public static long getLastFight(Player p){
        return combats.get(p.getUniqueId());
    }
    public static void setLastFight(Player p){
        combats.put(p.getUniqueId(), System.currentTimeMillis());
    }

    public static boolean isInFight(Player p) {
        Long time = combats.get(p.getUniqueId());
        return time != null && System.currentTimeMillis() - time < TimeUtil.SECOND.getTime(Config.COMBAT_TIME);
    }

    public static boolean wasInFight(Player p) {
        Long time = combats.get(p.getUniqueId());
        return time != null && System.currentTimeMillis() - time - 1000L < TimeUtil.SECOND.getTime(Config.COMBAT_TIME);
    }

    public static long getTimeToEnd(Player p) {
        Long time = combats.get(p.getUniqueId());
        if (time == null || time == 0L) {
            return 0L;
        }
        return TimeUtil.SECOND.getTime(Config.COMBAT_TIME) - (System.currentTimeMillis() - time);
    }

    public static String getTime(Player p) {
        return df.format(getTimeToEnd(p) / 1000.0);
    }

    public static HashMap<UUID, Long> getCombats() {
        return combats;
    }
}
