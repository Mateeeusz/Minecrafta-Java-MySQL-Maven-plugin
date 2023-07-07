package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class headCommand extends PlayerCommand {

    public headCommand() {
        super("head", "pobierania glowy gracza", "/head [gracz]", "sky.head");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        String owner = player.getName();
        if (args.length == 1) {
            owner = args[0];
        }
        player.getInventory().addItem(ItemUtil.getPlayerHead(owner));
        return Utill.sendMsg(player,"&7Glowa gracza &3" + owner + " &7zostala dodana do twojego ekwipunku!");
    }
}
