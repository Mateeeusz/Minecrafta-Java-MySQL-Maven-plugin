package me.mati.skyblock.listeners;

import me.mati.skyblock.Main;
import me.mati.skyblock.listeners.guis.listeners.MainCraftingsProvider;
import me.mati.skyblock.utils.Utill;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BoyFarmerListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace( BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        Block under = b.getLocation().subtract(0.0, 1.0, 0.0).getBlock();
        if (e.isCancelled()) {
            return;
        }
        if (under.getType() != Material.ENDER_PORTAL_FRAME) {
            return;
        }
        if (b.getType() != Material.OBSIDIAN && b.getType() != Material.SAND && b.getType() != Material.STONE) {
            return;
        }
        Location loc = e.getBlock().getLocation().add(0.0,1.0, 0.0);
                if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                    if (b.getType() == Material.OBSIDIAN) {
                        under.setType(Material.OBSIDIAN);
                        int hight;
                        int i = hight = e.getBlockPlaced().getY() - 1;
                        while (i > 0) {
                            Block block;
                            if ((block = e.getBlock().getWorld().getBlockAt(e.getBlockPlaced().getX(), hight, e.getBlockPlaced().getZ())).getType() == Material.BEDROCK || block.getType() == Material.SEA_LANTERN) {
                                return;
                            }
                            Block block3 = block;
                            --hight;
                            block3.setType(Material.OBSIDIAN);
                            i = hight;
                        }
                        return;
                    }
                    else if (b.getType() == Material.SAND) {
                        under.setType(Material.SAND);
                        int hight;
                        int i = hight = e.getBlockPlaced().getY() - 1;
                        while (i > 0) {
                            Block block;
                            if ((block = e.getBlock().getWorld().getBlockAt(e.getBlockPlaced().getX(), hight, e.getBlockPlaced().getZ())).getType() == Material.BEDROCK || block.getType() == Material.SEA_LANTERN) {
                                return;
                            }
                            Block block3 = block;
                            --hight;
                            block3.setType(Material.SAND);
                            i = hight;
                        }
                        return;
                    }
                    else if (b.getType() == Material.STONE) {
                        under.setType(Material.AIR);
                        int hight;
                        int i = hight = e.getBlockPlaced().getY() - 1;
                        while (i > 0) {
                            Block block;
                            if ((block = e.getBlock().getWorld().getBlockAt(e.getBlockPlaced().getX(), hight, e.getBlockPlaced().getZ())).getType() == Material.BEDROCK || block.getType() == Material.SEA_LANTERN) {
                                return;
                            }
                            Block block3 = block;
                            --hight;
                            block3.setType(Material.AIR);
                            i = hight;
                        }
                        return;
                    }
                }

            }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace1(final BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType() == Material.ENDER_PORTAL_FRAME) {
            final Block b = e.getBlockPlaced();
            final Player p = e.getPlayer();
            if (b.getLocation().getBlockY() <= 80) {
                Utill.sendMsg((CommandSender)p, "&8» &7Pomyslnie &7utworzyles &2BoyFarmer. &7Teraz wystarczy postawic nad nim &aobsydian/piasek/kamien!");
            }
            else {
                Utill.sendMsg((CommandSender)p, "&4Blad: &cBoyFarmera mozna stawiac do poziomu Y: 80!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreakgeneator(final BlockDamageEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getBlock();
        if (b.getType() == Material.ENDER_PORTAL_FRAME && !p.getGameMode().equals((Object) GameMode.SURVIVAL)) {
            Utill.sendMsg((CommandSender)p, "&8» &6Pomyslnie &7zniszczyles &3BoyFarmer");
            b.setType(Material.AIR);
            return;
        }
        if (b.getType() == Material.ENDER_PORTAL_FRAME) {
            Utill.sendMsg((CommandSender)p, "&8» &6Pomyslnie &7zniszczyles &3BoyFarmer");
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack[] {MainCraftingsProvider.bfm });
        }
    }
}
