package me.mati.skyblock.guisCommands;

import fr.minuskube.inv.SmartInventory;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.listeners.guis.listeners.*;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class helpCommand extends PlayerCommand {


    public helpCommand() {
        super("help", "pomoc ", "/pomoc", "sky.help", "pomoc");
    }

    public static SmartInventory helpGui = SmartInventory.builder()
            .id("helpCommand")
            .provider(new HelpInventoryProvider())
            .size(5, 9)
            .title(Utill.fixColor("&8GLOWNA POMOC SERWERA"))
            .closeable(true)
            .build();

    public static SmartInventory linksGui = SmartInventory.builder()
            .id("linki")
            .provider(new LinksProvider())
            .size(3, 9)
            .title(Utill.fixColor("&8PRZYDATNE LINKI"))
            .closeable(true)
            .build();

    public static SmartInventory administracjaGui = SmartInventory.builder()
            .id("admins")
            .provider(new AdministracjaProvider())
            .size(1, 9)
            .title(Utill.fixColor("&8ADMINISTRACJA SERWERA"))
            .closeable(true)
            .build();

    public static SmartInventory comandsGui = SmartInventory.builder()
            .id("help")
            .provider(new ComandsProvider())
            .size(3, 9)
            .title(Utill.fixColor("&8GLOWNA POMOC SERWERA"))
            .closeable(true)
            .build();

    public static SmartInventory playerProfileGui = SmartInventory.builder()
            .id("profile")
            .provider(new PlayerProfileProvider())
            .size(6, 9)
            .title(Utill.fixColor("&8STATYSTYKI GRACZA"))
            .closeable(true)
            .build();

    @Override
    public boolean onCommand(Player p0, String[] p1) {
        helpGui.open(p0);
        return false;
    }
}
