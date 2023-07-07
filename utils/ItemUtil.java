package me.mati.skyblock.utils;

import me.mati.skyblock.Main;
import me.mati.skyblock.Managers.EnchantManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUtil {
    private static final HashMap<Material, String> polishNames;

    static {
        (polishNames = new HashMap<>()).put(Material.AIR, " powietrze");
        ItemUtil.polishNames.put(Material.STONE, " kamien");
        ItemUtil.polishNames.put(Material.GRASS, " trawa");
        ItemUtil.polishNames.put(Material.DIRT, " ziemia");
        ItemUtil.polishNames.put(Material.COBBLESTONE, " bruk");
        ItemUtil.polishNames.put(Material.WOOD, " deski");
        ItemUtil.polishNames.put(Material.SAPLING, " sadzonka");
        ItemUtil.polishNames.put(Material.BEDROCK, " bedrock");
        ItemUtil.polishNames.put(Material.WATER, " woda");
        ItemUtil.polishNames.put(Material.STATIONARY_WATER, " woda");
        ItemUtil.polishNames.put(Material.LAVA, " lawa");
        ItemUtil.polishNames.put(Material.STATIONARY_LAVA, " lawa");
        ItemUtil.polishNames.put(Material.SAND, " piasek");
        ItemUtil.polishNames.put(Material.GRAVEL, " zwir");
        ItemUtil.polishNames.put(Material.GOLD_ORE, " ruda zlota");
        ItemUtil.polishNames.put(Material.IRON_ORE, " ruda zelaza");
        ItemUtil.polishNames.put(Material.COAL_ORE, " ruda wegla");
        ItemUtil.polishNames.put(Material.LOG, " drewno");
        ItemUtil.polishNames.put(Material.LEAVES, " liscie");
        ItemUtil.polishNames.put(Material.SPONGE, " gabka");
        ItemUtil.polishNames.put(Material.GLASS, " szklo");
        ItemUtil.polishNames.put(Material.LAPIS_ORE, " ruda lipisu");
        ItemUtil.polishNames.put(Material.LAPIS_BLOCK, " blok lapisu");
        ItemUtil.polishNames.put(Material.DISPENSER, " dozownik");
        ItemUtil.polishNames.put(Material.SANDSTONE, " pisakowiec");
        ItemUtil.polishNames.put(Material.NOTE_BLOCK, " note block");
        ItemUtil.polishNames.put(Material.BED_BLOCK, " lozko");
        ItemUtil.polishNames.put(Material.POWERED_RAIL, " zasilane tory");
        ItemUtil.polishNames.put(Material.DETECTOR_RAIL, " tory z czujnikiem");
        ItemUtil.polishNames.put(Material.PISTON_STICKY_BASE, " tlok");
        ItemUtil.polishNames.put(Material.WEB, " nic");
        ItemUtil.polishNames.put(Material.LONG_GRASS, " trawa");
        ItemUtil.polishNames.put(Material.DEAD_BUSH, " uschneity krzak");
        ItemUtil.polishNames.put(Material.PISTON_BASE, " tlok");
        ItemUtil.polishNames.put(Material.PISTON_EXTENSION, " tlok");
        ItemUtil.polishNames.put(Material.WOOL, " welna");
        ItemUtil.polishNames.put(Material.PISTON_MOVING_PIECE, " tlok");
        ItemUtil.polishNames.put(Material.YELLOW_FLOWER, " tulipan");
        ItemUtil.polishNames.put(Material.RED_ROSE, " roza");
        ItemUtil.polishNames.put(Material.BROWN_MUSHROOM, " brazowy grzyb");
        ItemUtil.polishNames.put(Material.RED_MUSHROOM, " muchomor");
        ItemUtil.polishNames.put(Material.GOLD_BLOCK, " blok zlota");
        ItemUtil.polishNames.put(Material.IRON_BLOCK, " blok zelaza");
        ItemUtil.polishNames.put(Material.DOUBLE_STEP, " podwojna polplytka");
        ItemUtil.polishNames.put(Material.STEP, " polplytka");
        ItemUtil.polishNames.put(Material.BRICK, " cegly");
        ItemUtil.polishNames.put(Material.TNT, " tnt");
        ItemUtil.polishNames.put(Material.BOOKSHELF, " biblioteczka");
        ItemUtil.polishNames.put(Material.MOSSY_COBBLESTONE, " zamszony bruk");
        ItemUtil.polishNames.put(Material.OBSIDIAN, " obsydian");
        ItemUtil.polishNames.put(Material.TORCH, " pochodnia");
        ItemUtil.polishNames.put(Material.FIRE, " ogien");
        ItemUtil.polishNames.put(Material.MOB_SPAWNER, " mob spawner");
        ItemUtil.polishNames.put(Material.WOOD_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.CHEST, " skrzynia");
        ItemUtil.polishNames.put(Material.REDSTONE_WIRE, " redstone");
        ItemUtil.polishNames.put(Material.DIAMOND_ORE, " ruda diamentu");
        ItemUtil.polishNames.put(Material.DIAMOND_BLOCK, " blok diamentu");
        ItemUtil.polishNames.put(Material.WORKBENCH, " stol rzemieslniczy");
        ItemUtil.polishNames.put(Material.CROPS, " nasionka");
        ItemUtil.polishNames.put(Material.SOIL, " nasionka");
        ItemUtil.polishNames.put(Material.FURNACE, " piecyk");
        ItemUtil.polishNames.put(Material.BURNING_FURNACE, " piecyk");
        ItemUtil.polishNames.put(Material.SIGN_POST, " tabliczka");
        ItemUtil.polishNames.put(Material.WOODEN_DOOR, " drewniane drzwi");
        ItemUtil.polishNames.put(Material.LADDER, " drabinka");
        ItemUtil.polishNames.put(Material.RAILS, " tory");
        ItemUtil.polishNames.put(Material.COBBLESTONE_STAIRS, " brukowe schody");
        ItemUtil.polishNames.put(Material.WALL_SIGN, " tabliczka");
        ItemUtil.polishNames.put(Material.LEVER, " dzwignia");
        ItemUtil.polishNames.put(Material.STONE_PLATE, " plytka naciskowa");
        ItemUtil.polishNames.put(Material.IRON_DOOR_BLOCK, " zelazne drzwi");
        ItemUtil.polishNames.put(Material.WOOD_PLATE, " plytka nasickowa");
        ItemUtil.polishNames.put(Material.REDSTONE_ORE, " ruda redstone");
        ItemUtil.polishNames.put(Material.GLOWING_REDSTONE_ORE, " ruda redstone");
        ItemUtil.polishNames.put(Material.REDSTONE_TORCH_OFF, " czerwona pochodnia");
        ItemUtil.polishNames.put(Material.REDSTONE_TORCH_ON, " czerwona pochodnia");
        ItemUtil.polishNames.put(Material.STONE_BUTTON, " kamienny przycisk");
        ItemUtil.polishNames.put(Material.SNOW, " snieg");
        ItemUtil.polishNames.put(Material.ICE, " lod");
        ItemUtil.polishNames.put(Material.SNOW_BLOCK, " snieg");
        ItemUtil.polishNames.put(Material.CACTUS, " kaktus");
        ItemUtil.polishNames.put(Material.CLAY, " glina");
        ItemUtil.polishNames.put(Material.SUGAR_CANE_BLOCK, " trzcina");
        ItemUtil.polishNames.put(Material.JUKEBOX, " szafa grajaca");
        ItemUtil.polishNames.put(Material.FENCE, " plotek");
        ItemUtil.polishNames.put(Material.PUMPKIN, " dynia");
        ItemUtil.polishNames.put(Material.NETHERRACK, " netherrack");
        ItemUtil.polishNames.put(Material.SOUL_SAND, " pisaek dusz");
        ItemUtil.polishNames.put(Material.GLOWSTONE, " jasnoglaz");
        ItemUtil.polishNames.put(Material.PORTAL, " portal");
        ItemUtil.polishNames.put(Material.JACK_O_LANTERN, " jack'o'latern");
        ItemUtil.polishNames.put(Material.CAKE_BLOCK, " ciasto");
        ItemUtil.polishNames.put(Material.DIODE_BLOCK_OFF, " przekaznik");
        ItemUtil.polishNames.put(Material.DIODE_BLOCK_ON, " przekaznik");
        ItemUtil.polishNames.put(Material.STAINED_GLASS, " utwardzone szklo");
        ItemUtil.polishNames.put(Material.TRAP_DOOR, " wlaz");
        ItemUtil.polishNames.put(Material.MONSTER_EGGS, " jajko potwora");
        ItemUtil.polishNames.put(Material.SMOOTH_BRICK, " cegly");
        ItemUtil.polishNames.put(Material.HUGE_MUSHROOM_1, " duzy grzyb");
        ItemUtil.polishNames.put(Material.HUGE_MUSHROOM_2, " duzy grzyb");
        ItemUtil.polishNames.put(Material.IRON_FENCE, " kraty");
        ItemUtil.polishNames.put(Material.THIN_GLASS, " szyba");
        ItemUtil.polishNames.put(Material.MELON_BLOCK, " arbuz");
        ItemUtil.polishNames.put(Material.PUMPKIN_STEM, " dynia");
        ItemUtil.polishNames.put(Material.MELON_STEM, " arbuz");
        ItemUtil.polishNames.put(Material.VINE, " pnacze");
        ItemUtil.polishNames.put(Material.FENCE_GATE, " furtka");
        ItemUtil.polishNames.put(Material.BRICK_STAIRS, " ceglane schodki");
        ItemUtil.polishNames.put(Material.SMOOTH_STAIRS, " kamienne schodki");
        ItemUtil.polishNames.put(Material.MYCEL, " grzybnia");
        ItemUtil.polishNames.put(Material.WATER_LILY, " lilia wodna");
        ItemUtil.polishNames.put(Material.NETHER_BRICK, " cegly netherowe");
        ItemUtil.polishNames.put(Material.NETHER_FENCE, " netherowy plotek");
        ItemUtil.polishNames.put(Material.NETHER_BRICK_STAIRS, " netherowe schodki");
        ItemUtil.polishNames.put(Material.NETHER_WARTS, " brodawki");
        ItemUtil.polishNames.put(Material.ENCHANTMENT_TABLE, " stol do enchantu");
        ItemUtil.polishNames.put(Material.BREWING_STAND, " stol alchemiczny");
        ItemUtil.polishNames.put(Material.CAULDRON, " kociol");
        ItemUtil.polishNames.put(Material.ENDER_PORTAL, " ender portal");
        ItemUtil.polishNames.put(Material.ENDER_PORTAL_FRAME, " ender portal");
        ItemUtil.polishNames.put(Material.ENDER_STONE, " kamien kresu");
        ItemUtil.polishNames.put(Material.DRAGON_EGG, " jajko smoka");
        ItemUtil.polishNames.put(Material.REDSTONE_LAMP_OFF, " lampa");
        ItemUtil.polishNames.put(Material.REDSTONE_LAMP_ON, " lampa");
        ItemUtil.polishNames.put(Material.WOOD_DOUBLE_STEP, " podwojna drewniana polplytka");
        ItemUtil.polishNames.put(Material.WOOD_STEP, " drewnania polplytka");
        ItemUtil.polishNames.put(Material.COCOA, " kakao");
        ItemUtil.polishNames.put(Material.SANDSTONE_STAIRS, " piaskowe schodki");
        ItemUtil.polishNames.put(Material.EMERALD_ORE, " ruda szmaragdu");
        ItemUtil.polishNames.put(Material.ENDER_CHEST, " skrzynia kresu");
        ItemUtil.polishNames.put(Material.TRIPWIRE_HOOK, " potykacz");
        ItemUtil.polishNames.put(Material.TRIPWIRE, " potykacz");
        ItemUtil.polishNames.put(Material.EMERALD_BLOCK, " blok szmaragdu");
        ItemUtil.polishNames.put(Material.SPRUCE_WOOD_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.BIRCH_WOOD_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.JUNGLE_WOOD_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.COMMAND, " blok polecen");
        ItemUtil.polishNames.put(Material.BEACON, " magiczna latarnia");
        ItemUtil.polishNames.put(Material.COBBLE_WALL, " brukowy plotek");
        ItemUtil.polishNames.put(Material.FLOWER_POT, " doniczka");
        ItemUtil.polishNames.put(Material.CARROT, " marchewka");
        ItemUtil.polishNames.put(Material.POTATO, " ziemniak");
        ItemUtil.polishNames.put(Material.WOOD_BUTTON, " drewniany przycisk");
        ItemUtil.polishNames.put(Material.SKULL, " glowa");
        ItemUtil.polishNames.put(Material.ANVIL, " kowadlo");
        ItemUtil.polishNames.put(Material.TRAPPED_CHEST, " skrzynka z pulapka");
        ItemUtil.polishNames.put(Material.GOLD_PLATE, " zlota polplytka");
        ItemUtil.polishNames.put(Material.IRON_PLATE, " zelaza polplytka");
        ItemUtil.polishNames.put(Material.REDSTONE_COMPARATOR_OFF, " komparator");
        ItemUtil.polishNames.put(Material.REDSTONE_COMPARATOR_ON, " komparator");
        ItemUtil.polishNames.put(Material.DAYLIGHT_DETECTOR, " detektor swiatla dziennego");
        ItemUtil.polishNames.put(Material.REDSTONE_BLOCK, " blok redstone");
        ItemUtil.polishNames.put(Material.QUARTZ_ORE, " ruda kwarcu");
        ItemUtil.polishNames.put(Material.HOPPER, " lej");
        ItemUtil.polishNames.put(Material.QUARTZ_BLOCK, " blok kwarcu");
        ItemUtil.polishNames.put(Material.QUARTZ_STAIRS, " kwarcowe schodki");
        ItemUtil.polishNames.put(Material.ACTIVATOR_RAIL, " tory aktywacyjne");
        ItemUtil.polishNames.put(Material.DROPPER, " podajnik");
        ItemUtil.polishNames.put(Material.STAINED_CLAY, " utwardzona glina");
        ItemUtil.polishNames.put(Material.STAINED_GLASS_PANE, " utwardzona szyba");
        ItemUtil.polishNames.put(Material.LEAVES_2, " liscie");
        ItemUtil.polishNames.put(Material.LOG_2, " drewno");
        ItemUtil.polishNames.put(Material.ACACIA_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.DARK_OAK_STAIRS, " drewniane schodki");
        ItemUtil.polishNames.put(Material.HAY_BLOCK, " sloma");
        ItemUtil.polishNames.put(Material.CARPET, " dywan");
        ItemUtil.polishNames.put(Material.HARD_CLAY, " glina");
        ItemUtil.polishNames.put(Material.COAL_BLOCK, " blok wegla");
        ItemUtil.polishNames.put(Material.PACKED_ICE, " utwardzony lod");
        ItemUtil.polishNames.put(Material.DOUBLE_PLANT, " sadzonka");
        ItemUtil.polishNames.put(Material.IRON_SPADE, " zelazna lopata");
        ItemUtil.polishNames.put(Material.IRON_PICKAXE, " zelazny kilof");
        ItemUtil.polishNames.put(Material.IRON_AXE, " zelazna siekiera");
        ItemUtil.polishNames.put(Material.FLINT_AND_STEEL, " zapalniczka");
        ItemUtil.polishNames.put(Material.APPLE, " jablko");
        ItemUtil.polishNames.put(Material.BOW, " luk");
        ItemUtil.polishNames.put(Material.ARROW, " strzala");
        ItemUtil.polishNames.put(Material.COAL, " wegiel");
        ItemUtil.polishNames.put(Material.DIAMOND, " diament");
        ItemUtil.polishNames.put(Material.IRON_INGOT, " sztabka zelaza");
        ItemUtil.polishNames.put(Material.GOLD_INGOT, " sztabka zlota");
        ItemUtil.polishNames.put(Material.IRON_SWORD, " zelazny miecz");
        ItemUtil.polishNames.put(Material.WOOD_SWORD, " drewniany miecz");
        ItemUtil.polishNames.put(Material.WOOD_SPADE, " drewniana lopata");
        ItemUtil.polishNames.put(Material.WOOD_PICKAXE, " drewniany kilof");
        ItemUtil.polishNames.put(Material.WOOD_AXE, " drewnania siekiera");
        ItemUtil.polishNames.put(Material.STONE_SWORD, " kamienny miecz");
        ItemUtil.polishNames.put(Material.STONE_SPADE, " kamienna lopata");
        ItemUtil.polishNames.put(Material.STONE_PICKAXE, " kamienny kilof");
        ItemUtil.polishNames.put(Material.STONE_AXE, " kamienna siekiera");
        ItemUtil.polishNames.put(Material.DIAMOND_SWORD, " diamentowy miecz");
        ItemUtil.polishNames.put(Material.DIAMOND_SPADE, " diamentowa lopata");
        ItemUtil.polishNames.put(Material.DIAMOND_PICKAXE, " diamentowy kilof");
        ItemUtil.polishNames.put(Material.DIAMOND_AXE, " diamentowa siekiera");
        ItemUtil.polishNames.put(Material.STICK, " patyk");
        ItemUtil.polishNames.put(Material.BOWL, " miseczka");
        ItemUtil.polishNames.put(Material.MUSHROOM_SOUP, " zupa grzybowa");
        ItemUtil.polishNames.put(Material.GOLD_SWORD, " zloty miecz");
        ItemUtil.polishNames.put(Material.GOLD_SPADE, " zlota lopata");
        ItemUtil.polishNames.put(Material.GOLD_PICKAXE, " zloty kilof");
        ItemUtil.polishNames.put(Material.GOLD_AXE, " zlota siekiera");
        ItemUtil.polishNames.put(Material.STRING, " nitka");
        ItemUtil.polishNames.put(Material.FEATHER, " pioro");
        ItemUtil.polishNames.put(Material.SULPHUR, " proch strzelniczy");
        ItemUtil.polishNames.put(Material.WOOD_HOE, " drewniana motyka");
        ItemUtil.polishNames.put(Material.STONE_HOE, " kamienna motyka");
        ItemUtil.polishNames.put(Material.IRON_HOE, " zelazna motyka");
        ItemUtil.polishNames.put(Material.DIAMOND_HOE, " diemtnowa motyka");
        ItemUtil.polishNames.put(Material.GOLD_HOE, " zlota motyka");
        ItemUtil.polishNames.put(Material.SEEDS, " nasionka");
        ItemUtil.polishNames.put(Material.WHEAT, " pszenica");
        ItemUtil.polishNames.put(Material.BREAD, " chleb");
        ItemUtil.polishNames.put(Material.LEATHER_HELMET, " skorzany helm");
        ItemUtil.polishNames.put(Material.LEATHER_CHESTPLATE, " skorzana klata");
        ItemUtil.polishNames.put(Material.LEATHER_LEGGINGS, " skorzane spodnie");
        ItemUtil.polishNames.put(Material.LEATHER_BOOTS, " skorzane buty");
        ItemUtil.polishNames.put(Material.CHAINMAIL_HELMET, " helm z kolcza");
        ItemUtil.polishNames.put(Material.CHAINMAIL_CHESTPLATE, " klata z kolcza");
        ItemUtil.polishNames.put(Material.CHAINMAIL_LEGGINGS, " spodnie z kolcza");
        ItemUtil.polishNames.put(Material.CHAINMAIL_BOOTS, " buty z kolcza");
        ItemUtil.polishNames.put(Material.IRON_HELMET, " zelazny helm");
        ItemUtil.polishNames.put(Material.IRON_CHESTPLATE, " zelazna klata");
        ItemUtil.polishNames.put(Material.IRON_LEGGINGS, " zelazne spodnie");
        ItemUtil.polishNames.put(Material.IRON_BOOTS, " zelazne buty");
        ItemUtil.polishNames.put(Material.DIAMOND_HELMET, " diamentowy helm");
        ItemUtil.polishNames.put(Material.DIAMOND_CHESTPLATE, " diamentowa klata");
        ItemUtil.polishNames.put(Material.DIAMOND_LEGGINGS, " diamentowe spodnie");
        ItemUtil.polishNames.put(Material.DIAMOND_BOOTS, " diamentowe buty");
        ItemUtil.polishNames.put(Material.GOLD_HELMET, " zloty helm");
        ItemUtil.polishNames.put(Material.GOLD_CHESTPLATE, " zlota klata");
        ItemUtil.polishNames.put(Material.GOLD_LEGGINGS, " zlote spodnie");
        ItemUtil.polishNames.put(Material.GOLD_BOOTS, " zlote buty");
        ItemUtil.polishNames.put(Material.FLINT, " krzemien");
        ItemUtil.polishNames.put(Material.PORK, " schab");
        ItemUtil.polishNames.put(Material.GRILLED_PORK, " pieczony schab");
        ItemUtil.polishNames.put(Material.PAINTING, " obraz");
        ItemUtil.polishNames.put(Material.GOLDEN_APPLE, " zlote jablko");
        ItemUtil.polishNames.put(Material.SIGN, " znak");
        ItemUtil.polishNames.put(Material.WOOD_DOOR, " drewniane drzwi");
        ItemUtil.polishNames.put(Material.BUCKET, " wiaderko");
        ItemUtil.polishNames.put(Material.WATER_BUCKET, " wiaderko wody");
        ItemUtil.polishNames.put(Material.LAVA_BUCKET, " wiaderko lawy");
        ItemUtil.polishNames.put(Material.MINECART, " wagonik");
        ItemUtil.polishNames.put(Material.SADDLE, " siodlo");
        ItemUtil.polishNames.put(Material.IRON_DOOR, " zelazne drzwi");
        ItemUtil.polishNames.put(Material.REDSTONE, " czerwony proszek");
        ItemUtil.polishNames.put(Material.SNOW_BALL, " sniezka");
        ItemUtil.polishNames.put(Material.BOAT, " lodka");
        ItemUtil.polishNames.put(Material.LEATHER, " skora");
        ItemUtil.polishNames.put(Material.MILK_BUCKET, " wiaderko mleka");
        ItemUtil.polishNames.put(Material.CLAY_BRICK, " cegly");
        ItemUtil.polishNames.put(Material.CLAY_BALL, " kulka gliny");
        ItemUtil.polishNames.put(Material.SUGAR_CANE, " trzcina cukrowa");
        ItemUtil.polishNames.put(Material.PAPER, " papier");
        ItemUtil.polishNames.put(Material.BOOK, " ksiazka");
        ItemUtil.polishNames.put(Material.SLIME_BALL, " kulka szlamu");
        ItemUtil.polishNames.put(Material.STORAGE_MINECART, " wagonik");
        ItemUtil.polishNames.put(Material.POWERED_MINECART, " wagonik");
        ItemUtil.polishNames.put(Material.EGG, " jajko");
        ItemUtil.polishNames.put(Material.COMPASS, " kompas");
        ItemUtil.polishNames.put(Material.FISHING_ROD, " wedka");
        ItemUtil.polishNames.put(Material.WATCH, " zegar");
        ItemUtil.polishNames.put(Material.GLOWSTONE_DUST, " jasnopyl");
        ItemUtil.polishNames.put(Material.RAW_FISH, " ryba");
        ItemUtil.polishNames.put(Material.COOKED_FISH, " pieczona ryba");
        ItemUtil.polishNames.put(Material.INK_SACK, " czarny barwnik");
        ItemUtil.polishNames.put(Material.BONE, " kosc");
        ItemUtil.polishNames.put(Material.SUGAR, " cukier");
        ItemUtil.polishNames.put(Material.CAKE, " ciasto");
        ItemUtil.polishNames.put(Material.BED, " lozko");
        ItemUtil.polishNames.put(Material.DIODE, " przekaznik");
        ItemUtil.polishNames.put(Material.COOKIE, " ciastko");
        ItemUtil.polishNames.put(Material.MAP, " mapa");
        ItemUtil.polishNames.put(Material.SHEARS, " nozyce");
        ItemUtil.polishNames.put(Material.MELON, " arbuz");
        ItemUtil.polishNames.put(Material.PUMPKIN_SEEDS, " nasiono dyni");
        ItemUtil.polishNames.put(Material.MELON_SEEDS, " nasiono melona");
        ItemUtil.polishNames.put(Material.RAW_BEEF, " stek");
        ItemUtil.polishNames.put(Material.COOKED_BEEF, " pieczony stek");
        ItemUtil.polishNames.put(Material.RAW_CHICKEN, " kurczak");
        ItemUtil.polishNames.put(Material.COOKED_CHICKEN, " upieczony kurczak");
        ItemUtil.polishNames.put(Material.ROTTEN_FLESH, " zgnile mieso");
        ItemUtil.polishNames.put(Material.ENDER_PEARL, " perla endermana");
        ItemUtil.polishNames.put(Material.BLAZE_ROD, " palka blaza");
        ItemUtil.polishNames.put(Material.GHAST_TEAR, " lza gasta");
        ItemUtil.polishNames.put(Material.GOLD_NUGGET, " zloty samorodek");
        ItemUtil.polishNames.put(Material.NETHER_STALK, " brodawka netherowa");
        ItemUtil.polishNames.put(Material.POTION, " mikstura");
        ItemUtil.polishNames.put(Material.GLASS_BOTTLE, " szklana butelka");
        ItemUtil.polishNames.put(Material.SPIDER_EYE, " oko pajaka");
        ItemUtil.polishNames.put(Material.FERMENTED_SPIDER_EYE, " zfermentowane oko pajaka");
        ItemUtil.polishNames.put(Material.BLAZE_POWDER, " blaze powder");
        ItemUtil.polishNames.put(Material.MAGMA_CREAM, " magmowy krem");
        ItemUtil.polishNames.put(Material.BREWING_STAND_ITEM, " stol alchemiczny");
        ItemUtil.polishNames.put(Material.CAULDRON_ITEM, " kociol");
        ItemUtil.polishNames.put(Material.EYE_OF_ENDER, " oko kresu");
        ItemUtil.polishNames.put(Material.SPECKLED_MELON, " arbuz");
        ItemUtil.polishNames.put(Material.MONSTER_EGG, " jajko spawnujace");
        ItemUtil.polishNames.put(Material.EXP_BOTTLE, " butelka z expem");
        ItemUtil.polishNames.put(Material.FIREBALL, " kula ognia");
        ItemUtil.polishNames.put(Material.BOOK_AND_QUILL, " ksiazka z piorem");
        ItemUtil.polishNames.put(Material.WRITTEN_BOOK, " zapisana ksiazka");
        ItemUtil.polishNames.put(Material.EMERALD, " emerald");
        ItemUtil.polishNames.put(Material.ITEM_FRAME, " ramka na obraz");
        ItemUtil.polishNames.put(Material.FLOWER_POT_ITEM, " doniczka");
        ItemUtil.polishNames.put(Material.CARROT_ITEM, " marchewka");
        ItemUtil.polishNames.put(Material.POTATO_ITEM, " ziemniak");
        ItemUtil.polishNames.put(Material.BAKED_POTATO, " upieczony ziemniak");
        ItemUtil.polishNames.put(Material.POISONOUS_POTATO, " trujacy ziemniak");
        ItemUtil.polishNames.put(Material.EMPTY_MAP, " pusta mapa");
        ItemUtil.polishNames.put(Material.GOLDEN_CARROT, " zlota marchewka");
        ItemUtil.polishNames.put(Material.SKULL_ITEM, " glowa");
        ItemUtil.polishNames.put(Material.CARROT_STICK, " marchewka na patyku");
        ItemUtil.polishNames.put(Material.NETHER_STAR, " gwiazda netherowa");
        ItemUtil.polishNames.put(Material.PUMPKIN_PIE, " placek dyniowy");
        ItemUtil.polishNames.put(Material.FIREWORK, " fajerwerka");
        ItemUtil.polishNames.put(Material.FIREWORK_CHARGE, " fajerwerka");
        ItemUtil.polishNames.put(Material.ENCHANTED_BOOK, " enchantowana ksiazka");
        ItemUtil.polishNames.put(Material.REDSTONE_COMPARATOR, " komperator");
        ItemUtil.polishNames.put(Material.NETHER_BRICK_ITEM, " cegla netherowa");
        ItemUtil.polishNames.put(Material.QUARTZ, " kwarc");
        ItemUtil.polishNames.put(Material.EXPLOSIVE_MINECART, " wagonik z tnt");
        ItemUtil.polishNames.put(Material.HOPPER_MINECART, " wagonik z lejem");
        ItemUtil.polishNames.put(Material.IRON_BARDING, " zelazna motyka");
        ItemUtil.polishNames.put(Material.GOLD_BARDING, " zlota motyka");
        ItemUtil.polishNames.put(Material.DIAMOND_BARDING, " diamentowa motyka");
        ItemUtil.polishNames.put(Material.LEASH, " lasso");
        ItemUtil.polishNames.put(Material.NAME_TAG, " name tag");
        ItemUtil.polishNames.put(Material.COMMAND_MINECART, " wagonik z blokiem polecen");
        ItemUtil.polishNames.put(Material.GOLD_RECORD, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.GREEN_RECORD, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_3, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_4, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_5, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_6, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_7, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_8, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_9, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_10, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_11, " plyta muzyczna");
        ItemUtil.polishNames.put(Material.RECORD_12, " plyta muzyczna");
    }


    public static ItemStack getPlayerHead(final String name) {
        final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        final SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(Utill.fixColor("&6" + name));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static void removeItems(final List<ItemStack> items, final Player player) {
        final Inventory inv = player.getInventory();
        final List<ItemStack> removes = new ArrayList<ItemStack>();
        for (final ItemStack item : items) {
            if (inv.containsAtLeast(item, item.getAmount())) {
                removes.add(item);
            }
        }
        if (removes.size() == items.size()) {
            for (final ItemStack item : items) {
                for (final ItemStack remove : removes) {
                    if (item.getType().equals(remove.getType()) && item.getData().equals(remove.getData())) {
                        inv.removeItem(item);
                    }
                }
            }
        }
        removes.clear();
    }
    public static List<ItemStack> getItems(final String string, final int modifier) {
        final List<ItemStack> items = new ArrayList<ItemStack>();
        for (final String s : string.split(";")) {
            final String[] split = s.split("-");
            final int id = Integer.parseInt(split[0].split(":")[0]);
            final int data = Integer.parseInt(split[0].split(":")[1]);
            final int amount = Integer.parseInt(split[1]) * modifier;
            items.add(new ItemStack(Material.getMaterial(id), amount, (short) data));
        }
        return items;
    }
    public static String getItems(final List<ItemStack> items) {
        final StringBuilder sb = new StringBuilder();
        for (final ItemStack item : items) {
            sb.append(getPolishMaterial(item.getType())).append(" ").append("(").append(item.getTypeId()).append("/").append(item.getData().getData()).append(")").append(" ").append(item.getAmount()).append(" ").append("szt.").append("\n");
        }
        return sb.toString();
    }

    public static String getPolishMaterial(final Material material) {
        String name = ItemUtil.polishNames.get(material);
        if (name == null || name.equals("")) {
            name = material.name().toLowerCase().replace("_", " ");
        }
        return name;
    }

    public static boolean checkItems(final List<ItemStack> items, final Player p) {
        for (final ItemStack item : items) {
            if (!p.getInventory().containsAtLeast(item, item.getAmount())) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkAndRemove(final List<ItemStack> items, final Player player) {
        final boolean has = checkItems(items, player);
        if (has) {
            removeItems(items, player);
        }
        return has;
    }
    public static String replace(final String text, final String searchString, final String replacement) {
        if (text == null || text.isEmpty() || searchString.isEmpty() || replacement == null) {
            return text;
        }
        int start = 0;
        int max = -1;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        final int replacedLength = searchString.length();
        int increase = replacement.length() - replacedLength;
        increase = ((increase < 0) ? 0 : increase);
        increase *= ((max > 64) ? 64 : ((max < 0) ? 16 : max));
        final StringBuilder sb = new StringBuilder(text.length() + increase);
        while (end != -1) {
            sb.append(text.substring(start, end)).append(replacement);
            start = end + replacedLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        sb.append(text.substring(start));
        return sb.toString();
    }

    public static boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public static ItemStack parseItemStack(final String itemStack) {
        final ItemStack is = new ItemStack(Material.AIR);
        final String[] strings = itemStack.split(" ");
        final String[] item = strings[0].split(":");
        if (item.length > 1) {
            final Material m = Material.getMaterial(Integer.parseInt(item[0]));
            is.setType(m);
            is.setDurability(Short.parseShort(item[1]));
        }
        else if (isInteger(item[0])) {
            final Material m = Material.getMaterial(Integer.parseInt(item[0]));
            is.setType(m);
        }
        else {
            final Material m = Material.getMaterial(item[0]);
            is.setType(m);
        }
        int amount = 1;
        if (isInteger(strings[1])) {
            amount = Integer.parseInt(strings[1]);
        }
        is.setAmount(amount);
        for (int i = 2; i < strings.length; ++i) {
            final String s = strings[i];
            final String[] trim = s.split(":");
            if (trim.length >= 1) {
                if (trim[0].equalsIgnoreCase("name")) {
                    final ItemMeta im = is.getItemMeta();
                    final String name = Utill.fixColor(replace(trim[1], "_", " "));
                    im.setDisplayName(name);
                    is.setItemMeta(im);
                }
                else if (trim[0].equalsIgnoreCase("lore")) {
                    final ItemMeta im = is.getItemMeta();
                    trim[1] = replace(trim[1], "_", " ");
                    final String[] lorestring = trim[1].split("%nl%");
                    final List<String> lore = new ArrayList<String>();
                    String[] array;
                    for (int length = (array = lorestring).length, j = 0; j < length; ++j) {
                        final String s2 = array[j];
                        lore.add(Utill.fixColor(s2));
                    }
                    im.setLore((List)lore);
                    is.setItemMeta(im);
                }
                else if (trim[0].equalsIgnoreCase("owner")) {
                    is.setType(Material.SKULL);
                    final SkullMeta im2 = (SkullMeta)is.getItemMeta();
                    im2.setOwner(Utill.fixColor(trim[1]));
                }
                else {
                    final Enchantment e = EnchantManager.get(trim[0]);
                    if (e != null) {
                        final int lvl = Integer.parseInt(trim[1]);
                        final ItemMeta im3 = is.getItemMeta();
                        is.setItemMeta(im3);
                        is.addUnsafeEnchantment(e, lvl);
                    }
                }
            }
        }
        return is;
    }
    public static boolean has(final List<ItemStack> items, final Player player) {
        for (final ItemStack is : items) {
            if (getAmountOfItem(is.getType(), player, is.getDurability()) < is.getAmount()) {
                return false;
            }
        }
        return true;
    }

    public static int getAmountOfItem(final Material material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object) material)
                    && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public static void remove(final List<ItemStack> items, final Player player) {
        for (final ItemStack is : items) {
            remove(is, player, is.getAmount());
        }
    }

    public static void remove(final ItemStack is, final Player player, final int amount) {
        int removed = 0;
        boolean all = false;
        final List<ItemStack> toRemove = new ArrayList<ItemStack>();
        final ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; ++i) {
            final ItemStack item = contents[i];
            if (item != null) {
                if (!item.getType().equals((Object) Material.AIR)) {
                    if (item.getType().equals((Object) is.getType())) {
                        if (item.getDurability() == is.getDurability()) {
                            if (!all) {
                                if (removed != amount) {
                                    if (item.getAmount() == amount) {
                                        if (removed == 0) {
                                            toRemove.add(item.clone());
                                            all = true;
                                            removed = item.getAmount();
                                        } else {
                                            final int a = amount - removed;
                                            final ItemStack s = item.clone();
                                            s.setAmount(a);
                                            toRemove.add(s);
                                            removed += a;
                                            all = true;
                                        }
                                    } else if (item.getAmount() > amount) {
                                        if (removed == 0) {
                                            final ItemStack s2 = item.clone();
                                            s2.setAmount(amount);
                                            toRemove.add(s2);
                                            all = true;
                                            removed = amount;
                                        } else {
                                            final int a = amount - removed;
                                            final ItemStack s = item.clone();
                                            s.setAmount(a);
                                            toRemove.add(s);
                                            removed += a;
                                            all = true;
                                        }
                                    } else if (item.getAmount() < amount) {
                                        if (removed == 0) {
                                            toRemove.add(item.clone());
                                            removed = item.getAmount();
                                        } else {
                                            final int a = amount - removed;
                                            if (a == item.getAmount()) {
                                                toRemove.add(item.clone());
                                                removed += item.getAmount();
                                                all = true;
                                            } else if (item.getAmount() > a) {
                                                final ItemStack s = item.clone();
                                                s.setAmount(a);
                                                toRemove.add(s);
                                                removed += a;
                                                all = true;
                                            } else if (item.getAmount() < a) {
                                                toRemove.add(item.clone());
                                                removed += item.getAmount();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        removeItem(player, toRemove);
    }

    public static boolean hasItem(final Player player, final ItemStack item) {
        return player != null && item != null && player.getInventory().containsAtLeast(item, item.getAmount());
    }

    public static boolean hasItem(final Player player, final List<ItemStack> items) {
        if (player == null || items == null) {
            return false;
        }
        if (items.isEmpty()) {
            return true;
        }
        for (final ItemStack is : items) {
            if (!player.getInventory().containsAtLeast(is, is.getAmount())) {
                return false;
            }
        }
        return true;
    }

    public static void removeItem(final Player player, final ItemStack item) {
        if (player == null || item == null) {
            return;
        }
        player.getInventory().removeItem(new ItemStack[] { item });
    }

    public static void removeItem(final Player player, final List<ItemStack> items) {
        if (player == null || items == null || items.isEmpty()) {
            return;
        }
        for (final ItemStack is : items) {
            player.getInventory().removeItem(new ItemStack[] { is });
        }
    }

    public static void giveItem(final Player player, final List<ItemStack> items, final Location location) {
        final PlayerInventory inv = player.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>) inv
                .addItem((ItemStack[]) items.toArray(new ItemStack[items.size()]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            if (en.getValue() != null) {
                if (en.getValue().getType().equals((Object) Material.AIR)) {
                    continue;
                }
                location.getWorld().dropItemNaturally(location, (ItemStack) en.getValue());
            }
        }
    }



    public static void itemDrops(final List<ItemStack> items, final Location location) {
        for (final ItemStack is : items) {
            location.getWorld().dropItemNaturally(location, is);
        }
    }

    public static int playerHasItems(final Player player, final Material mat) {
        int size = 0;
        for (final Map.Entry<Integer, ? extends ItemStack> set : player.getInventory().all(mat).entrySet()) {
            size += ((ItemStack) set.getValue()).getAmount();
        }
        return size;
    }

    public static void recalculateDurability(final Player player, final ItemStack item) {
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        final int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        final short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > RandomUtil.getRandInt(0, 100)) {
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                } else {
                    item.setDurability((short) (d + 1));
                }
            }
        } else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
        } else {
            item.setDurability((short) (d + 1));
        }
    }
}
