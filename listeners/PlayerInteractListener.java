package me.mati.skyblock.listeners;

import me.mati.skyblock.utils.Utill;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getMaterial() == Material.TNT && e.getItem().getItemMeta().getDisplayName() != null) {
            if (e.getItem().getItemMeta().getDisplayName().equals(Utill.fixColor("&4&lRzucane TNT"))) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                    p.getLocation().setY(p.getLocation().getY() + 1.0);
                    Entity entity = null;
                    entity = p.getWorld().spawn(p.getLocation(), (Class) TNTPrimed.class);
                    entity.setVelocity(p.getLocation().getDirection().multiply(1.1));
                    if (p.getItemInHand().getAmount() > 1) {
                        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                    }
                    else {
                        p.setItemInHand((ItemStack)null);
                    }
                }
            }
            else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void RzucakPlace( PlayerInteractEvent playerInteractEvent) {
        if ((playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) && playerInteractEvent.getPlayer().getItemInHand().getType().equals((Object)Material.TNT) && playerInteractEvent.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Utill.fixColor("&4&lRzucane TNT"))) {
            if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (!playerInteractEvent.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                    ItemStack itemInHand;
                    (itemInHand = playerInteractEvent.getPlayer().getItemInHand()).setAmount(itemInHand.getAmount() - 1);
                    playerInteractEvent.getPlayer().setItemInHand(itemInHand);
                }
                ((TNTPrimed)playerInteractEvent.getPlayer().getWorld().spawn(playerInteractEvent.getClickedBlock().getLocation().add(0.0, 1.0, 0.0), (Class)TNTPrimed.class)).setFuseTicks(60);
            }
        }
    }
}
