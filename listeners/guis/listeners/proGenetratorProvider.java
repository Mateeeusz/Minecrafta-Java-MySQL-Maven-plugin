package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.Config;
import me.mati.skyblock.guisCommands.craftingsCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class proGenetratorProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        ItemStack biala = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        ItemStack stone = new ItemStack(Material.OBSIDIAN);
        ItemStack kamien = new ItemStack(Material.STONE);
        ItemStack piach = new ItemStack(Material.SAND);

        ItemStack ac = new ItemBuilder(Material.WORKBENCH).setTitle(Utill.fixColor("&f&l   Automatyczny Crafting!")).addLores(Arrays.asList("", Utill.fixColor("&7Kliknij aby stworzyc!"))).build();
        ItemStack back = new ItemBuilder(Material.BARRIER).setTitle(Utill.fixColor("&cPowrot!")).addLores(Arrays.asList("", Utill.fixColor("&7Kliknij aby wrocic do strony z craftingami!"))).build();

        //Crafting
        contents.set(1, 1, ClickableItem.empty(stone));
        contents.set(1, 2, ClickableItem.empty(stone));
        contents.set(1, 3, ClickableItem.empty(stone));
        contents.set(2, 1, ClickableItem.empty(kamien));
        contents.set(2, 2, ClickableItem.empty(kamien)); //srodek
        contents.set(2, 3, ClickableItem.empty(kamien));
        contents.set(3, 1, ClickableItem.empty(piach));
        contents.set(3, 2, ClickableItem.empty(piach));
        contents.set(3, 3, ClickableItem.empty(piach));

        //rezultat

        contents.set(1, 7, ClickableItem.empty(biala));
        contents.set(1, 6, ClickableItem.empty(biala));
        contents.set(1, 5, ClickableItem.empty(biala));
        contents.set(2, 7, ClickableItem.empty(biala));
        contents.set(2, 6, ClickableItem.empty(MainCraftingsProvider.bfm)); //efekt
        contents.set(2, 5, ClickableItem.empty(biala));
        contents.set(3, 7, ClickableItem.empty(biala));
        contents.set(3, 6, ClickableItem.empty(biala));
        contents.set(3, 5, ClickableItem.empty(biala));

        //autocrafting
        contents.set(5, 6, ClickableItem.of(ac, e ->{
            String it = Config.COST_BoyFarmer;
            List<ItemStack> items = ItemUtil.getItems(it, 1);
            player.closeInventory();
            if (!ItemUtil.checkAndRemove(items, player)) {
                Utill.sendMsg(player, "&4Blad: &cPotrzebujesz&8: &3Obsydian&8(&e3&8)&7, &3Piasek&8(&e3&8), &3Kamien&8(&e3&8)&7.");
                return;
            }
            ItemStack boy = MainCraftingsProvider.bfm;
            player.getInventory().addItem(boy);
            Utill.sendMsg(player, "&7Udalo ci sie stworzyc &3BoyFarmera&7!");

        }));

        contents.set(5, 2, ClickableItem.of(back, e ->{
            player.closeInventory();
            craftingsCommand.craftingsinv.open(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
