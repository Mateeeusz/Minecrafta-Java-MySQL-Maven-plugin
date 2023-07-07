package me.mati.skyblock.guisCommands;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.listeners.guis.listeners.MainCraftingsProvider;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class adminCommand extends PlayerCommand {
    public adminCommand() {
        super("admin", "itemy dla admina", "/admin", "sky.itemy.admin", "itemy");
    }
            public static void adminItems(Player p){
                Inventory admin = Bukkit.createInventory(p, 18 , Utill.fixColor("&3Itemy dla admina"));
                ItemStack stown = MainCraftingsProvider.stoniarka;
                ItemStack bf = MainCraftingsProvider.bfm;
                ItemStack s2 = MainCraftingsProvider.stoniarka2;
                ItemStack rtnt = MainCraftingsProvider.rtnt;
                ItemStack cx = MainCraftingsProvider.cx;
                ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8___")).addLores(Arrays.asList("", Utill.fixColor("&7To jest puste pole wybierz inne"))).build();
                ItemBuilder pc = new ItemBuilder(Material.TRAPPED_CHEST).setTitle(Utill.fixColor("&8>> &c&lPremiumCase")).addLores(Arrays.asList("&8>> &7Poloz na ziemi aby otworzyc.", "&8>> &7Kup wiecej na: &6STEPHC.PL"));
                ItemBuilder patyk = new ItemBuilder(Material.BLAZE_ROD).setTitle(Utill.fixColor("&2&lMagiczna Rozdzka")).addEnchantment(Enchantment.KNOCKBACK, 10);
                ItemStack compas = new ItemStack(Material.COMPASS);
                admin.setItem(0, stown);
                admin.setItem(1, patyk.build());
                admin.setItem(2, compas);
                admin.setItem(3, bf);
                admin.setItem(4, s2);
                admin.setItem(5, rtnt);
                admin.setItem(6, szara);
                admin.setItem(7, cx);
                admin.setItem(8, szara);
                admin.setItem(9, pc.build());
                p.openInventory(admin);
            }
    @Override
    public boolean onCommand(Player p, String[] p1) {
        adminCommand.adminItems(p);
        return false;
    }
}
