package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class buttonCommand extends PlayerCommand {
    public buttonCommand() {
        super("przycisk", "Losowe tp", "/przycisk <ustaw/usun/ustawg>", "sky.button", "groupbutton", "group-button");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        Block targetedBlock = p.getTargetBlock((HashSet<Byte>) null, 20);
        if (args.length != 1) {
            return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
        switch (args[0]) {
            case "ustaw":
                if (!targetedBlock.getType().equals(Material.STONE_BUTTON)) {
                    return Utill.sendMsg(p,"&4Blad: &cBlok na ktory patrzysz nie jest przyciskiem!");
                }
                if (Config.IsButtonTP(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p, "&4Blad: &cTen przycisk jest juz losowym teleportem!");
                }
                if (Config.IsButtonTPG(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p,"&4Blad: &cTen przycisk jest juz losowym teleportem!");
                }
                Config.AddButtonTP(targetedBlock.getLocation());
                return Utill.sendMsg(p, "&aTeleport w losowe koordynaty zostal ustawiony");
            case "usun":
                if (!Config.IsButtonTPG(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p, "&4Blad: &cTen przycisk nie jest losowym teleportem!");
                }
                if (!Config.IsButtonTP(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p, "&4Blad: &cTen przycisk nie jest losowym teleportem!");
                }
                Config.RemoveButtonTP(targetedBlock.getLocation());
                return Utill.sendMsg(p, "&7Teleport w losowe koordynaty zostal usuniety!");
            case "ustawg":
                if (!targetedBlock.getType().equals(Material.STONE_BUTTON)) {
                    return Utill.sendMsg(p, "&4Blad: &cBlok na ktory patrzysz nie jest przyciskiem!");
                }
                if (Config.IsButtonTPG(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p, "&4Blad: &cTen przycisk jest juz losowym teleportem!");
                }
                if (Config.IsButtonTP(targetedBlock.getLocation())) {
                    return Utill.sendMsg(p, "&4Blad: &cTen przycisk jest juz losowym teleportem!");
                }
                Config.AddButtonTPG(targetedBlock.getLocation());
                return Utill.sendMsg(p, "&aTeleport w losowe koordynaty zostal ustawiony");
            default:
                return Utill.sendMsg(p, "&7Prawidlowe uzycie: &3" + this.getUsage());
        }
    }

}
