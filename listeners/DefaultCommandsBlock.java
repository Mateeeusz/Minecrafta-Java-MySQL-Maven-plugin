package me.mati.skyblock.listeners;

import me.mati.skyblock.Config;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class DefaultCommandsBlock implements Listener {

    private static List<String> allowCommands;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        for (String blockedCommand : DefaultCommandsBlock.allowCommands) {
            if (e.getMessage().toLowerCase().startsWith(blockedCommand)) {
                return;
            }
        }
        e.getPlayer().sendMessage(Utill.fixColor(Config.INFO_HELP));
        e.setCancelled(true);
    }
}

