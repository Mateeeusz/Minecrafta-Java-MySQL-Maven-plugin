package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Config;
import me.mati.skyblock.utils.TimeUtil;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.WeakHashMap;

public class ChatManager {

    private static WeakHashMap<UUID, Long> times = new WeakHashMap<>();


    public static boolean canSendMessage(Player p) {
        if (p.hasPermission("stephc.chat.bypass")) {
            return true;
        }
        Long time = ChatManager.times.get(p.getUniqueId());
        return time == null || System.currentTimeMillis() - time >= TimeUtil.SECOND.getTime(Config.CHAT_SLOWMODE);
    }

    public static WeakHashMap<UUID, Long> getTimes() {
        return ChatManager.times;
    }
}
