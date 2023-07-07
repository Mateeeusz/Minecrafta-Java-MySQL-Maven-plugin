package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class vanishCommand extends PlayerCommand {
    public vanishCommand() {
        super("vanish", "usuwa gracza", "/vanish", "stephc.vanish", "v");
    }

    public static ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(Player p, String[] args) {
        if(args.length != 0){
            Utill.sendMsg(p, "&4Blad: &cpoprawne uzycie: " + this.getUsage());
        }
        if(vanished.contains(p)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.showPlayer(p);
            }
            vanished.remove(p);
            Utill.sendMsg(p, "&2Vanish wylaczony!");
        } else {
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!player.hasPermission("stephc.vanish.seeothers")){
                    player.hidePlayer(p);
                }
            }
            vanished.add(p);
            Utill.sendMsg(p, "&2Vanish wlaczony!");
        }

        return false;
    }
}
