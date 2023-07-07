package me.mati.skyblock.listeners.guis.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.Config;
import me.mati.skyblock.guisCommands.helpCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class LinksProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        ItemStack fb = new ItemBuilder(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()).setTitle(Utill.fixColor("&f&lNasz Facebook!")).addLores(Arrays.asList("", Utill.fixColor("&7»Kliknije mnie aby dostac link do naszego fanpage!"))).build();
        SkullMeta fbm = (SkullMeta) fb.getItemMeta();
        fbm.setOwner("Mateeeusz");
        fb.setItemMeta(fbm);
        ItemStack dc = new ItemBuilder(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()).setTitle(Utill.fixColor("&f&lNasz Discord!")).addLores(Arrays.asList("", Utill.fixColor("&7»Kliknij mnie aby dostac link do naszego discorda!"))).build();
        SkullMeta dcm = (SkullMeta) dc.getItemMeta();
        dcm.setOwner("Mateeeusz");
        dc.setItemMeta(dcm);
        ItemStack sklep = new ItemBuilder(Material.DOUBLE_PLANT, 1).setTitle(Utill.fixColor("&f&lSklep")).addLores(Arrays.asList("", Utill.fixColor("&7»Kliknij mnie aby dostac link do naszego sklepu!!"))).build();


        ItemStack powrot = new ItemBuilder(Material.BARRIER, 1).setTitle(Utill.fixColor("&cPowrot")).build();

        contents.fill(ClickableItem.empty(szara));

        contents.set(1, 2, ClickableItem.of(fb, e ->{
            player.closeInventory();
            TitleAPI.sendTitle(player, 20, 40, 20, Utill.fixColor("&fFacebook"), Utill.fixColor("&6Na chacie wyslalismy ci link kliknij go!"));
            for (String fbw : Config.FB) {
                Utill.sendMsg(player, fbw);
            }
        }));
        contents.set(1, 4, ClickableItem.of(sklep, e ->{
            player.closeInventory();
            TitleAPI.sendTitle(player, 20, 40, 20, Utill.fixColor("&fSklep"), Utill.fixColor("&6Na chacie wyslalismy ci link kliknij go!"));
            for (String sklepw : Config.SKLEP) {
                Utill.sendMsg(player, sklepw);
            }
        }));
        contents.set(1, 6, ClickableItem.of(dc, e ->{
            player.closeInventory();
            TitleAPI.sendTitle(player, 20, 40, 20, Utill.fixColor("&fDiscord"), Utill.fixColor("&6Na chacie wyslalismy ci link kliknij go!"));
            for (String dcw : Config.DC) {
                Utill.sendMsg(player, dcw);
            }
        }));
        contents.set(1, 8, ClickableItem.of(powrot, e ->{
            player.closeInventory();
            helpCommand.helpGui.open(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
