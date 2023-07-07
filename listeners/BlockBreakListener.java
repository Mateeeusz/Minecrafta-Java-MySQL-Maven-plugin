package me.mati.skyblock.listeners;

import me.mati.skyblock.Main;
import me.mati.skyblock.Managers.DropManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Block under = block.getLocation().subtract(0.0, 1.0, 0.0).getBlock();
        if (!under.getType().equals(Material.ENDER_STONE)) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                if (!under.getType().equals(Material.ENDER_STONE)) {
                    return;
                }
                block.setType(Material.STONE);
            }
        }.runTaskLater(Main.getPlugin(Main.class), 25L);
    }
    @EventHandler
    public void onBlockBreak2(BlockBreakEvent e) {
        Block block = e.getBlock();
        Block under = block.getLocation().subtract(0.0, 1.0, 0.0).getBlock();
        if (!under.getType().equals(Material.NETHERRACK)) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                if (!under.getType().equals(Material.NETHERRACK)) {
                    return;
                }
                block.setType(Material.STONE);
            }
        }.runTaskLater(Main.getPlugin(Main.class), 15L);

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onStoniarkaBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        if(block.getType() == Material.ENDER_STONE){
            Utill.sendMsg(e.getPlayer(),"&2Zniszczyles generator!");
        } else {
            return;
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onStoniarkaBreak2(BlockBreakEvent e){
        Block block = e.getBlock();
        if(block.getType() == Material.NETHERRACK){
            Utill.sendMsg(e.getPlayer(),"&2Zniszczyles turbo generator!");
        } else {
            return;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreakDrop(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (e.isCancelled()) {
            return;
        }
        if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        p.giveExp(DropManager.getExp(b.getType()));
        DropManager.getDropData(b.getType()).breakBlock(b, p, p.getItemInHand());
        e.setCancelled(true);
    }

    @EventHandler
    public void addBloki(BlockBreakEvent e){
        Block block = e.getBlock();
        User u =UserManager.getUser(e.getPlayer());
        if(block.getType() == Material.STONE){
            u.addStone(1);
        }else if(block.getType() == Material.OBSIDIAN){
            u.addObs(1);
        }
    }

}
