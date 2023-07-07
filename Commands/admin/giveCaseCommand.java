package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Main;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class giveCaseCommand extends PlayerCommand {
    public giveCaseCommand() {
        super("givecase", "dawanie premium case", "givecase <all/gracz> ilosc", "sky.case", "itemcase");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if(args.length != 2){
            Utill.sendMsg(p, "&4Blad: &cPoprawne uzycie: " + this.getUsage());
        } else {
            if(Utill.isInteger(args[1])){
                if(args[0].equalsIgnoreCase("all")){
                    for(Player all : Bukkit.getOnlinePlayers()){
                        ItemStack pc =  new ItemStack(Material.TRAPPED_CHEST);
                        ItemMeta pcim = pc.getItemMeta();
                        pc.setAmount(Integer.parseInt(args[1]));
                        pcim.setDisplayName(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("itemstack-premiumcase.name")));
                        List<String> lore =  new ArrayList<String>();
                        for(String string : Main.getPlugin(Main.class).getConfig().getStringList("itemstack-premiumcase.lore")){
                            lore.add(Utill.fixColor(string));
                        }
                        pcim.setLore((List)lore);
                        pc.setItemMeta(pcim);
                        Utill.sendMsg(all, "&8>> &7Otrzymales: &6PremiumCase &7(&ex" + args[1] + "&7)!");
                        Utill.sendMsg(p , "&8>> &7Kazdy &7otrzymal &6PremiumCase&7 (&ex" + args[1] + "&7)");
                        if(all.getInventory().firstEmpty() == -1){
                            all.getLocation().getWorld().dropItemNaturally(all.getLocation(), pc);
                            return true;
                        }
                        all.getInventory().addItem(new ItemStack[] { pc });
                    }
                } else {
                    Player p1 = Bukkit.getPlayer(args[0]);
                    ItemStack pc =  new ItemStack(Material.TRAPPED_CHEST);
                    ItemMeta pcim = pc.getItemMeta();
                    pc.setAmount(Integer.parseInt(args[1]));
                    pcim.setDisplayName(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("itemstack-premiumcase.name")));
                    List<String> lore =  new ArrayList<String>();
                    for(String string : Main.getPlugin(Main.class).getConfig().getStringList("itemstack-premiumcase.lore")){
                        lore.add(Utill.fixColor(string));
                    }
                    pcim.setLore((List)lore);
                    pc.setItemMeta(pcim);
                    if(p1 == null){
                        Utill.sendMsg(p, "&4Gracz offline");
                        return true;
                    }
                    if(p1.getInventory().firstEmpty() == -1){
                        p1.getLocation().getWorld().dropItemNaturally(p1.getLocation(), pc);
                        return true;
                    }
                    p1.getInventory().addItem(new ItemStack[] { pc });
                    p1.sendMessage(Utill.fixColor("&8>> &7Otrzymales: &6PremiumCase &7(&ex" + args[1] + "&7)!"));
                    p.sendMessage(Utill.fixColor("&8>> &7Gracz: &2" + args[0] + " &7otrzymal &6PremiumCase&7 (&ex" + args[1] + "&7)"));
                }
            } else {
                Utill.sendMsg(p, "&aPodany argument nie jest liczba " + args[0]);
                return true;
            }
        }
//        if(args.length != 2){
//            Utill.sendMsg(p, "&4Blad: &cPoprawne uzycie: " + this.getUsage());
//        }
//        if(args[0].equalsIgnoreCase("all")){
//            for(Player all : Bukkit.getOnlinePlayers()){
//                ItemStack pc =  new ItemStack(Material.TRAPPED_CHEST);
//                ItemMeta pcim = pc.getItemMeta();
//                pc.setAmount(Integer.parseInt(args[1]));
//                pcim.setDisplayName(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("itemstack-premiumcase.name")));
//                List<String> lore =  new ArrayList<String>();
//                for(String string : Main.getPlugin(Main.class).getConfig().getStringList("itemstack-premiumcase.lore")){
//                    lore.add(Utill.fixColor(string));
//                }
//                pcim.setLore((List)lore);
//                pc.setItemMeta(pcim);
//                Utill.sendMsg(all, "&8>> &7Otrzymales: &6PremiumCase &7(&ex" + args[1] + "&7)!");
//                Utill.sendMsg(p , "&8>> &7Kazdy &7otrzymal &6PremiumCase&7 (&ex" + args[1] + "&7)");
//                if(all.getInventory().firstEmpty() == -1){
//                    all.getLocation().getWorld().dropItemNaturally(all.getLocation(), pc);
//                    return true;
//                }
//                all.getInventory().addItem(new ItemStack[] { pc });
//            }
//        }
//            Player p1 = Bukkit.getPlayer(args[0]);
//            ItemStack pc =  new ItemStack(Material.TRAPPED_CHEST);
//            ItemMeta pcim = pc.getItemMeta();
//            pc.setAmount(Integer.parseInt(args[1]));
//            pcim.setDisplayName(Utill.fixColor(Main.getPlugin(Main.class).getConfig().getString("itemstack-premiumcase.name")));
//            List<String> lore =  new ArrayList<String>();
//            for(String string : Main.getPlugin(Main.class).getConfig().getStringList("itemstack-premiumcase.lore")){
//                lore.add(Utill.fixColor(string));
//            }
//            pcim.setLore((List)lore);
//            pc.setItemMeta(pcim);
//            if(p1 == null){
//                Utill.sendMsg(p, "&4Gracz offline");
//                return true;
//            }
//            if(Utill.isInteger(args[1])){
//                Utill.sendMsg(p, "&aPodany argument nie jest liczba " + args[0]);
//                return true;
//            }
//            if(p1.getInventory().firstEmpty() == -1){
//                p1.getLocation().getWorld().dropItemNaturally(p1.getLocation(), pc);
//                return true;
//            }
//            p1.getInventory().addItem(new ItemStack[] { pc });
//            p1.sendMessage(Utill.fixColor("&8>> &7Otrzymales: &6PremiumCase &7(&ex" + args[1] + "&7)!"));
//            p.sendMessage(Utill.fixColor("&8>> &7Gracz: &2" + args[0] + " &7otrzymal &6PremiumCase&7 (&ex" + args[1] + "&7)"));
//        return true;
        return false;
    }
}
