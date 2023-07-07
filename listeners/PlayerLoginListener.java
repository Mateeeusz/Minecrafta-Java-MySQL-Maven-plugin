package me.mati.skyblock.listeners;

import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.base.Ban;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (Bukkit.getOnlinePlayers().size() >= Config.SLOTMANAGER_SLOTS && !p.hasPermission("sky.join.bypass")) {
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, Utill.fixColor("&7Serwer jest pelen. Sprobuj pozniej lub zakup range &3Premium!\n" + "&7Range mozesz zakupic na naszej stronie: &3www.erhc.pl"));
            return;
        }
        Ban ban = BanManager.getBan(e.getPlayer().getUniqueId());
        if (ban != null) {
            if (ban.isAlive()) {
                BanManager.deleteBan(ban);
                return;
            }
            String kick = "&7Zostales zbanowany!\n" + "&7Przez: &3" + ban.getAdmin() + "&7, dnia: &3" + Utill.getDate(ban.getCreateTime()) + "&7!\n" + "&7Powod: &3" + ban.getReason() + "&7.";
            if (ban.getExpireTime() > 0L) {
                kick = kick + "\n" + "&7Wygasa: &3" + Utill.getDate(ban.getExpireTime()) + "&8(&e" + Utill.secondsToString((int) (ban.getExpireTime() - System.currentTimeMillis()) / 1000) + "&8)";
            }
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Utill.fixColor(kick));
        }
    }

}
