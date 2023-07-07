package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;


public class setSpawnCommand extends PlayerCommand {

    public setSpawnCommand() {
        super("setspawn", "Ustawia spawn mapy", "/setspawn", "sky.setspawn");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
        return Utill.sendMsg(p, "&aPomyslnie ustawiono spawn!");
    }
}