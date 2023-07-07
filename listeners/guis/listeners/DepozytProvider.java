package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class DepozytProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        User us = UserManager.getUser(player);
        ItemStack refy = new ItemBuilder(Material.GOLDEN_APPLE, 1).setTitle(Utill.fixColor("&6&lRefile")).addLores(Arrays.asList("", Utill.fixColor("&8 » &7Posiadasz&8:&e " + us.getRefy()), Utill.fixColor("&8 » &7Limit&8: &7" + Config.SCHOWEK_KREFYLIMIT), "", Utill.fixColor("&8» &aKliknij aby wyplacic!"))).build();
        ItemStack perly = new ItemBuilder(Material.ENDER_PEARL, 1).setTitle(Utill.fixColor("&6&lPerly")).addLores(Arrays.asList("", Utill.fixColor("&8 » &7Posiadasz&8:&e " + us.getPerly()), Utill.fixColor("&8 » &7Limit&8: &7" + Config.SCHOWEK_PERLYLIMIT), "", Utill.fixColor("&8» &aKliknij aby wyplacic!"))).build();
        ItemStack Koxy = new ItemBuilder(Material.GOLDEN_APPLE, 1, (short) 1).setTitle(Utill.fixColor("&6&lKoxy")).addLores(Arrays.asList("", Utill.fixColor("&8 » &7Posiadasz&8:&e " + us.getkoxy()), Utill.fixColor("&8 » &7Limit&8: &7" + Config.SCHOWEK_KOXYLIMIT), "", Utill.fixColor("&8» &aKliknij aby wyplacic!"))).build();
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));

        contents.fillColumn(1, ClickableItem.of(Koxy, e -> {
            Player p = (Player) e.getWhoClicked();
            User u = UserManager.getUser(player);
            int maxkox = Config.SCHOWEK_KOXYLIMIT;
            if(u == null){
                u = UserManager.getUser((Player) e.getWhoClicked());
            }
            if(u.getkoxy() <= 0){
                Utill.sendMsg(p, Utill.fixColor(Config.not_HAVE_KOX));
                return;
            }
            int kox = ItemUtil.getAmountOfItem(Material.GOLDEN_APPLE, p, (short) 1);
            if(kox >= maxkox){
                Utill.sendMsg(p ,Utill.fixColor(Config.you_HAVE_MAX_KOX));
                return;
            }
            int i = maxkox - kox;
            if(i > u.getkoxy()){
                i = u.getkoxy();
            }
            u.removeKoxy(i);
            ItemStack remove = new ItemStack(Material.GOLDEN_APPLE, i , (short) 1);
            ItemUtil.giveItem(p , Arrays.asList(new ItemStack[] {remove}), p.getLocation());
            Player player2 = p;
            Utill.sendMsg(p ,Utill.fixColor(Config.deposited_KOX.replace("{DEPO}", Integer.toString(i))));
            player2.updateInventory();
        }));
        contents.fillColumn(4 ,ClickableItem.of(perly, e ->{
            Player p = (Player) e.getWhoClicked();
            User u = UserManager.getUser(player);
            int maxkox = Config.SCHOWEK_PERLYLIMIT;
            if(u == null){
                u = UserManager.getUser((Player) e.getWhoClicked());
            }
            if(u.getPerly() <= 0){
                Utill.sendMsg(p, Utill.fixColor(Config.not_HAVE_PEARL));
                return;
            }
            int kox = ItemUtil.getAmountOfItem(Material.ENDER_PEARL, p ,(short) 0);
            if(kox >= maxkox){
                Utill.sendMsg(p ,Utill.fixColor(Config.you_HAVE_MAX_PEARL));
                return;
            }
            int i = maxkox - kox;
            if(i > u.getPerly()){
                i = u.getPerly();
            }
            u.removePerly(i);
            ItemStack remove = new ItemStack(Material.ENDER_PEARL, i);
            ItemUtil.giveItem(p , Arrays.asList(new ItemStack[] {remove}), p.getLocation());
            Player player2 = p;
            Utill.sendMsg(p ,Utill.fixColor(Config.deposited_PEARL.replace("{DEPO}", Integer.toString(i))));
            player2.updateInventory();
            return;
        }));

        contents.fillColumn(7 ,ClickableItem.of(refy, e ->{
            int maxkox = Config.SCHOWEK_KREFYLIMIT;
            Player p = (Player) e.getWhoClicked();
            User u = UserManager.getUser(player);
            if(u == null){
                u = UserManager.getUser((Player) e.getWhoClicked());
            }
            if(u.getRefy() <= 0){
                Utill.sendMsg(p, Utill.fixColor(Config.not_HAVE_REF));
                return;
            }
            int kox = ItemUtil.getAmountOfItem(Material.GOLDEN_APPLE, p, (short) 0);
            if(kox >= maxkox){
                Utill.sendMsg(p ,Utill.fixColor(Config.you_HAVE_MAX_REF));
                return;
            }
            int i = maxkox - kox;
            if(i > u.getRefy()){
                i = u.getRefy();
            }
            u.removeRefy(i);
            ItemStack remove = new ItemStack(Material.GOLDEN_APPLE, i);
            ItemUtil.giveItem(p , Arrays.asList(new ItemStack[] {remove}), p.getLocation());
            Player player2 = p;
            Utill.sendMsg(p ,Utill.fixColor(Config.deposited_REF.replace("{DEPO}", Integer.toString(i))));
            player2.updateInventory();
        }));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
