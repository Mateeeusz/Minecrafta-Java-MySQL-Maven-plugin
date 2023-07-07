package me.mati.skyblock.listeners;

import me.mati.skyblock.utils.Utill;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;


public class CommandCooldown implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();


    @EventHandler
    public void Commandcooldown(PlayerCommandPreprocessEvent e) {
        int cooldownTime = 2;
        if(cooldowns.containsKey(e.getPlayer().getName())) {
            long secondsLeft = ((cooldowns.get(e.getPlayer().getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft>0) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Utill.fixColor("&cNie spam komendami!!!"));
            }
        }
        cooldowns.put(e.getPlayer().getName(), System.currentTimeMillis());
    }

}
