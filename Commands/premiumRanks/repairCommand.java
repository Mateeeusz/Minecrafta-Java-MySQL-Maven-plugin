package me.mati.skyblock.Commands.premiumRanks;

import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class repairCommand extends PlayerCommand {
    private static HashMap<UUID, Long> times = new HashMap<>();

    public repairCommand() {
        super("repair", "naprawa przedmiotu z lapki", "/repair <all>", "sky.repair");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length == 0){
            Long t = repairCommand.times.get(player.getUniqueId());
            if (t != null && System.currentTimeMillis() - t < 300000L) {
                return Utill.sendMsg(player, "&4Blad: &cPonownie mozesz uzyc za: &7" + Utill.getDurationBreakdown(300000L - (System.currentTimeMillis() - t)) + "&c!");
            }
            ItemStack item = player.getItemInHand();
            if (item.getDurability() == 1)
                return Utill.sendMsg(player, "&4Blad: &cNie mozna tego naprawic");
            player.getItemInHand().setDurability((short) 0);
            return Utill.sendMsg(player,"&aNaprawiono!");
        } else if(args.length == 1 && args[0].equalsIgnoreCase("all")){
            if (!player.hasPermission("sky.repair.plus")) {
                return Utill.sendMsg(player, "&cNie masz uprawnien. &7(sky.repair.plus)");
            }
            for (ItemStack items : player.getInventory().getContents()) {
                if (items != null) {
                    items.setDurability((short) 0);
                }
            }
            for (ItemStack items : player.getInventory().getArmorContents()) {
                items.setDurability((short) 0);
            }
            return Utill.sendMsg(player, "&7Naprawiono caly ekwipunek!");
        } else {
            Utill.sendMsg(player, "&4Blad: &cpoprawne uzycie: " + this.getUsage());
        }
        return false;
    }
}
