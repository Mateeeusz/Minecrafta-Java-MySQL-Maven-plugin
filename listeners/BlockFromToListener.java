package me.mati.skyblock.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToListener implements Listener {
    @EventHandler
    public void onWather( BlockFromToEvent e) {
        Material id;
        if ((id = e.getBlock().getType()) == Material.WATER || id == Material.STATIONARY_WATER || id == Material.LAVA
                || id == Material.STATIONARY_LAVA) {
            e.setCancelled(true);
        }
    }
}
