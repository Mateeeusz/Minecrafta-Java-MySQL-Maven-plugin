package me.mati.skyblock.guisCommands;

import fr.minuskube.inv.SmartInventory;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.EfektyProvider;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class efektyCommand extends PlayerCommand {
    public efektyCommand() {
        super("efekty", "efekty dla gracza", "/efekty", "sky.efekty", "potki");
    }

    public static SmartInventory efektyGUI = SmartInventory.builder()
            .id("efekty")
            .provider(new EfektyProvider())
            .size(5, 9)
            .title(Utill.fixColor("&8EFEKTY"))
            .closeable(true)
            .build();

    @Override
    public boolean onCommand(Player p, String[] args) {
        efektyGUI.open(p);
        return false;
    }
}
