package me.mati.skyblock.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSaveTask extends BukkitRunnable {
    @Override
    public void run() {
        for(World world : Bukkit.getWorlds()){
            world.save();
        }
        Bukkit.savePlayers();
    }
}
