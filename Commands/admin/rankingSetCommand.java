package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class rankingSetCommand extends Command {
    public rankingSetCommand() {
        super("rankingset", "ustawianie rankingu", "rankingset <gracz> <ranking>", "stephc.setrank", "rankingustaw");
    }

    @Override
    public boolean onExecute(CommandSender p, String[] args) {
        if(args.length != 2){
            Utill.sendMsg(p, "&4Blad: &7poprawne uzycie " + this.getUsage());
        } else {
            if(Bukkit.getPlayer(args[0]) == null){
                Utill.sendMsg(p, "&4Blad: &cgracz o podanym nicku jest offline");
            } else {
                if(!Utill.isInteger(args[1])){
                    Utill.sendMsg(p, "&4Blad: &cblad podany argument nie jest liczba " + args[1]);
                } else {
                    User u = User.get(Bukkit.getPlayer(args[0]));
                    u.getRank().setPoints(Integer.parseInt(args[1]));
                    Utill.sendMsg(p, "&7Ustawiles ranking gracza: " + Bukkit.getPlayer(args[0]).getDisplayName() + " na " + args[1]);
                    Utill.sendMsg(Bukkit.getPlayer(args[0]), "&7Twoj ranking zostal ustawiony przez: " + p.getName() + " na " + args[1]);
                }
            }
        }
        return false;
    }
}
