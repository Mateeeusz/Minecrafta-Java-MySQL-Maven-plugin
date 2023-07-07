package me.mati.skyblock.listeners;


import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Calendar;

public class EntityExplodeListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityExplode( EntityExplodeEvent event) {
        event.blockList().stream().filter(block -> block.getType() == Material.ENDER_STONE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.EMERALD_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.COAL_ORE || block.getType() == Material.LAPIS_ORE).forEach(block -> block.setType(Material.AIR));

        Calendar c = Calendar.getInstance();
        int hr = c.get(11);
        if (!event.getEntity().getType().equals(EntityType.CREEPER) || !event.getEntity().getType().equals(EntityType.PRIMED_TNT) || !event.getEntity().getType().equals(EntityType.MINECART_TNT)) {
            return;
        }
        if ((hr <= 23 || hr > 10)) {
            return;
        }
        event.setCancelled(true);

    }
}
