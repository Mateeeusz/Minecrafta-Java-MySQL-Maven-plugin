package me.mati.skyblock.listeners;

import me.mati.skyblock.Main;
import me.mati.skyblock.Managers.DropManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.Managers.PremiumCaseDropFile;
import me.mati.skyblock.base.User;
import me.mati.skyblock.data.Drop;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class BlockPlaceListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlockPlaced().getType().equals(Material.ENDER_STONE)){
            e.getPlayer().sendMessage(Utill.fixColor("&2Postawiles generator!"));
        } else {
            return;
        }
        if (!e.getBlockPlaced().getType().equals(Material.ENDER_STONE)) {
            return;
        }
        e.getBlockPlaced().getLocation().add(0.0, 1.0, 0.0).getBlock().setType(Material.STONE);

        Player p = e.getPlayer();
        Calendar c = Calendar.getInstance();
        int hr = c.get(11);
        if (!e.getBlockPlaced().getType().equals(Material.TNT)) {
            return;
        }
        if ((hr <= 23 || hr > 10)) {
            return;
        }
        e.setCancelled(true);
        Utill.sendMsg(p, "&4Blad: &cTnT jest wylaczone pomiedzy &723:00 &ca &710:00&c!");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace2(BlockPlaceEvent e) {
        if (!e.getBlockPlaced().getType().equals(Material.NETHERRACK)) {
            return;
        }
        e.getBlockPlaced().getLocation().add(0.0, 1.0, 0.0).getBlock().setType(Material.STONE);

    }

    @EventHandler
    public void onPremiumCasePlace(BlockPlaceEvent e){
        if (e.isCancelled()) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack item = e.getPlayer().getItemInHand();
        if (item.getType() != Material.TRAPPED_CHEST) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("itemstack-premiumcase.name")))) {
            return;
        }
        e.setCancelled(true);
        ItemStack isp = p.getItemInHand().clone();
        isp.setAmount(1);
        p.getInventory().removeItem(new ItemStack[] { isp });
        User u = UserManager.getUser(p);
        u.addSkrzynki(1);
        List<String> message = new ArrayList<>();
//        for (int i = 0; i < DropManager.getPremiumCaseDrops().size(); i++) {
//            PremiumCaseDropFile drop = DropManager.getPremiumCaseDrops().get(i);
//            if (Math.random() * 100.0 <= drop.getChance()) {
//                message.add(drop.getMsg());
//                for ( ItemStack is : drop.getItems()) {
//                    p.getWorld().dropItem(e.getBlock().getLocation(), is);
//                }
//            }
//        }
        for ( PremiumCaseDropFile drop : DropManager.getPremiumCaseDrops()) {
            if (Math.random() * 100.0 <= drop.getChance()) {
                message.add(drop.getMsg());
                for ( ItemStack is : drop.getItems()) {
                    p.getWorld().dropItem(e.getBlock().getLocation(), is);
                }
            }
        }
        if (Main.getPlugin(Main.class).getConfig().getBoolean("messages-enable.open-broadcast")) {
            Main.getPlugin(Main.class).getServer().broadcastMessage(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("msg.startmsg").replace("{PLAYER}", p.getName())));
            for ( String msg : message) {
                Main.getPlugin(Main.class).getServer().broadcastMessage(Utill.fixColor(msg));
            }
            Main.getPlugin(Main.class).getServer().broadcastMessage(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("msg.endmsg")));
        }
    }
}

