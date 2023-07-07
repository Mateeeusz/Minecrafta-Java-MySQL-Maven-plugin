package me.mati.skyblock.listeners;

import me.mati.skyblock.Commands.admin.checkCommand;
import me.mati.skyblock.Managers.MainManagers.CombatManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class OnLeave implements Listener {

    public static void quitGame(Player p) {
        User u = UserManager.getUser(p);
        u.setLastLocation(p.getLocation());
        net.dzikoysk.funnyguilds.basic.user.User user = net.dzikoysk.funnyguilds.basic.user.User.get(p);
        if (CombatManager.isInFight(p)) {
            user.getRank().removePoints(50);
            p.setHealth(0.0D);
        }
    }


    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        UserManager.leaveFromGame(e.getPlayer());
        User u = UserManager.getUser(e.getPlayer());
        e.setQuitMessage(null);
        quitGame(e.getPlayer());
        if (u.isCheck()) {
            return;
        }
        checkCommand.EndCheck(Bukkit.getPlayer(u.getChecker()), e.getPlayer(), checkCommand.CheckResult.QUIT);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onKick(PlayerKickEvent e) {
        UserManager.leaveFromGame(e.getPlayer());
        User u = UserManager.getUser(e.getPlayer());
        u.setLastLocation(e.getPlayer().getLocation());
        e.setLeaveMessage(null);
    }
}
