package me.mati.skyblock.listeners.guis.listeners;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class EfektyProvider implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack szara = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setTitle(Utill.fixColor("&8&l.")).build();
        contents.fill(ClickableItem.empty(szara));
        ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
        ItemStack sila1 = new ItemBuilder(Material.DIAMOND_SWORD, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lSila I &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(1, 1, ClickableItem.of(sila1, e -> {
            if(player.getInventory().containsAtLeast(new ItemStack(Material.EMERALD_BLOCK), 7)){
                player.getInventory().removeItem(new ItemStack(Material.EMERALD_BLOCK, 7));
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 0));
                Utill.sendMsg(player, "&2zakupiles efekt!");
            }else {
                Utill.sendMsg(player, "&4Nie masz 7 emerald block");
                player.closeInventory();
            }
        }));
        ItemStack sila2 = new ItemBuilder(Material.DIAMOND_SWORD, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lSila II &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();

        contents.set(2, 1, ClickableItem.of(sila2, e -> {
            Utill.RemoveCost(player, emerald, 14);
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4000, 0));
        }));

        ItemStack haste1 = new ItemBuilder(Material.DIAMOND_PICKAXE, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lHASTE I &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(0, 4, ClickableItem.of(haste1, e -> {
            Utill.RemoveCost(player, emerald, 20);
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 4000, 0));
        }));
        ItemStack speed1 = new ItemBuilder(Material.SUGAR, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lSPEED I &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(1, 4 ,ClickableItem.of(speed1, e-> {
            Utill.RemoveCost(player, emerald, 6);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4000, 0));
        }));

        ItemStack speed2 = new ItemBuilder(Material.SUGAR, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lSPEED II &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(2, 4 ,ClickableItem.of(speed2, e-> {
            Utill.RemoveCost(player, emerald, 12);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4000, 1));
        }));

        ItemStack fire = new ItemBuilder(Material.BLAZE_POWDER, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lFIRE RESISTANCE &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(3, 4 ,ClickableItem.of(fire, e-> {
            Utill.RemoveCost(player, emerald, 5);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4000, 1));
        }));

        ItemStack mleko = new ItemBuilder(Material.MILK_BUCKET, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lUSUN EFEKTY &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cUsuwa wszystkie efekty!")).build();
        contents.set(4, 4 ,ClickableItem.of(mleko, e-> {
            Utill.RemoveCost(player, emerald, 4);
            for(PotionEffect efeect : player.getActivePotionEffects()){
                player.removePotionEffect(efeect.getType());
            }
        }));

        ItemStack SKOK = new ItemBuilder(Material.DIAMOND_BOOTS, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lWYSOKIE SKAKANIE &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(1, 7, ClickableItem.of(SKOK, e -> {
            Utill.RemoveCost(player, emerald, 7);
            player.removePotionEffect(PotionEffectType.JUMP);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4000, 1));
        }));

        ItemStack widzenie = new ItemBuilder(Material.TORCH, 1).setTitle(Utill.fixColor("&8&l>&8&l> &c&lWIDZENIE W CIEMNOSCI &8&l<&8&l<")).addLore(Utill.fixColor(" &8>> &cCzas trwania&7: 5 minut")).addLore(Utill.fixColor(" &8>> &cKoszt&7: 2 bloki szmaragdowe")).addLore(Utill.fixColor(" &8>> &aKliknij, aby zakupic efekt!")).build();
        contents.set(2, 7, ClickableItem.of(widzenie, e -> {
            Utill.RemoveCost(player, emerald, 7);
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 4000, 1));
        }));

    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
