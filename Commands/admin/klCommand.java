package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class klCommand extends PlayerCommand {

    public klCommand() {
        super("kl", "zmienianie statusu kitVIPa", "/kl", "sky.kl", "kl");
    }

    private static boolean KIT = true;

    public static void setKIT(boolean KIT) {
        klCommand.KIT = KIT;
    }

    public static boolean isKit() {
        return klCommand.KIT;
    }


    public static void toggleKIT(Player p) {
        setKIT(!isKit());
        Utill.sendMsg(p, "&7Kit VIP zostal &3" + (isKit() ? "wlaczony" : "wylaczony") + "&7!");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        toggleKIT(player);
        return true;
    }
}
