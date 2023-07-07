package me.mati.skyblock.tasks;

import me.mati.skyblock.Config;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
public class AutomsgTask extends BukkitRunnable {
    private int index;

    public AutomsgTask() {
        super();
        this.index = 0;
    }

    public void run() {
        if (this.index >= Config.AUTOMESSAGE_MESSAGES.size()) {
            this.index = 0;
        }
        Utill.sendMsg(Bukkit.getOnlinePlayers(), Config.AUTOMESSAGE_PREFIX + "&4" + Config.AUTOMESSAGE_MESSAGES.get(this.index));
        ++this.index;
    }
}
