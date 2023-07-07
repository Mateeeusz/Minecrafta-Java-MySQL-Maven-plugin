package me.mati.skyblock.Commands;

import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class premiumCommand extends Command {

    public premiumCommand() {
        super("is", "Nadawanie uslug na serwerze", "/is nick (usluga)", "sky.itemshop", "itemshop");
    }

    public boolean onExecute(CommandSender sender, String[] args) {
        if(args.length == 2){
            Player buyer = Bukkit.getPlayer(args[0]);
            String usluga = args[1];
            if(buyer == null){
                Utill.sendMsg(sender, "&4Blad: &cpodany gracz jest offline!");
                return true;
            }
            switch (usluga){
                case "vip": {

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc addGroup " + buyer.getDisplayName() + " vip 90d");
                break;
            }
                case "svip": {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc addGroup " + buyer.getDisplayName() + " svip 90d");
                    break;
                }
                case "sponsor": {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc addGroup " + buyer.getDisplayName() + " sponsor 90d");
                    break;
                } default: {
                    return Utill.sendMsg(sender, "Dostepne uslugi: VIPS,SVIP,SPONSOR");
                }
            }

        } else {
            Utill.sendMsg(sender, "&4Blad: &cpoprawne uzycie: " + this.getUsage());
        }
//        if(args[0].length() < 1) {
//            Utill.sendMsg(sender, "&cPrawidlowe uzycie &c" + this.getUsage());
//            return false;
//        }
//
//        String other = Bukkit.getPlayer(args[0]).getDisplayName();
//        String usluga = args[1];
//        if(other ==null) {
//            Utill.sendMsg(sender, "&cGracz jest offline");
//            return true;
//        }
//        switch (usluga) {
//            case "vip": {
//                Utill.broadcast("VIP KUPIONY PSRZYSZEDL PRZELEW PRZYSZLO SIANO");
//                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + other + " group set vip");
//                break;
//            }
//            case "svip": {
//                Utill.broadcast("&7jakis chuj kupil svipa nasza storna www: chujwdupiechlupie.pl");
//                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + other + " group set svip");
//                break;
//            }default: {
//                return Utill.sendMsg(sender, "&7Dostepne uslugi to: VIP, SVIP");
//            }
//        }
//        return false;
//    }
        return false;
    }
}
