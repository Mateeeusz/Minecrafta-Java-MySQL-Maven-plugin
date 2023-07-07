package me.mati.skyblock.listeners;

import me.mati.skyblock.utils.ItemUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

//    @EventHandler
//    public void onPlayerdDeathEvent(PlayerDeathEvent e) {
//        Player p = (Player) e.getEntity();
//        switch (p.getLastDamageCause().getCause()) {
//            case LAVA: {
//                e.setDeathMessage(Utill.fixColor("&6Gracz &c" + p.getName() + " &6probowal plywac w lawie!"));
//                break;
//            }
//            case DROWNING: {
//                e.setDeathMessage(Utill.fixColor("&6Gracz &c" + p.getName() + " &6utopil sie!"));
//                break;
//            }
//            case SUICIDE: {
//                e.setDeathMessage(Utill.fixColor("&6Gracz &c" + p.getName() + "&6 postanowil popelnic samobojstwo!"));
//                break;
//            }
//            case FALL: {
//                e.setDeathMessage(Utill.fixColor("&6Gracz &c" + p.getName() + " &6 nie potrafi latac!"));
//                break;
//            }
//            default: {
//                e.setDeathMessage(Utill.fixColor("&6Gracz &c" + p.getName() + "&6 zostal zabity przez potwora!"));
//                break;
//            }
//        }
//    }
    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Player k = p.getKiller();
        p.getLocation().getWorld().dropItemNaturally(p.getLocation(), ItemUtil.getPlayerHead(p.getDisplayName()));

    }
}
