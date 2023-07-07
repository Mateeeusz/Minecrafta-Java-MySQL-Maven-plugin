package me.mati.skyblock.listeners.guis.listeners.todo;

import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.guisCommands.kitCommand;
import me.mati.skyblock.utils.TimeUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class kitInventoryListener implements Listener {


    @EventHandler(priority = EventPriority.MONITOR)
    public void OnClickEventKity(InventoryClickEvent e) {
        User u = UserManager.getUser(e.getWhoClicked().getUniqueId());
        if (!Utill.fixColor("&6Kity!").equalsIgnoreCase(e.getInventory().getName())) {
            return;
        }
        e.setCancelled(true);
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if (item == null) {
            e.setCancelled(true);
            kitCommand.kit(p);
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            e.setCancelled(true);
            kitCommand.kit(p);
            return;
        }
        if (meta.getDisplayName() != null && meta.getDisplayName().equals(Utill.fixColor("&6Kit VIP"))) {
            if(p.hasPermission("sky.recivekitvip")){
                p.closeInventory();
                kitCommand.vip(p);
                u.setKitVIPTime(System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.KitVipTime));
            }else {
                p.closeInventory();
                Utill.sendMsg(p, "&4Blad! &cAby moc odebrac ten kit musisz zakupic range &6VIP!");
                Utill.sendMsg(p, "&cMozesz to zrobic na: www.erhc.pl");
            }
        } else if (meta.getDisplayName() != null && meta.getDisplayName().equals(Utill.fixColor("&3Kit sVIP"))) {
            if(p.hasPermission("sky.recivekitsvip")){
                p.closeInventory();
                kitCommand.svip(p);
                u.setKitSVIPTime(System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.KitSvipTime));
            } else {
                p.closeInventory();
                Utill.sendMsg(p, "&4Blad! &cAby moc odebrac ten kit musisz zakupic range &3sVIP!");
                Utill.sendMsg(p, "&cMozesz to zrobic na: www.erhc.pl");
            }
        } else if (meta.getDisplayName() != null && meta.getDisplayName().equals(Utill.fixColor("&bKit MVIP"))) {
            if(p.hasPermission("sky.recivekitmvip")){
                p.closeInventory();
                kitCommand.mvip(p);
                u.setKitMVIPTime(System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.KitMvipTime));
            } else {
                p.closeInventory();
                Utill.sendMsg(p, "&4Blad! &cAby moc odebrac ten kit musisz zakupic range &bVMIP!");
                Utill.sendMsg(p, "&cMozesz to zrobic na: www.erhc.pl");
            }
        } else if (meta.getDisplayName() != null && meta.getDisplayName().equals(Utill.fixColor("&2Kit Mieso"))) {
            p.closeInventory();
            kitCommand.mieso(p);
        }

    }
}
