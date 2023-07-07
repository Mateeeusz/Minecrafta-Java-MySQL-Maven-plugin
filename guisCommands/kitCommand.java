package me.mati.skyblock.guisCommands;

import me.mati.skyblock.Commands.admin.klCommand;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class kitCommand extends PlayerCommand{
    public kitCommand() {
        super("kit", "odbieranie kitow", "/kit", "mvti.kitgui", "");
    }

    public static void kit(Player p){
        Inventory kitstart = Bukkit.createInventory(p, 27, Utill.fixColor("&6Kity!"));
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8___")).addLores(Arrays.asList("", Utill.fixColor("&7To jest puste pole wybierz inne"))).build();
        ItemStack vip = new ItemBuilder(Material.IRON_HELMET, 1).setTitle(Utill.fixColor("&6Kit VIP")).build();
        ItemStack svip = new ItemBuilder(Material.GOLD_HELMET, 1).setTitle(Utill.fixColor("&3Kit sVIP")).build();
        ItemStack mvip = new ItemBuilder(Material.DIAMOND_HELMET, 1).setTitle(Utill.fixColor("&bKit MVIP")).build();
        ItemStack miesko = new ItemBuilder(Material.COOKED_BEEF, 1).setTitle(Utill.fixColor("&2Kit Mieso")).addLores(Arrays.asList("", Utill.fixColor("&2Kliknij aby odbierac"))).build();
        ItemMeta vipKit = vip.getItemMeta();
        List<String> viplore = new ArrayList<>();
        ItemMeta svipKit = svip.getItemMeta();
        List<String> sviplore = new ArrayList<>();
        ItemMeta mvipKit = mvip.getItemMeta();
        List<String> mviplore = new ArrayList<>();

        User u = UserManager.getUser(p);
        if(!p.hasPermission("sky.kit.bypass") && u.getKitVIPTime() > System.currentTimeMillis()){
            viplore.add(Utill.fixColor(""));
            viplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            viplore.add(Utill.fixColor("&4Blad: &cKit vip mozesz wziac dopiero za " + Utill.secondsToString((int)(u.getKitVIPTime() - System.currentTimeMillis()) / 1000) + "!"));
        } else {
            viplore.add(Utill.fixColor(""));
            viplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            viplore.add(Utill.fixColor("&2Dostepny: ✔"));
        }
        if(!p.hasPermission("sky.kit.bypass") && u.getKitSVIPTime() > System.currentTimeMillis()){
            sviplore.add(Utill.fixColor(""));
            sviplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            sviplore.add(Utill.fixColor("&cDostepny za: " + Utill.secondsToString((int)(u.getKitSVIPTime() - System.currentTimeMillis()) / 1000) + "!"));
        } else {
            sviplore.add(Utill.fixColor(""));
            sviplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            sviplore.add(Utill.fixColor("&2Dostepny: ✔"));
        }
        if(!p.hasPermission("sky.kit.bypass") && u.getKitMVIPTime() > System.currentTimeMillis()){
            mviplore.add(Utill.fixColor(""));
            mviplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            mviplore.add(Utill.fixColor("&cDostepny za: " + Utill.secondsToString((int)(u.getKitMVIPTime() - System.currentTimeMillis()) / 1000) + "!"));
        } else {
            mviplore.add(Utill.fixColor(""));
            mviplore.add(Utill.fixColor("&2Kliknij aby odebrac!"));
            mviplore.add(Utill.fixColor("&2Dostepny: ✔"));
        }
        vipKit.setLore(viplore);
        vip.setItemMeta(vipKit);
        svipKit.setLore(sviplore);
        svip.setItemMeta(svipKit);
        mvipKit.setLore(mviplore);
        mvip.setItemMeta(mvipKit);

        kitstart.setItem(0, szara);
        kitstart.setItem(1, szara);
        kitstart.setItem(2, szara);
        kitstart.setItem(3, szara);
        kitstart.setItem(4, szara);
        kitstart.setItem(5, szara);
        kitstart.setItem(6, szara);
        kitstart.setItem(7, szara);
        kitstart.setItem(8, szara);
        kitstart.setItem(9, szara);
        kitstart.setItem(10, vip);
        kitstart.setItem(11, szara);
        kitstart.setItem(12, svip);
        kitstart.setItem(13, szara);
        kitstart.setItem(14, mvip);
        kitstart.setItem(15, szara);
        kitstart.setItem(16, miesko);
        kitstart.setItem(17, szara);
        kitstart.setItem(18, szara);
        kitstart.setItem(19, szara);
        kitstart.setItem(20, szara);
        kitstart.setItem(21, szara);
        kitstart.setItem(22, szara);
        kitstart.setItem(23, szara);
        kitstart.setItem(24, szara);
        kitstart.setItem(25, szara);
        kitstart.setItem(26, szara);
        if(!klCommand.isKit()){
            Utill.sendMsg(p, "&4Blad: &cKit VIP jest aktualnie wylaczony!");
        } else {
            p.openInventory(kitstart);
        }
    }


    public static void vip(Player p){
        Inventory inv1 = Bukkit.createInventory(p, 27, Utill.fixColor("&6KIT VIP!"));

        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8___")).addLores(Arrays.asList("", Utill.fixColor("&7To jest puste pole wybierz inne"))).build();
        inv1.setItem(0, szara);
        inv1.setItem(1, szara);
        inv1.setItem(2, szara);
        inv1.setItem(3, szara);
        inv1.setItem(4, szara);
        inv1.setItem(5, szara);
        inv1.setItem(6, szara);
        inv1.setItem(7, szara);
        inv1.setItem(8, szara);
        inv1.setItem(9, szara);
        inv1.setItem(10, szara);
        inv1.setItem(11, szara);
        inv1.setItem(12, szara);
        inv1.setItem(13, szara);
        inv1.setItem(14, szara);
        inv1.setItem(15, szara);
        inv1.setItem(16, szara);
        inv1.setItem(17, szara);
        inv1.setItem(18, szara);
        inv1.setItem(19, szara);
        inv1.setItem(20, szara);
        inv1.setItem(21, szara);
        inv1.setItem(22, szara);
        inv1.setItem(23, szara);
        inv1.setItem(24, szara);
        inv1.setItem(25, szara);
        inv1.setItem(26, szara);
        p.openInventory(inv1);

    }
    public static void svip(Player p){
        Inventory inv1 = Bukkit.createInventory(p, 27, Utill.fixColor("&6KIT SVIP!"));
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8___")).addLores(Arrays.asList("", Utill.fixColor("&7To jest puste pole wybierz inne"))).build();
        inv1.setItem(0, szara);
        inv1.setItem(1, szara);
        inv1.setItem(2, szara);
        inv1.setItem(3, szara);
        inv1.setItem(4, szara);
        inv1.setItem(5, szara);
        inv1.setItem(6, szara);
        inv1.setItem(7, szara);
        inv1.setItem(8, szara);
        inv1.setItem(9, szara);
        inv1.setItem(10, szara);
        inv1.setItem(11, szara);
        inv1.setItem(12, szara);
        inv1.setItem(13, szara);
        inv1.setItem(14, szara);
        inv1.setItem(15, szara);
        inv1.setItem(16, szara);
        inv1.setItem(17, szara);
        inv1.setItem(18, szara);
        inv1.setItem(19, szara);
        inv1.setItem(20, szara);
        inv1.setItem(21, szara);
        inv1.setItem(22, szara);
        inv1.setItem(23, szara);
        inv1.setItem(24, szara);
        inv1.setItem(25, szara);
        inv1.setItem(26, szara);
        p.openInventory(inv1);

    }
    public static void mvip(Player p){
        Inventory inv1 = Bukkit.createInventory(p, 27, Utill.fixColor("&6KIT MVIP!"));
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setTitle(Utill.fixColor("&8___")).addLores(Arrays.asList("", Utill.fixColor("&7To jest puste pole wybierz inne"))).build();
        inv1.setItem(0, szara);
        inv1.setItem(1, szara);
        inv1.setItem(2, szara);
        inv1.setItem(3, szara);
        inv1.setItem(4, szara);
        inv1.setItem(5, szara);
        inv1.setItem(6, szara);
        inv1.setItem(7, szara);
        inv1.setItem(8, szara);
        inv1.setItem(9, szara);
        inv1.setItem(10, szara);
        inv1.setItem(11, szara);
        inv1.setItem(12, szara);
        inv1.setItem(13, szara);
        inv1.setItem(14, szara);
        inv1.setItem(15, szara);
        inv1.setItem(16, szara);
        inv1.setItem(17, szara);
        inv1.setItem(18, szara);
        inv1.setItem(19, szara);
        inv1.setItem(20, szara);
        inv1.setItem(21, szara);
        inv1.setItem(22, szara);
        inv1.setItem(23, szara);
        inv1.setItem(24, szara);
        inv1.setItem(25, szara);
        inv1.setItem(26, szara);
        p.openInventory(inv1);

    }
    public static void mieso(Player p){
        Inventory inv1 = Bukkit.createInventory(p, 9, Utill.fixColor("&6KIT MIESO!"));
        ItemStack mieso = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack szara = new ItemStack(Material.AIR, 1);
        inv1.setItem(0, szara);
        inv1.setItem(1, szara);
        inv1.setItem(2, szara);
        inv1.setItem(3, mieso);
        inv1.setItem(4, mieso);
        inv1.setItem(5, mieso);
        inv1.setItem(6, szara);
        inv1.setItem(7, szara);
        inv1.setItem(8, szara);
        p.openInventory(inv1);

    }



    @Override
    public boolean onCommand(Player p, String[] args) {
        kit(p);
        return false;
    }
}
