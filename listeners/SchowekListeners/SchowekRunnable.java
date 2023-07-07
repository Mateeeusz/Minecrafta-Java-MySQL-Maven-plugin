package me.mati.skyblock.listeners.SchowekListeners;

import me.mati.skyblock.Config;
import me.mati.skyblock.Main;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class SchowekRunnable extends BukkitRunnable {


    @Override
    public void run() {
        Iterator<Player> iterator2;
        Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
        while (iterator.hasNext()) {
            Player player = iterator2.next();
            int kox = ItemUtil.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 1);
            int ref = ItemUtil.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 0);
            int perla = ItemUtil.getAmountOfItem(Material.ENDER_PEARL, player, (short) 0);
            int maxkox = Config.SCHOWEK_KOXYLIMIT;
            int maxref = Config.SCHOWEK_KREFYLIMIT;
            int maxperla = Config.SCHOWEK_PERLYLIMIT;
            User user = null;
            if (kox > maxkox) {
                int toRemove = kox - maxkox;
                ItemUtil.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove, (short) 1), player, toRemove);
                if (user == null) {
                    user = UserManager.getUser(player);
                }
                user.addKoxy(toRemove);
                Utill.sendMsg(player, Utill.fixColor(Config.limit_KOX.replace("{MAX}", Integer.toString(maxkox))
                        .replace("{MOVE-SIZE}", Integer.toString(toRemove))));
            }
            if (ref > maxref) {
                int toRemove = ref - maxref;
                ItemUtil.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove), player, toRemove);
                if (user == null) {
                    user = UserManager.getUser(player);
                }
                user.addRefy(toRemove);
                Utill.sendMsg(player, Utill.fixColor(Config.limit_REFIL.replace("{MAX}", Integer.toString(maxkox))
                        .replace("{MOVE-SIZE}", Integer.toString(toRemove))));
            }
            if (perla > maxperla) {
                int toRemove = perla - maxperla;
                ItemUtil.remove(new ItemStack(Material.ENDER_PEARL, toRemove), player, toRemove);
                if (user == null) {
                    user = UserManager.getUser(player);
                }
                user.addPerly(toRemove);
                Utill.sendMsg(player, Utill.fixColor(Config.limit_PERLA.replace("{MAX}", Integer.toString(maxkox))
                        .replace("{MOVE-SIZE}", Integer.toString(toRemove))));
            }
            iterator = iterator2;
        }
    }

}
