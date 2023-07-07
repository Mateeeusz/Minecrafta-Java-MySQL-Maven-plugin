package me.mati.skyblock.tasks;

import me.mati.skyblock.Managers.MainManagers.CombatManager;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatLogTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream().filter(p -> CombatManager.wasInFight(p) && !CombatManager.isInFight(p)).forEach(p ->{
            Utill.sendMsg(p, "&aSkonczyles walczyc! Mozesz bezpiecznie sie wylogowac");
        });
    }
}
