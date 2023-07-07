package me.mati.skyblock.tasks;

import me.mati.skyblock.Main;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearItemsTask extends BukkitRunnable {

    public void run() {
        Utill.sendMsg(Bukkit.getOnlinePlayers(),"&3Czysczenie itemow nastapi za 15 sekund!");
        new BukkitRunnable() {
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    world.getEntities().stream().filter(entity -> entity.getType().equals(EntityType.DROPPED_ITEM)).forEach(org.bukkit.entity.Entity::remove);
                }
                Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Wyczyszczono wszystkie przedmioty lezace na ziemi!");
            }
        }.runTaskLaterAsynchronously(Main.getPlugin(Main.class), 300L);
    }
}
