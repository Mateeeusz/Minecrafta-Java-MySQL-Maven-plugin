package me.mati.skyblock.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import me.mati.skyblock.Commands.admin.vanishCommand;
import me.mati.skyblock.Managers.MainManagers.CombatManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class OnJoin implements Listener {
    private static final ItemStack kilof = new ItemBuilder(Material.STONE_PICKAXE).setTitle(Utill.fixColor("&6&oStartowy Kilof")).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1).addEnchantment(Enchantment.DIG_SPEED, 3).build();
    private static final ItemStack enderchest = new ItemBuilder(Material.ENDER_CHEST, 1).build();
    private static final ItemStack zarcie = new ItemBuilder(Material.COOKED_BEEF, 64).build();

    public static void GiveFirstItems(Player p){
//        private static final ItemStack stoniarki = new ItemBuilder(Material.ENDER_STONE, 5).setTitle(Utill.fixColor("&cGenerator kamienia")).addEnchantment(Enchantment.SILK_TOUCH, 1).addLores(Arrays.asList("", Util.fixColor("&eGeneruje kamien"), Core.cprefix + Utill.fixColor("&eAby zaczal dzialac nalezy go postawic"))).build();
        p.getInventory().addItem(enderchest);
        p.getInventory().addItem(kilof);
        p.getInventory().addItem(zarcie);
        p.getInventory().addItem(zarcie);
        p.updateInventory();
    }





    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();
        player.setHealth(20.0);
        player.setFoodLevel(20);
        TitleAPI.sendFullTitle(player, 25, 25, 25, Utill.fixColor("&6Czesc"), Utill.fixColor("&6witaj na krok hardkor kropka polska!"));
        CombatManager.createPlayer(e.getPlayer());
        User u = UserManager.getUser(player);
        if (u == null) {
            UserManager.createUser(player);
        }
        UserManager.joinToGame(player);
        if (player.hasPlayedBefore()) {
            return;
        }
        GiveFirstItems(player);
        RandomTp.RandomTP(player);
        for(Player p : vanishCommand.vanished){
            if(!p.hasPermission("stephc.vanish.seeothers")){
                player.hidePlayer(p);
            }
        }

    }

}