package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class healCommand extends PlayerCommand {
    public healCommand() {
        super("heal", "leczy i uzupelnia glod", "/heal <nick>", "sky.heal", "ulecz, feed");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
            if (args.length == 0) {
                p.setHealth(p.getMaxHealth());
                p.setFoodLevel(20);
                p.setFireTicks(0);
                for (PotionEffect effect : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect.getType());
                }
                Utill.sendMsg(p, "&7Uleczono!");
            } else if (args.length > 1) {
                p.sendMessage(Utill.fixColor("&cBlad poprawne uzycie: &7" + this.getUsage()));
            } else if (args.length == 1) {
                Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    p.sendMessage("&cBlad gracz &7" + o.getDisplayName() + " jest Offline");
                } else {
                    o.setHealth(o.getMaxHealth());
                    o.setFoodLevel(20);
                    o.setFireTicks(0);
                    for (PotionEffect effect2 : o.getActivePotionEffects()) {
                        o.removePotionEffect(effect2.getType());
                    }
                    Utill.sendMsg(p, "&7Uleczyles gracza &3" + o.getDisplayName());
                    o.sendMessage(Utill.fixColor("&7Zostales uleczony przez gracza &3" + p.getDisplayName()));
                }
        }
        return false;
    }
}
