package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class rankingResetCommand extends Command {
    public rankingResetCommand() {
        super("resetujranking", "resetuje ranking", "/resetujranking <gracz>", "stephc.rankingreset", "resetrankingu");
    }

    @Override
    public boolean onExecute(CommandSender p, String[] args) {
        if(args.length != 1){
            Utill.sendMsg(p, "&4Blad: &cpoprawne uzcie " + this.getUsage());
        } else {
            if(p.hasPermission("stephc.reset.others")){
                if(Bukkit.getPlayer(args[0]) == null){
                    Utill.sendMsg(p, "&4Blad: podany gracz jest offline");
                } else {
                    User u = User.get(Bukkit.getPlayer(args[0]));
                    u.getRank().setPoints(1000);
                    Utill.sendMsg(p, "&7Ranking gracza " + Bukkit.getPlayer(args[0]).getDisplayName() + " zostal zresetowany");
                    Utill.sendMsg(Bukkit.getPlayer(args[0]), "&7Twoj ranking zostal zresetowany przez: " + p.getName());
                }
            } else {
                Utill.sendMsg(p, "&4Blad nie posiadasz permisji: stephc.reset.others");
            }
        }
        return false;
    }
}
