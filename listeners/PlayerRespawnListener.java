package me.mati.skyblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(e.getPlayer().getWorld().getSpawnLocation());
    }
}
