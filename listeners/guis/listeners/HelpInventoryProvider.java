package me.mati.skyblock.listeners.guis.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.base.User;
import me.mati.skyblock.guisCommands.craftingsCommand;
import me.mati.skyblock.guisCommands.helpCommand;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpInventoryProvider implements InventoryProvider {
    @Override
    public void init(Player p, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        ItemStack kompas = new ItemBuilder(Material.COMPASS, 1).setTitle(Utill.fixColor("&f&l     Glowna Pomoc Serwera")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknij aby wyswietlic dostepne komendy"), Utill.fixColor("&8  » &7Oraz informacje o rozgrywce"))).build();
        contents.set(0 , 4, ClickableItem.of(kompas, e -> {
            p.closeInventory();
            helpCommand.comandsGui.open(p);
        }));
        ItemStack administracja = new ItemBuilder(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()).setTitle(Utill.fixColor("&f&l     Administracja")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknij, aby wyswietlic administracje!"))).build();
        SkullMeta administracjam = (SkullMeta) administracja.getItemMeta();
        administracjam.setOwner("Mateeeusz");
        administracja.setItemMeta(administracjam);
        contents.set(1, 2, ClickableItem.of(administracja, e -> {
            p.closeInventory();
            helpCommand.administracjaGui.open(p);
        }));

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(p.getName());
        meta.setDisplayName(Utill.fixColor("&f&l     " + p.getDisplayName()));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Utill.fixColor(Utill.fixColor("&8  » &7Kliknij mnie aby wyswietlic swoj profil!")));
        meta.setLore(lore);
        skull.setItemMeta(meta);
        contents.set(1, 6, ClickableItem.of(skull, e -> {
            p.closeInventory();
            helpCommand.playerProfileGui.open(p);

        }));
        ItemStack ranks = new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setTitle(Utill.fixColor("&f&l     Rangi Serwerowe")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknij mnie aby zobaczyc dostepne rangi!!"))).build();
        contents.set(3, 1, ClickableItem.of(ranks, e -> {
            p.closeInventory();
            TitleAPI.sendTitle(p,20,40,20,Utill.fixColor("&f&lRangi"), Utill.fixColor("&6&lNa chacie kliknij jaka ranga cie intersuje!"));
            ComponentBuilder cb = new ComponentBuilder("");
            HoverEvent hoverVip = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczy informacje o randze vip")).create());
            ClickEvent clickVip = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vip");
            cb.append(Utill.fixColor("&6VIP ")).event(hoverVip).event(clickVip);
            HoverEvent hoversvip = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczy informacje o randze svip")).create());
            ClickEvent clicksVip = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/svip");
            cb.append(Utill.fixColor("&6SVIP ")).event(hoversvip).event(clicksVip);
            HoverEvent hoverSponsor = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczy informacje o randze sponsor")).create());
            ClickEvent clickSponsor = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sponsor");
            cb.append(Utill.fixColor("&6SPONSOR ")).event(hoverSponsor).event(clickSponsor);
            BaseComponent[] bc = cb.create();
            p.spigot().sendMessage(bc);
        }));
        ItemStack linki = new ItemBuilder(Material.TRIPWIRE_HOOK , 1).setTitle(Utill.fixColor("&f&l     Przydatne linki!")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknije mnie aby otrzymac przydatne linki"))).build();
        contents.set(3, 3, ClickableItem.of(linki, e -> {
            p.closeInventory();
            helpCommand.linksGui.open(p);
        }));

        ItemStack sklep = new ItemBuilder(Material.DOUBLE_PLANT, 1).setTitle(Utill.fixColor("&f&l     Sklep za coinsy")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknij mnie aby przjesc do sklepu!"))).build();
        contents.set(3, 5, ClickableItem.of(sklep, e ->{

        }));

        ItemStack wb = new ItemBuilder(Material.WORKBENCH, 1).setTitle(Utill.fixColor("&f&l     Craftingi serweowe")).addLores(Arrays.asList("", Utill.fixColor("&8  » &7Kliknij mnie aby zobaczyc dostepne crafitngi!!"))).build();
        contents.set(3, 7, ClickableItem.of(wb, e -> {
            craftingsCommand.craftingsinv.open(p);
        }));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
