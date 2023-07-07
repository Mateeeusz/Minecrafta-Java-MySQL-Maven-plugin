package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.guisCommands.helpCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ComandsProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        ItemStack komendy = new ItemBuilder(Material.PAPER, 1).setTitle(Utill.fixColor("&6&lPrzydatne Komendy!")).addLores(Arrays.asList("Tu beda komendy jak cos")).build();
        ItemStack tnt = new ItemBuilder(Material.TNT, 1).setTitle(Utill.fixColor("&6&lInformacje o TNT")).addLores(Arrays.asList("Tu bedzie o tnt jak cos")).build();
        ItemStack powrot = new ItemBuilder(Material.BARRIER, 1).setTitle(Utill.fixColor("&cPowrot")).build();
        ItemStack kilof = new ItemBuilder(Material.DIAMOND_PICKAXE, 1).setTitle("&6&lRozgrywka").addLores(Arrays.asList("Tu bedzie rozgrywka jak cos")).build();

        contents.fill(ClickableItem.empty(szara));

        contents.set(2, 9, ClickableItem.of(powrot, e ->{
            player.closeInventory();
            helpCommand.helpGui.open(player);
        }));

        contents.set(1, 2, ClickableItem.empty(komendy));
        contents.set(1, 4, ClickableItem.empty(kilof));
        contents.set(1, 6, ClickableItem.empty(tnt));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
