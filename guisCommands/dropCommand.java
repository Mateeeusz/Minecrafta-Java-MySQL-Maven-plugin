package me.mati.skyblock.guisCommands;

import fr.minuskube.inv.SmartInventory;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.dropProvider;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class dropCommand extends PlayerCommand {
    public dropCommand() {
        super("drop", "drop z stone", "/drop", "stephc.drop", "kamien");
    }

    @Override
    public boolean onCommand(Player p0, String[] p1) {
        dropinv.open(p0);
        return false;
    }
    public static SmartInventory dropinv = SmartInventory.builder()
            .id("dropinv")
            .provider(new dropProvider())
            .size(5, 9)
            .title(Utill.fixColor("&8DROP"))
            .closeable(true)
            .build();

}
