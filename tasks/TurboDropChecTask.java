package me.mati.skyblock.tasks;

import me.mati.skyblock.Managers.MainManagers.TurboDropManager;
import me.mati.skyblock.base.Turbo;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TurboDropChecTask extends BukkitRunnable {

    public void run() {
        for (Turbo turbodrop : TurboDropManager.getTurboDrops()) {
            if (turbodrop.getExpireTime() == 0L) {
                continue;
            }
            if (System.currentTimeMillis() >= turbodrop.getExpireTime()) {
                Player player = turbodrop.getPlayer();
                if (player != null) {
                    Utill.sendMsg(player, " &2\u0fc3 &7Czas Twojego &3TurboDropa &7zakonczyl sie - mozliwosc wykupienia &3www.stephc.pl");
                }
                TurboDropManager.deleteTurboDrop(turbodrop);
            }
        }
    }

}
