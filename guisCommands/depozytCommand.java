package me.mati.skyblock.guisCommands;

import fr.minuskube.inv.SmartInventory;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.DepozytProvider;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class depozytCommand extends PlayerCommand {

    public depozytCommand() {
        super("depozyt", "depozyt koxow", "/depozyt", "sky.depozyt", "schowek");
    }

    public static SmartInventory schowek = SmartInventory.builder()
            .id("schoweInventory")
            .provider(new DepozytProvider())
            .size(1, 9)
            .title(Utill.fixColor("&8SCHOWEK"))
            .closeable(true)
            .build();

    @Override
    public boolean onCommand(Player p0, String[] p1) {
        schowek.open(p0);
        return false;
    }
}
