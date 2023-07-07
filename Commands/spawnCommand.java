package me.mati.skyblock.Commands;

import me.mati.skyblock.Managers.TimerManagers.TimerUtil;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class spawnCommand extends PlayerCommand {

    public spawnCommand() {
        super("spawn", "Teleport na spawn mapy", "/spawn", "sky.spawn");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        Location spawn = sender.getWorld().getSpawnLocation().add(0.5, 0.5, 0.5);
        if (args.length == 1 && sender.hasPermission("sky.spawn.others")) {
            User u = UserManager.getUser(args[0]);
            if (u == null) {
                return Utill.sendMsg(sender,"&4Blad: &cGracz nie istnieje w bazie danych!");
            }
            Player player = u.getPlayer();
            if (player == null) {
                return Utill.sendMsg(sender,"&4Blad: &cGracz nie jest online!");
            }
            player.teleport(spawn);
            Utill.sendMsg(player,"&7Zostales przeteleportowany na spawn przez &3" + sender.getName() + "&7!");
            return Utill.sendMsg(sender,"&7Przeteleportowano gracza &3" + player.getName() + " &7na spawn!");
        }
        if (sender.hasPermission("sky.spawn.nodelay")) {
            sender.teleport(spawn, PlayerTeleportEvent.TeleportCause.PLUGIN);
            return Utill.sendMsg(sender,"&aZostales przeteleportowany na spawn!");
        }
        if (sender.hasPermission("sky.spawn.teleport")) {
            TimerUtil.teleport(sender, spawn, 10);
        } else if(sender.hasPermission("sky.shorter.tp")){
            TimerUtil.teleport(sender, spawn, 5);
            return true;
        }
        return true;
    }
}
