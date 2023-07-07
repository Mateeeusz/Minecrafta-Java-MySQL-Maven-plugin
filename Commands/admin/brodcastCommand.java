package me.mati.skyblock.Commands.admin;

import com.connorlinfoot.titleapi.TitleAPI;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class brodcastCommand extends Command {
    public brodcastCommand() {
        super("bc", "oglasza na serwerze", "/bc <wiadomosc>", "sky.bc", "brodcast");
    }

    @Override
    public boolean onExecute(CommandSender p, String[] args) {
            if(args.length >=1){
                String msg = "";
                for(int i = 0; i < args.length;i++){
                    msg = msg +args[i] + " ";
                }
                for (Player p1 : Bukkit.getOnlinePlayers()){
                    TitleAPI.sendFullTitle(p1, 40, 40, 40, Utill.fixColor("&4UWAGA!"), Utill.fixColor("&8" + msg));
                }
            } else {
                p.sendMessage(Utill.fixColor("&4Blad! Uzycie: &1" +this.getUsage()));
            }
        return false;
    }
}
