package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class gmCommand extends PlayerCommand {
    public gmCommand() {
        super("gm", "zmiana gamemode", "/gm <1/0> <gracz>", "sky.gm", "gamemode");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        String[] survival = {"0", "s", "survival"};
        String[] creative = {"1", "c", "creative"};
            if(args.length == 1){
                String mode = args[0];
                if(Utill.containsIgnoreCase(survival, mode)){
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Utill.fixColor(Config.GAMEMODE_CHANGE_SURVIVAL));
                } else if(Utill.containsIgnoreCase(creative, mode)){
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Utill.fixColor(Config.GAMEMODE_CHANGE_CREATIVE));
                }
            } else if(args.length == 2){
                Player other = Bukkit.getPlayer(args[1]);
                if(other == null){
                    p.sendMessage(Utill.fixColor("&4Blad: &cGracz : " + other.getDisplayName() + " jest offline"));
                } else {
                    GameMode othergm = other.getGameMode();
                    if(othergm.equals(GameMode.SURVIVAL)){
                        other.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Utill.fixColor(Config.PLAYER_GAMEMODE_CHANGE).replace("{PLAYER}", other.getDisplayName()).replace("{MODE}", other.getGameMode().name()));
                        other.sendMessage(Utill.fixColor("&2Gamemode zmieniony na survival przez :" + p.getDisplayName()));
                    } else if(othergm.equals(GameMode.CREATIVE)){
                        other.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Utill.fixColor(Config.PLAYER_GAMEMODE_CHANGE).replace("{PLAYER}", other.getDisplayName()).replace("{MODE}", other.getGameMode().name()));
                        other.sendMessage(Utill.fixColor("&2Gamemode zmieniony na creative przez :" + p.getDisplayName()));
                    }
                }
            } else if(args.length != 2){
                p.sendMessage("&4Blad: &cPoprawne uzycie: " + this.getUsage());
            }
        return false;
    }
}
