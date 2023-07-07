package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.guisCommands.helpCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class AdministracjaProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {

        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        ItemStack mvti = new ItemBuilder(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()).setTitle(Utill.fixColor("&4&l[ROOT] &6&lMateeeusz")).build();
        SkullMeta mvtim = (SkullMeta) mvti.getItemMeta();
        mvtim.setOwner("Mateeeusz");
        mvti.setItemMeta(mvtim);
        ItemStack michal = new ItemBuilder(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()).setTitle(Utill.fixColor("&4&l[H@] &6&lZahus")).build();
        SkullMeta michalm = (SkullMeta) michal.getItemMeta();
        michalm.setOwner("Zahus");
        michal.setItemMeta(michalm);
        ItemStack powrot = new ItemBuilder(Material.BARRIER, 1).setTitle(Utill.fixColor("&cPowrot")).build();

        contents.set(0, 0, ClickableItem.empty(mvti));
        contents.set(0, 1, ClickableItem.empty(michal));
        contents.set(0, 8, ClickableItem.of(powrot, e -> {
            player.closeInventory();
            helpCommand.helpGui.open(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
