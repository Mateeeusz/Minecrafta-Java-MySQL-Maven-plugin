package me.mati.skyblock.listeners;

import me.mati.skyblock.Managers.MainManagers.CombatManager;
import me.mati.skyblock.utils.Utill;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class EntityDamageByEntityListener implements Listener {

    private static HashMap<UUID, UUID> lastDamager = new HashMap<>();




    public static HashMap<UUID, UUID> getLastDamager() {
        return EntityDamageByEntityListener.lastDamager;
    }

    public static void clearDamagers(UUID damager) {
        List<UUID> toRemove = EntityDamageByEntityListener.lastDamager.entrySet().stream().filter(e -> e.getValue().equals(damager)).map(Map.Entry::getKey).collect(Collectors.toList());
        toRemove.forEach(EntityDamageByEntityListener.lastDamager::remove);
    }

    @EventHandler
    public void EntityByEntityDmagae(EntityDamageByEntityEvent e){
        Player p = (Player) e.getEntity();
        Player d = Utill.getDamager(e);
        if(p.hasPermission("stephc.nodmg")){
            e.setCancelled(true);
            Utill.sendMsg(d, "&4Blad: &ctego gracza nie mozesz udezyc");
        }
        if (e.getDamage() <= 0.0) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (Utill.getDamager(e) == null) {
            return;
        }
        if (p.equals(d)) {
            return;
        }
        clearDamagers(d.getUniqueId());
        if(!p.hasPermission("stephc.nodmg") || !d.hasPermission("stephc.nodmg")){
            EntityDamageByEntityListener.lastDamager.put(p.getUniqueId(), d.getUniqueId());
            if (!CombatManager.isInFight(p)) {

                Utill.sendMsg(p, "&cJestes podczas walki! Nie mozesz sie wylogowac");
//            String message = Utill.fixColor("Jestes w walce");
//            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            }
            if (!CombatManager.isInFight(d)) {
                Utill.sendMsg(d, "&cJestes podczas walki! Nie mozesz sie wylogowac");
            }
            CombatManager.setLastFight(p);
            CombatManager.setLastFight(d);
        }
    }

    @EventHandler
    public void EnityDamageEvent(EntityDamageEvent e){
        if(e.getEntity().hasPermission("stephc.nodmg")){
            e.setCancelled(true);
        }
    }


}
