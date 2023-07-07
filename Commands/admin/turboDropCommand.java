package me.mati.skyblock.Commands.admin;


import me.mati.skyblock.Managers.MainManagers.TurboDropManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.base.Turbo;
import me.mati.skyblock.utils.Utill;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class turboDropCommand extends Command {

    public static boolean TurboDrop = false;
    private static String TurboDropMessage;

    public turboDropCommand() {
        super("turbodrop", "Ustawianie turbodropu czasowego", "/turbodrop <set//all/off/remove> <gracz/tag[wiadomosc]> [czas]", "sky.turbodrop");
    }

    public static String getMessage() {
        return TurboDropMessage;
    }

    public void setMessage( String Message) {
        TurboDropMessage = Message;
    }



    public boolean onExecute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("off")) {
                if (!isTurbo()) {
                    return Utill.sendMsg(sender, "&4Blad: &cTurboDrop nie jest wlaczony");
                }
                this.setTurbo(false);
//                Bukkit.getOnlinePlayers().forEach(BarAPI::removeBar);
//                return Utill.sendMsg(Bukkit.getOnlinePlayers(),  "&3Event z TurboDropem zostal zakonczony!");
            }
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("remove")) {
                Turbo turbodrop = TurboDropManager.getByNick(args[1]);
                if (turbodrop == null) {
                    return Utill.sendMsg(sender,  "&4Blad: &cGracz o podanym nicku nie posiada turbodropa!");
                }
                TurboDropManager.deleteTurboDrop(turbodrop);
                return Utill.sendMsg(sender, "&7Usunieto turbodropa gracza &3" + turbodrop.getNick() + "&7!");
            }
            return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("all")) {
                if (isTurbo()) {
                    return Utill.sendMsg(sender, "&4Blad: &cTurboDrop jest juz wlaczaony");
                }
                setTurbo(true);
                setMessage(StringUtils.join(args, " ", 1, args.length));
                for ( Player p : Bukkit.getOnlinePlayers()) {
//                    BarAPI.setMessage(p, Utill.fixColor("&3TurboDrop, od Administracji! &e" + TurboDropMessage));
                }
                return Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3TurboDrop zostal wlaczaony przez &7" + sender.getName() + " &8(&e" + turboDropCommand.TurboDropMessage + "&8)");
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args.length > 3) {
                    return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
                }

                Turbo turbodrop = TurboDropManager.getByNick(args[1]);
                if (turbodrop != null) {
                    return Utill.sendMsg(sender,  "&4Blad: &cGracz o podanym nicku posiada juz turbodropa!");
                }
                Player o = Bukkit.getPlayer(args[1]);
                if (o == null) {
                    return Utill.sendMsg(sender, "&4Blad: &cgracz jest offline");
                }

                Long time = 0L;
                if (args.length == 3) {
                    time = Utill.parseDateDiff(args[2], true);
                }
                TurboDropManager.createTurboDrop(args[1], time);
//                BarAPI.setMessage(o, Utill.fixColor("&7TurboDrop, Wygasa: &3" + Utill.getDate(time)), (int) ((time - System.currentTimeMillis()) / 1000L));

                if (time != 0L) {
                    return Utill.sendMsg(sender, "&7Dano graczowi &3" + args[1] + " &7turbodropa do dnia &3" + Utill.getDate(time) + "&7!");
                }
                return Utill.sendMsg(sender, "&7Dano graczowi &3" + args[1] + " &7turbodropa na zawsze!");
            }
        }
        return Utill.sendMsg(sender, "&7Prawidlowe uzycie: &3" + this.getUsage());
    }

    public boolean isTurbo() {
        return turboDropCommand.TurboDrop;
    }

    public void setTurbo(final boolean TurboDrop) {
        turboDropCommand.TurboDrop = TurboDrop;
    }
}
