package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.guisCommands.craftingsCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCraftingsProvider implements InventoryProvider {

    public static ItemStack stoniarka = new ItemStack(Material.ENDER_STONE);

    static {
        ItemMeta stoniarkameta = stoniarka.getItemMeta();
        stoniarkameta.setDisplayName(Utill.fixColor("&3&lGenerator kamienia"));
        stoniarkameta.addEnchant(Enchantment.SILK_TOUCH, 10, false);
        List<String> lore = new ArrayList<>();

        lore.add(Utill.fixColor("&7Generuje kamien po 1,5 sekundy."));
        lore.add(Utill.fixColor("&7Aby zaczal dzialac nalezy go postawic!"));
        stoniarkameta.setLore(lore);
        stoniarka.setItemMeta(stoniarkameta);
    }

    public static void giveStoniarka(Player p, int amount) {
        ItemStack toGive = stoniarka;
        toGive.setAmount(amount);
        p.getInventory().addItem(toGive);
    }

    public static ItemStack bfm = new ItemStack(Material.ENDER_PORTAL_FRAME);
    public static ItemStack rtnt = new ItemStack(Material.TNT);
    public static ItemStack cx = new ItemStack(Material.MOSSY_COBBLESTONE);
    public static ItemStack stoniarka2 = new ItemStack(Material.NETHERRACK);
    static {
        ItemMeta bfmeta = bfm.getItemMeta();
        bfmeta.setDisplayName(Utill.fixColor("&3&lBoyFarmer"));
        bfmeta.addEnchant(Enchantment.DURABILITY, 1, false);
        List<String> lore = new ArrayList<>();

        lore.add(Utill.fixColor("&7Po postawieniu generuje obsydian/piasek/kopie w dół"));
        lore.add(Utill.fixColor("&7Aby zaczal dzialac nalezy go postawic!"));
        bfmeta.setLore(lore);
        bfm.setItemMeta(bfmeta);
    }
    static {
        ItemMeta stoniarkameta2 = stoniarka2.getItemMeta();
        stoniarkameta2.setDisplayName(Utill.fixColor("&3&lTurbo Generator"));
        stoniarkameta2.addEnchant(Enchantment.SILK_TOUCH, 10, false);
        List<String> lore = new ArrayList<>();

        lore.add(Utill.fixColor("&7Generuje kamien po 1 sekundzie."));
        lore.add(Utill.fixColor("&7Aby zaczal dzialac nalezy go postawic!"));
        stoniarkameta2.setLore(lore);
        stoniarka2.setItemMeta(stoniarkameta2);
    }
    static {
        ItemMeta cxmeta = cx.getItemMeta();
        cxmeta.setDisplayName(Utill.fixColor("&3&lCobblex"));
        cxmeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false);
        List<String> lore = new ArrayList<>();

        lore.add(Utill.fixColor("&7Postaw i zobacz czy masz szczescie!"));
        cxmeta.setLore(lore);
        cx.setItemMeta(cxmeta);
    }
    static {
        ItemMeta rtntmeta = rtnt.getItemMeta();
        rtntmeta.setDisplayName(Utill.fixColor("&4&lRzucane TNT"));
        rtntmeta.addEnchant(Enchantment.FIRE_ASPECT, 10, false);
        List<String> lore = new ArrayList<>();

        lore.add(Utill.fixColor("&7Nacisnij PPM aby uzyc!"));
        rtntmeta.setLore(lore);
        rtnt.setItemMeta(rtntmeta);
    }
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemBuilder show1 = new ItemBuilder(Material.ENDER_STONE).setTitle(Utill.fixColor("&3&lGenerator kamienia")).addEnchantment(Enchantment.SILK_TOUCH, 10).addLores(Arrays.asList("", Utill.fixColor("&7Generuje kamien po 1,5 sekundy"), Utill.fixColor("&7Aby zaczal dzialac nalezy go postawic")));
        ItemBuilder show2 = new ItemBuilder(Material.ENDER_CHEST).setTitle(Utill.fixColor("&3&lEnderChest")).addLores(Arrays.asList("", Utill.fixColor("&7Kliknij aby zobaczyc crafting.")));
        ItemBuilder kartka = new ItemBuilder(Material.PAPER).setTitle(Utill.fixColor("&6&lBoyFarmer"));
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        contents.set(1, 1, ClickableItem.of(show1.build(), e ->{
            player.closeInventory();
            craftingsCommand.stoniarka1.open(player);
        }));

        contents.set(1, 4, ClickableItem.of(show2.build(), e ->{
            player.closeInventory();
            craftingsCommand.ender.open(player);
        }));

        contents.set(1, 3, ClickableItem.of(rtnt, e ->{
            player.closeInventory();
            craftingsCommand.rzucak.open(player);
        }));


        contents.set(1, 2, ClickableItem.of(stoniarka2, e ->{
            player.closeInventory();
            craftingsCommand.stoniarka2.open(player);
        }));

        contents.set(1, 5, ClickableItem.of(bfm, e ->{
            player.closeInventory();
            craftingsCommand.boy.open(player);
        }));

        contents.set(1, 7, ClickableItem.empty(kartka.build()));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
