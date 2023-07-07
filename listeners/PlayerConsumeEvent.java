package me.mati.skyblock.listeners;

import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerConsumeEvent implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        ItemStack is = e.getItem();
        if(!is.getType().equals(Material.GOLDEN_APPLE)){
            return;
        }
        User u = UserManager.getUser(e.getPlayer());
        if(is.getDurability() == 1){
            u.addEatenKox(1);
            return;
        }
        u.addEatenRef(1);
    }

}
