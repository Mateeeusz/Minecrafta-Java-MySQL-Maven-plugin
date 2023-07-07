package me.mati.skyblock.listeners;

import me.mati.skyblock.Config;
import me.mati.skyblock.utils.RandomUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.stream.Collectors;

public class RandomTp implements Listener {

    public static List<Player> getPlayersInRadius(Location loc, int radius) {
        return loc.getWorld().getPlayers().stream().filter(player -> loc.distance(player.getLocation()) <= radius).collect(Collectors.toList());
    }

    @EventHandler
    public void onInteractRandomTP(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Block block = event.getClickedBlock();
        if (!block.getType().equals(Material.STONE_BUTTON)) {
            return;
        }
        if (!Config.IsButtonTP(block.getLocation())) {
            return;
        }
        Player p = event.getPlayer();
        RandomTP(p);
    }

    @EventHandler
    public void onInteractRandomTPG(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Block block = event.getClickedBlock();
        if (!block.getType().equals(Material.STONE_BUTTON)) {
            return;
        }
        if (!Config.IsButtonTPG(block.getLocation())) {
            return;
        }
        Player p = event.getPlayer();
        int x = RandomUtil.getRandInt(-1500, 1500);
        int z = RandomUtil.getRandInt(-1500, 1500);
        double y = p.getWorld().getHighestBlockYAt(x, z) + 1.5f;
        Location loc = new Location(p.getWorld(), (double) x, y, (double) z);
        for (Player inRadius : getPlayersInRadius(block.getLocation(), 2)) {
            inRadius.teleport(loc);
            Utill.sendMsg(inRadius, "&aZostales przeteleportowany na losowe koordynaty!");
        }


    }
    public static boolean RandomTP(Player p) {
        int x = RandomUtil.getRandInt(-1500, 1500);
        int z = RandomUtil.getRandInt(-1500, 1500);
        double y = p.getWorld().getHighestBlockYAt(x, z) + 1.5f;
        Location loc = new Location(p.getWorld(), (double)x, y, (double)z);
        p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        Utill.sendMsg(p,"&aZostales przeteleportowany na losowe koordynaty!");
        return true;
    }
}
