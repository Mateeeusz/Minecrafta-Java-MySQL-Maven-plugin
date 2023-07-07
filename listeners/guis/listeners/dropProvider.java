package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class dropProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));

        ItemStack turbo = new ItemBuilder(Material.GOLD_PICKAXE, 1).setTitle(Utill.fixColor("&6&l   TurboDrop")).build();
        ItemStack wiadomosci = new ItemBuilder(Material.PAPER, 1).setTitle(Utill.fixColor("&f&l   Wiadomosci na Chacie")).build();
        ItemStack cb = new ItemBuilder(Material.COBBLESTONE, 1).setTitle(Utill.fixColor("&f&l   Wypadanie Cobblestone")).build();
        ItemStack kamien = new ItemBuilder(Material.STONE, 1).setTitle(Utill.fixColor("&f&l   Drop ze Stone")).build();
        ItemStack premiumcase = new ItemBuilder(Material.TRAPPED_CHEST, 1).setTitle(Utill.fixColor("&f&l   Drop z &6&lPremiumCase")).build();
        ItemStack cx = new ItemBuilder(Material.MOSSY_COBBLESTONE, 1).setTitle(Utill.fixColor("&f&l   Drop z cobblex")).build();

        contents.set(1,1, ClickableItem.of(premiumcase, e -> {

        }));

        contents.set(1,4, ClickableItem.of(kamien, e -> {

        }));

        contents.set(1,7, ClickableItem.of(cx, e -> {

        }));

        contents.set(3,3, ClickableItem.of(cb, e -> {

        }));

        contents.set(3,4, ClickableItem.of(turbo, e -> {

        }));

        contents.set(3,5, ClickableItem.of(wiadomosci, e -> {

        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
