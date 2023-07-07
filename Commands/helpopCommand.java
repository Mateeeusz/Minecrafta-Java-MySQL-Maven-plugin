package me.mati.skyblock.Commands;


import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class helpopCommand extends PlayerCommand {

    public helpopCommand() {
        super("helpop", "kontakt z administracja ", "helpop <wiadomosc>", "sky.helpop", "adminpomocy");
    }

    private static HashMap<UUID, Long> time;

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length < 1) {
            Utill.sendMsg(p, Utill.fixColor("&4Blad: &cPoprawne uzycie:" +  this.getUsage()));
            return false;
        }
        if (helpopCommand.time.containsKey(p.getUniqueId()) && System.currentTimeMillis() < helpopCommand.time.get(p.getUniqueId())) {
            p.sendMessage(Utill.fixColor("&cNie mozesz jeszcze napisac wiadomosci na helpop"));
            return false;
        }
        String msg = args[0];
        if (args.length > 1) {
            for (int i = 1; i < args.length; ++i) {
                msg = msg + " " + args[i];
            }
        }
        for ( Player proxiedPlayer : Bukkit.getOnlinePlayers()) {
            if (proxiedPlayer.hasPermission("sky.helpop.recive")){
                proxiedPlayer.sendMessage("Helpop" + p.getName() + " -> " + msg);
            }
        }
        p.sendMessage(Utill.fixColor("&2Wyslano wiadomosc na helpopie!"));
        helpopCommand.time.put(p.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L));
        return true;
    }

    static {
        helpopCommand.time = new HashMap<>();
    }
}
