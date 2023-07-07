package me.mati.skyblock.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class BugListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityPortal(EntityPortalEvent event) {
        if (event.getEntityType() == EntityType.EXPERIENCE_ORB || event.getEntityType() == EntityType.DROPPED_ITEM) {
            event.setCancelled(true);
        }
    }
}
