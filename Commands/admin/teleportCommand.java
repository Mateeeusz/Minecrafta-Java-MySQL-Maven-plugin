package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class teleportCommand extends PlayerCommand {

    public teleportCommand() {
        super("teleport", "Teleport do graczy lub koordynaty", "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>", "sky.tp", "tp");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        switch (args.length) {
            case 1: {
                Player other = Bukkit.getPlayer(args[0]);
                if (other == null) {
                    return Utill.sendMsg(sender,  "&4Blad: &cGracz nie jest online!");
                }
                sender.teleport(other, PlayerTeleportEvent.TeleportCause.COMMAND);
                Utill.sendMsg(sender,  "&7Przeteleportowano do gracza &3" + other.getName() + "&7!");
                break;
            }
            case 2: {
                if (!sender.hasPermission("easyagecore.teleport.others")) {
                    return Utill.sendMsg(sender, "&cNie masz uprawnien. &7(sky.tp)");
                }
                Player player = Bukkit.getPlayer(args[0]);
                Player o = Bukkit.getPlayer(args[1]);
                if (player == null || o == null) {
                    return Utill.sendMsg(sender, "&4Blad: &cGracz nie jest online!");
                }
                player.teleport(o, PlayerTeleportEvent.TeleportCause.COMMAND);
                Utill.sendMsg(player, "&7Zostales przeteleportowany do &3" + o.getName() + " &7przez &3" + sender.getName() + "&7!");
                Utill.sendMsg(sender, "&7Przeteleportowano gracza &3" + player.getName() + " &7do gracza &3" + o.getName() + "&7!");
                break;
            }
            case 3: {
                if (!Utill.isInteger(args[0]) || !Utill.isInteger(args[1]) || !Utill.isInteger(args[2])) {
                    return Utill.sendMsg(sender,"&4Blad: &cPodana wartosc nie jest liczba!");
                }
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);
                Location l = new Location(sender.getWorld(), (double) x, (double) y, (double) z).add(0.5, 0.5, 0.5);
                sender.teleport(l, PlayerTeleportEvent.TeleportCause.COMMAND);
                Utill.sendMsg(sender, "&7Przeteleportowano na koordynaty &3{" + x + ", " + y + ", " + z + "}&7!");
                break;
            }
            case 4: {
                if (!sender.hasPermission("easyagecore.teleport.others")) {
                    return Utill.sendMsg(sender, "&cNie masz uprawnien. &7(sky.tp)");
                }
                Player ot = Bukkit.getPlayer(args[0]);
                if (ot == null) {
                    return Utill.sendMsg(sender, "&4Blad: &cGracz nie jest online!");
                }
                if (!Utill.isInteger(args[1]) || !Utill.isInteger(args[2]) || !Utill.isInteger(args[3])) {
                    return Utill.sendMsg(sender,"&4Blad: &cPodana wartosc nie jest liczba!");
                }
                int x2 = Integer.parseInt(args[1]);
                int y2 = Integer.parseInt(args[2]);
                int z2 = Integer.parseInt(args[3]);
                Location l2 = new Location(sender.getWorld(), (double) x2, (double) y2, (double) z2).add(0.5, 0.5, 0.5);
                ot.teleport(l2, PlayerTeleportEvent.TeleportCause.COMMAND);
                Utill.sendMsg(ot,"&7Zostales przeteleportowany na koordynaty &3{" + x2 + ", " + y2 + ", " + z2 + "}&7 przez &3" + sender.getName() + "&7!");
                Utill.sendMsg(sender, "&7Przeteleportowano gracza &3" + ot.getName() + " &7na koordynaty &3{" + x2 + ", " + y2 + ", " + z2 + "}&7!");
                break;
            }
            default: {
                Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
                break;
            }
        }
        return true;
    }
}
