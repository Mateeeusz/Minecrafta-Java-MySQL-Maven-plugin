package me.mati.skyblock.guisCommands;

import fr.minuskube.inv.SmartInventory;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.*;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;

public class craftingsCommand extends PlayerCommand {
    public craftingsCommand() {
        super("craftingi", "lista craftingow", "/craftingi", "stephc.craftingi", "craftings");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        craftingsinv.open(p);
        return false;
    }

    public static SmartInventory craftingsinv = SmartInventory.builder()
            .id("craftingsinv")
            .provider(new MainCraftingsProvider())
            .size(3, 9)
            .title(Utill.fixColor("&8CRAFTINGI"))
            .closeable(true)
            .build();

    public static SmartInventory stoniarka1 = SmartInventory.builder()
            .id("stoniarka1")
            .provider(new stoniarka1Provider())
            .size(6, 9)
            .title(Utill.fixColor("&8Stoniarka"))
            .closeable(true)
            .build();

    public static SmartInventory stoniarka2 = SmartInventory.builder()
            .id("stoniarka2")
            .provider(new stoniarka2Provider())
            .size(6, 9)
            .title(Utill.fixColor("&8Turbo generator"))
            .closeable(true)
            .build();

    public static SmartInventory rzucak = SmartInventory.builder()
            .id("rzucak")
            .provider(new rzucakProvider())
            .size(6, 9)
            .title(Utill.fixColor("&8Rzucane TNT"))
            .closeable(true)
            .build();

    public static SmartInventory ender = SmartInventory.builder()
            .id("ender")
            .provider(new enderProvider())
            .size(6, 9)
            .title(Utill.fixColor("&8Enderchest"))
            .closeable(true)
            .build();

    public static SmartInventory boy = SmartInventory.builder()
            .id("boy")
            .provider(new proGenetratorProvider())
            .size(6, 9)
            .title(Utill.fixColor("&8BoyFarmer"))
            .closeable(true)
            .build();
}
