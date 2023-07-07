package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class checkCommand extends PlayerCommand {
    public static boolean lobby = true;


    public checkCommand() {
        super("check", "Sprawdzanie graczy", "/check <sprawdz/czysty/ban/info/wand> <gracz>", "easyagecore.check", "sprawdz", "cp", "spr");
    }


    public static void StartCheck(Player p, Player o) {
        User u = UserManager.getUser(p);
        User u2 = UserManager.getUser(o);
        u.setLocCheck(p.getLocation());
        u2.setLocCheck(o.getLocation());
        p.teleport(Config.getLocationFromString(Config.CheckSpawn));
        o.teleport(Config.getLocationFromString(Config.CheckSpawn));
        u.setChecker(u2.getLastName());
        u2.setChecker(u.getLastName());
        u.toggleChecko();
        u2.toggleCheck();
        u2.setStartCheck(System.currentTimeMillis());
        u2.addChecks(1);
        checkCommand.toggleLobby();
        Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + o.getName() + " &3jest podejrzany o cheaty. Jest teraz sprawdzany przez &7" + p.getName() + "&3!");
    }

    public static void EndCheck(Player p, Player o, CheckResult result) {
        User u = UserManager.getUser(p);
        User u2 = UserManager.getUser(o);
        u.toggleChecko();
        u2.toggleCheck();
        p.teleport(u.getLocCheck());
        o.teleport(u2.getLocCheck());
        checkCommand.toggleLobby();
        if (result == CheckResult.ALLOW) {
            Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + o.getName() + " &3jest czysty, byl sprawdaczny przez: &7" + p.getName());
        }
        if (result == CheckResult.DISALLOW) {
            Utill.sendMsg(Bukkit.getOnlinePlayers(),"&3Gracz &7" + o.getName() + " &3posiadal cheaty!");
            BanManager.createBan(o.getUniqueId(), "Przy sprawdzaniu posiadales cheaty :)", p.getDisplayName(), o.getDisplayName(), 0L);
        }
        if (result == CheckResult.QUIT) {
            Utill.sendMsg(Bukkit.getOnlinePlayers(), "&3Gracz &7" + o.getName() + " &3wylogowal sie podczas sprawdzania, automatyczny ban.");
            BanManager.createBan(u2.getUuid(), "Wylogowales sie podczas sprawdzania.", p.getName(), o.getDisplayName(),0L);
        }
    }

    public static boolean isLobby() {
        return checkCommand.lobby;
    }

    public static void setLobby(boolean Lobby) {
        checkCommand.lobby = Lobby;
    }

    public static void toggleLobby() {
        setLobby(!isLobby());
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if (args.length == 0) {
            return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        String s = args[0];
        switch (s) {
            case "setspawn":
            case "setcheck":
            case "setcheckspawn": {
                if (!p.hasPermission("sky.check.setspawn")) {
                    return Utill.sendMsg(p, "&cNie masz uprawnien. &7(sky.check.setspawn)");
                }
                Location loc = p.getLocation();
                Config.CheckSpawn = Config.locationToString(loc);
                return Utill.sendMsg(p, "&7Ustawiles spawn do sprawdzania!");
            }
            case "info":
                if (args.length != 2) {
                    return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
                }
                User u = UserManager.getUser(args[1]);
                if (u == null) {
                    return Utill.sendMsg(p, "&4Blad: &cGracz nie istnieje w bazie danych!");
                }
                Utill.sendMsg(p, "&8&m------------- &3" + u.getLastName() + " &8&m-------------");
                Utill.sendMsg(p, " &3Sprawdzany aktualnie: &7" + (u.isCheck() ? "&cNie" : "&aTak"));
                if (!u.isCheck()) {
                    Utill.sendMsg(p, "  &e- &3Przez: &7" + u.getChecker());
                    Utill.sendMsg(p, "  &e- &3Data rozpoczecia sprawdzania: &7" + Utill.getDate(u.getStartCheck()));
                    Utill.sendMsg(p, "  &e- &3Sprawdzany od: &7" + Utill.secondsToString((int) (System.currentTimeMillis() - u.getStartCheck()) / 1000));
                }
                return Utill.sendMsg(p, " &3Lacznie byl sprawdzany: &7" + u.getChecks() + (u.getChecks() == 1 ? "&3 raz" : "&3 razy"));
            case "check":
            case "sprawdz":
            case "cp":
            case "spr":
            case "s": {
                if (args.length != 2) {
                    return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
                }
                Player o1 = Bukkit.getPlayer(args[1]);
                if (o1 == null) {
                    return Utill.sendMsg(p, "&4Blad: &cNie znaleziono gracza: &7" + args[1] + "&c!");
                }
                if (!checkCommand.isLobby()) {
                    Utill.sendMsg(p, "&4Blad: &cKtos juz kogos sprawdza, musisz poczekac!");
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        User uu = UserManager.getUser(pp);
                        if (!uu.isCheck()) {
                            Utill.sendMsg(p, "&7Sprawdzany jest aktualnie &3" + uu.getLastName() + "&7, przez: " + uu.getChecker());
                        }
                    }
                    return Utill.sendMsg(p, "&7Wiecej info o sprawdzajacym &3/check info <gracz>");
                }
                User u1 = UserManager.getUser(o1);
                if (!u1.isCheck()) {
                    return Utill.sendMsg(p, "&4Blad: &cGracz jest w tym momencie przez kogos sprawdzany!");
                }
                checkCommand.StartCheck(p, o1);
                return Utill.sendMsg(p, "&aRozpoczeto sprawdzanie gracza &7" + o1.getName());
            }
            case "clear":
            case "czysty": {
                if (args.length != 2) {
                    return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
                }
                Player ot = Bukkit.getPlayer(args[1]);
                User ut = UserManager.getUser(ot);
                if (ut.isCheck()) {
                    return Utill.sendMsg(p,  "&4Blad: &cGracz nie jest sprawdzany!");
                }
                if (!ut.getChecker().equals(p.getName())) {
                    return Utill.sendMsg(p,  "&4Blad: &cNie mozesz tego zrobic poniewaz nie sprawdzasz tej osoby!");
                }
                checkCommand.EndCheck(p, ot, CheckResult.ALLOW);
                return Utill.sendMsg(p,  "&aOczyszczono");
            }
            case "ban":
                if (args.length != 2) {
                    return Utill.sendMsg(p,  "&7Prawidlowe uzycie: &3" + this.getUsage());
                }

                Player other = Bukkit.getPlayer(args[1]);
                User uth = UserManager.getUser(other);
                if (uth.isCheck()) {
                    return Utill.sendMsg(p,  "&4Blad: &cGracz nie jest sprawdzany!");
                }
                if (!uth.getChecker().equals(p.getName())) {
                    return Utill.sendMsg(p,  "&4Blad: &cNie mozesz tego zrobic poniewaz nie sprawdzasz tej osoby!");
                }
                checkCommand.EndCheck(p, other, CheckResult.DISALLOW);
                return Utill.sendMsg(p, "&aZbanowano");
            default: {
                return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
            }
        }
    }

    public enum CheckResult {
        ALLOW, DISALLOW, QUIT
    }
}

