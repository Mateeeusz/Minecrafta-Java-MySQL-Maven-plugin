package me.mati.skyblock;

import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static List<String> TP_BUTTONS;
    public static List<String> TP_BUTTONSG;
    public static String deposited_PEARL = Utill.fixColor("&8%> &7Pomyslnie wyplaciles &c{DEPO} &7perel!");
    public static String you_HAVE_MAX_PEARL = Utill.fixColor("&8%> &7Posiadasz maksymalna liczbe perel w ekwipunku!");
    public static String not_HAVE_PEARL = Utill.fixColor("&8%> &7Nie posiadasz perel w depozycie!");
    public static String deposited_REF = Utill.fixColor("&8%> &7Pomyslnie wyplaciles &c{DEPO} &7refili!");
    public static String you_HAVE_MAX_REF = Utill.fixColor("&8%> &7Posiadasz maksymalna liczbe refili w ekwipunku!");
    public static String not_HAVE_REF = Utill.fixColor("&8%> &7Nie posiadasz refili w depozycie!");
    public static String deposited_KOX = Utill.fixColor("&8%> &7Pomyslnie wyplaciles &c{DEPO} &7koxow!");
    public static String you_HAVE_MAX_KOX = Utill.fixColor("&8%> &7Posiadasz maksymalna liczbe koxow w ekwipunku!");
    public static String not_HAVE_KOX = Utill.fixColor("&8%> &7Nie posiadasz koxow w depozycie!");
    public static String YOU_CAN_NOT_BAN_YOURSELF = Utill.fixColor("&4Blad: &cNie mozesz zbanowac sam siebie!");
    public static String YOU_CAN_NOT_BAN_THIS_PLAYER = Utill.fixColor("&4Nie mozesz zbanowac tego gracza");
    public static String REASON_IF_NOT_GIVEN = Utill.fixColor("Administrator ma zawsze racje!");//bez kolorow
    public static String THIS_PLAYER_IS_ALREADY_BANED = Utill.fixColor("&4Blad: &cTen gracz ma juz bana!");
    public static String INVENTORY_CLEARED = Utill.fixColor("&7Twoj ekwipunek zostal wyczyszczony!");
    public static String PLAYER_IS_NOT_ONLINE = Utill.fixColor("&4Blad: &cGracz nie jest online!");
    public static String PLAYER_INVENTORY_CLEARED = Utill.fixColor("&2Ekwipunek gracza {PLAYER} zostal wyczyszcony");
    public static String INVENTORY_CLEARED_BY = Utill.fixColor("%2Twoj ekwipunek zostal wyczyszczony przez {PLAYER}");
    public static String ENCHANT_NOT_FOUND = Utill.fixColor("&4Blad: &cNie znaleziono podanego enchantu!");
    public static String FLY_PLAYER_NOT_EXIST= Utill.fixColor("&4Blad {PLAYER} nie istnieje");
    public static String GAMEMODE_CHANGE_SURVIVAL = Utill.fixColor("&2gAMEMEODE ZMIENIONY NA SURVIVAL");
    public static String GAMEMODE_CHANGE_CREATIVE = Utill.fixColor("&2gAMEMEODE ZMIENIONY NA CREATIVE");
    public static String PLAYER_GAMEMODE_CHANGE = Utill.fixColor("&2 Zmieniles gamemode gracza {PLAYER} na {MODE}");
    public static String KICK_ALL_MESSAGE = Utill.fixColor("&aWyrzucono wszystkich graczy z serwera!");
    public static String YOU_CAN_NOT_KICK_YOURSELF = Utill.fixColor("&4Blad: &cNie mozesz wyrzucic sam siebie!");
    public static String YOU_CAN_NOT_KICK_THIS_PLAYER = Utill.fixColor("&4Blad: &cNie mozesz wyrzucic tego gracza!");
    public static String KICK_REASON_IF_NOT_GIVEN = Utill.fixColor("Administrator ma zawsze racje!!");//bez kolorow
    public static String limit_REFIL = Utill.fixColor("&8%> &7Posiadasz przy sobie wiecej niz &c{MAX} &7refili!\n&8%> &7Nadmiar zostaje przeniesiony do &c/depozyt &7! &8(&c{MOVE-SIZE}&8)");
    public static String limit_PERLA = Utill.fixColor("&8%> &7Posiadasz przy sobie wiecej niz &c{MAX} &7perel!\n&8%> &7Nadmiar zostaje przeniesiony do &c/depozyt &7! &8(&c{MOVE-SIZE}&8)");
    public static String limit_KOX = Utill.fixColor("&8%> &7Posiadasz przy sobie wiecej niz &c{MAX} &7koxow!\n&8%> &7Nadmiar zostaje przeniesiony do &c/depozyt &7! &8(&c{MOVE-SIZE}&8)");
    public static int CHAT_SLOWMODE = 3;
    public static int COMBAT_TIME = 20;
    public static String CheckSpawn = "world/32.906611719668255/106.0/34.5689525294822/-171.90033/7.2000084";
    public static int SCHOWEK_KOXYLIMIT = 5;
    public static int SCHOWEK_KREFYLIMIT = 5;
    public static int SCHOWEK_PERLYLIMIT = 5;
    public static String COMMAND_CHATLOCK_ON = "&cChat zostal wlaczony przez <GRACZ>";
    public static String COMMAND_CHAT_OFF = "&cChat zostal wylaczony!";
    public static String CHAT_OFF_MESSAGE = "&cChat jest obecnie wylaczony!";
    public static List<String> COMMAND_YT_MESSAGE = Arrays.asList("/yt cos", "druga linia");
    public static List<String> COMMAND_VIP_MESSAGE = Arrays.asList("/vip cos tam", "druga linia");
    public static List<String> COMMAND_SPONSOR_MESSAGE = Arrays.asList("/sponsor cos tam", "druga linia");
    public static List<String> SKLEP = Arrays.asList("/vip cos tam", "druga linia");
    public static List<String> FB = Arrays.asList("/vip cos tam", "druga linia");
    public static List<String> DC = Arrays.asList("/vip cos tam", "druga linia");
    public static List<String> COMMAND_SVIP_MESSAGE = Arrays.asList("/svip cos tam", "druga linia");
    public static String COMMAND_CHAT_CLEAR = "&c<GRACZ> wyczyscil chat!";
    public static List<String> AUTOMESSAGE_MESSAGES = Arrays.asList("chuj1", "chuj2");
    public static String AUTOMESSAGE_PREFIX = "&6[Informator]";
    public static String FLY_ENABLE_OTHER = "&2fly on dla gracza <PLAYER>";
    public static String FLY_DISABLE_OTHER = "&2fly off dla gracza <PLAYER>";
    public static String Fly_ENABLE = "&2fly on";
    public static String FLY_DISABLE = "&2 fly off";
    public static String DATABASE_TABLEPREFIX = "mvti_";
    public static String DATABASE_MYSQL_HOST = "localhost";
    public static int DATABASE_MYSQL_PORT = 3306;
    public static String DATABASE_MYSQL_USER = "root";
    public static String DATABASE_MYSQL_PASS = "mvti";
    public static String DATABASE_MYSQL_NAME = "mvtimc";
    public static String COST_Stoniarka = "1:0-8;264:0-1;";
    public static String COST_Stoniarka2 = "57:0-8;278:0-1;";
    public static String COST_BoyFarmer = "49:0-3;12:0-3;1:0-3;";
    public static String COST_CX = "4:0-576;";
    public static String COST_TNT = "46:0-288;";
    public static String COST_Enderchest = "49:0-8;368:0-1;";
    public static String INFO_HELPOP = "&8[&4HelpOp&8] &c{PLAYER} &8> &f{MESSAGE}";
    public static String INFO_HELP = "&cWpisz /pomoc aby otrzymac liste dostepnych komend";
    public static int AUTOMESSAGE_INTERVAL = 200;
    public static int ClearItems_TIME = 10;
    public static int SLOTMANAGER_SLOTS = 10;
    public static int HELPOP = 10;
    public static int KitVipTime = 1; //W dniach
    public static int KitSvipTime = 1; //W dniach
    public static int KitMvipTime = 1; //W dniach

    static {
        TP_BUTTONS = new ArrayList<>();
        TP_BUTTONSG = new ArrayList<>();

    }

    public static String locationToStringButton(Location loc) {
        if (loc == null) {
            return null;
        }
        return loc.getWorld().getName() + "/" + loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ();
    }

    public static Location getLocationFromString(final String locationInString) {
        final String[] split = locationInString.split("/");
        return new Location(Bukkit.getServer().getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }


    public static String locationToString(final Location loc) {
        if (loc == null) {
            return null;
        }
        return loc.getWorld().getName() + "/" + loc.getX() + "/" + loc.getY() + "/" + loc.getZ() + "/" + loc.getYaw() + "/" + loc.getPitch();
    }


    public static void AddButtonTP(Location loc) {
        Config.TP_BUTTONS.add(Config.locationToStringButton(loc));
    }

    public static void RemoveButtonTP(Location loc) {
        Config.TP_BUTTONS.remove(Config.locationToStringButton(loc));
    }

    public static boolean IsButtonTP(Location loc) {
        return Config.getTpButtons().contains(Config.locationToStringButton(loc));
    }

    public static void AddButtonTPG(Location loc) {
        Config.TP_BUTTONSG.add(Config.locationToStringButton(loc));
    }

    public static void RemoveButtonTPG(Location loc) {
        Config.TP_BUTTONSG.remove(Config.locationToStringButton(loc));
    }

    public static boolean IsButtonTPG(Location loc) {
        return Config.getTpButtonsG().contains(Config.locationToStringButton(loc));
    }

    public static List<String> getTpButtonsG() {
        return TP_BUTTONSG;
    }

    public static List<String> getTpButtons() {
        return TP_BUTTONS;
    }
}

