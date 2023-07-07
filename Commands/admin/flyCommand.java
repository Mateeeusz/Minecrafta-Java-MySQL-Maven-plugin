package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class flyCommand extends PlayerCommand {
    public flyCommand() {
        super("fly", "wlaczanie trybu latania", "", "sky.fly", "latanie");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if(args.length == 1){
            Player player = Bukkit.getPlayer(args[0]);
            if(player == null){
                Utill.sendMsg(p, Utill.fixColor(Config.FLY_PLAYER_NOT_EXIST).replace("{PLAYER", player.getDisplayName()));
            } else {
                player.setAllowFlight(!p.getAllowFlight());
                Utill.sendMsg(p, (player.getAllowFlight()) ? Utill.fixColor(Config.FLY_ENABLE_OTHER).replace("<PLAYER>", player.getDisplayName()) : Utill.fixColor(Config.FLY_DISABLE_OTHER).replace("<PLAYER>", player.getDisplayName()));
            }
        } else if(args.length == 0){
            p.setAllowFlight(!p.getAllowFlight());
            Utill.sendMsg(p, (p.getAllowFlight() ? Utill.fixColor(Config.Fly_ENABLE) : Utill.fixColor(Config.FLY_DISABLE)));
        } else {
            Utill.sendMsg(p, "&4Blad: &cpoprawne uzycie" + this.getUsage());
        }
        return false;
    }
}
