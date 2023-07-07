package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class coinShopManager {

    public boolean canBuy(Player p, int price){
        User u = UserManager.getUser(p);
        int coins = u.getCoinsy();
        if(coins > price){
            return true;
        }
        return false;
    }

    public void addOffer(ItemStack item, int price, Player p){
        if(canBuy(p, price)){
            p.getInventory().addItem(item);
            User u = UserManager.getUser(p);
            u.removeCoins(price);
            Utill.sendMsg(p, "&2Dziekujemy za zakupy");
            p.closeInventory();
        }
        Utill.sendMsg(p, "&4Nie masz wystarczajaco duzo coinsow");
    }



}
