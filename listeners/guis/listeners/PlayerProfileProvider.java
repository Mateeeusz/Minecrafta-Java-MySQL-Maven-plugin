package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerProfileProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        User u = User.get(player);
        me.mati.skyblock.base.User user = UserManager.getUser(player);
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName(Utill.fixColor("&f&l" + player.getDisplayName()));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Utill.fixColor("&8» &f&lPunkty: &7" + u.getRank().getPoints()));
        contents.set(0, 3, ClickableItem.empty(skull));
        meta.setLore(lore);
        skull.setItemMeta(meta);


        ItemStack guild = new ItemBuilder(Material.DRAGON_EGG).setTitle(Utill.fixColor("&8» &f&lInformacje o gildi:")).addLores(u.hasGuild() ? Arrays.asList(" ", Utill.fixColor("&8» &f&lTag: &7" + u.getGuild().getTag()), Utill.fixColor("&8» &f&lPunkty gildi: &7" + u .getGuild().getRank().getPoints()), Utill.fixColor("&8» &f&lPozycja: &7" + u.getGuild().getRank().getPosition()), Utill.fixColor("&8» &f&lZabojstwa: &7" +u.getGuild().getRank().getKills()), Utill.fixColor("&8» &f&lSmierci: &7" +u.getGuild().getRank().getDeaths())) : Arrays.asList("&8» &f&lBrak Gildi : (")).build();
        contents.set(0, 5, ClickableItem.empty(guild));

        ItemStack kills = new ItemBuilder(Material.DIAMOND_SWORD).setTitle(Utill.fixColor("&8» &f&lZabojstwa: &7" + u.getRank().getKills())).build();
        ItemStack deaths = new ItemBuilder(Material.SKULL_ITEM,1, (short) SkullType.SKELETON.ordinal()).setTitle(Utill.fixColor("&8» &f&lSmierci: &7" + u.getRank().getDeaths())).build();
        ItemStack kdr = new ItemBuilder(Material.ARROW).setTitle(Utill.fixColor("&8» &f&lKDA: &7" + u.getRank().getKDR())).build();
        ItemStack koxy = new ItemBuilder(Material.GOLDEN_APPLE).setTitle(Utill.fixColor("&8» &f&lZjedzonych Jablek &7" )).addLores(Arrays.asList(" ", Utill.fixColor("&8» &f&lZjedzone Koxy: &7" + user.getZjedzone_koxy()), Utill.fixColor("&8» &f&lZjedzone Refy: &7" + user.getZjedzone_refy()))).build();
        ItemStack skrzynki = new ItemBuilder(Material.TRAPPED_CHEST).setTitle(Utill.fixColor("&8» &f&lOtwarte skrzynki: &7" + user.getOtworzone_skrzynki())).build();
        ItemStack obs = new ItemBuilder(Material.OBSIDIAN).setTitle(Utill.fixColor("&8» &f&lWykopany Obsydian: &7" + user.getWykopany_obs())).build();
        ItemStack stone = new ItemBuilder(Material.DIAMOND_PICKAXE).setTitle(Utill.fixColor("&8» &f&lWykopany Kamien: &7" + user.getWykopany_stone())).build();
        ItemStack czas = new ItemBuilder(Material.WATCH).setTitle(Utill.fixColor("&8» &f&lSpedzony czas: &7" + user.getTimePlay())).build();
        ItemStack coins = new ItemBuilder(Material.DOUBLE_PLANT).setTitle(Utill.fixColor("&8» &f&lCoins: &7" + user.getCoinsy())).build();

        contents.set(2, 2, ClickableItem.empty(kills));
        contents.set(2, 4, ClickableItem.empty(deaths));
        contents.set(2, 6, ClickableItem.empty(kdr));
        contents.set(3, 1, ClickableItem.empty(koxy));
        contents.set(3, 7, ClickableItem.empty(skrzynki));
        contents.set(4, 2, ClickableItem.empty(obs));
        contents.set(4, 4, ClickableItem.empty(stone));
        contents.set(4, 6, ClickableItem.empty(czas));
        contents.set(5, 4, ClickableItem.empty(coins));



    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
