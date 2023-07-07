package me.mati.skyblock.Managers.TimerManagers;

import me.mati.skyblock.Main;
import me.mati.skyblock.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimerManager implements Listener {
    private static final Map<UUID, TimerTask> tasks = new HashMap<>();

    public static void addTask(final Player player, final TimerCallback<Player> call, final int time) {
        if (player.hasPermission("skytimer.bypass")) {
            call.success(player);
            return;
        }
        TimerTask t = TimerManager.tasks.get(player.getUniqueId());
        if (t != null) {
            t.cancel(player);
            return;
        }
        t = new TimerTask(player.getUniqueId(), call);
        TimerManager.tasks.put(player.getUniqueId(), t);
        t.runTaskLater(Main.getPlugin(Main.class), (long) TimeUtil.SECOND.getTick(time));
    }

    private static void cancel(final TimerTask task, final Player player) {
        task.cancel(player);
        TimerManager.tasks.remove(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMove(final PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockY() == event.getTo().getBlockY() && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return;
        }
        final TimerTask t = TimerManager.tasks.get(event.getPlayer().getUniqueId());
        if (t != null) {
            cancel(t, event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            final TimerTask t = TimerManager.tasks.get(player.getUniqueId());
            if (t != null) {
                cancel(t, player);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final TimerTask t = TimerManager.tasks.get(player.getUniqueId());
        if (t != null) {
            cancel(t, player);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
            final TimerTask t = TimerManager.tasks.get(p.getUniqueId());
            if (t != null) {
                cancel(t, p);
            }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
            final TimerTask t = TimerManager.tasks.get(p.getUniqueId());
            if (t != null) {
                cancel(t, p);
            }
    }

    public static class TimerTask extends BukkitRunnable {
        private UUID player;
        private TimerCallback<Player> call;

        public TimerTask(final UUID player, final TimerCallback<Player> call) {
            super();
            this.player = player;
            this.call = call;
        }

        public void run() {
            this.call.success(Bukkit.getPlayer(this.player));
            TimerManager.tasks.remove(this.player);
        }

        public void cancel(final Player player) {
            super.cancel();
            this.call.error(player);
        }

        public UUID getPlayer() {
            return this.player;
        }
    }
}
