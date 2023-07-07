package me.mati.skyblock.base;

import me.mati.skyblock.Main;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.store.Entry;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User implements Entry {
    private UUID uuid;
    private String lastName;
    private Location lastLocation;
    private Location homeLocation;
    private int koxy;
    private int refy;
    private int perly;
    private int rzucaki;
    private int timePlay;
    private int wykopany_obs;
    private int wykopany_stone;
    private int zjedzone_koxy;
    private int zjedzone_refy;
    private int otworzone_skrzynki;
    private int coinsy;
    private String firstIp;
    private String lastIp;
    private long lastJoin;
    private long kitVIPTime;
    private long kitSVIPTime;
    private long kitMVIPTime;
    private boolean check = true;
    private boolean checko = true;
    private String checker;
    private long startcheck;
    private int checks;
    private Location lastLocCheck;



    public User(Player player){
        super();
        this.uuid = player.getUniqueId();
        this.lastName = player.getName();
        this.lastLocation = player.getLocation();
        this.homeLocation = player.getLocation();
        this.koxy = 0;
        this.refy = 0;
        this.perly = 0;
        this.rzucaki = 0;
        this.timePlay = 0;
        this.wykopany_obs = 0;
        this.wykopany_stone = 0;
        this.zjedzone_koxy = 0;
        this.zjedzone_refy = 0;
        this.otworzone_skrzynki = 0;
        this.coinsy = 0;
        this.firstIp = player.getAddress().getHostString();
        this.lastIp = player.getAddress().getHostString();
        this.lastJoin = System.currentTimeMillis();
        this.kitVIPTime = 0L;
        this.kitSVIPTime = 0L;
        this.kitMVIPTime = 0L;
        this.checks = 0;
        this.insert();
    }

    public User(ResultSet rs) throws SQLException{
        super();
        this.uuid= UUID.fromString(rs.getString("uuid"));
        this.lastName = rs.getString("lastName");
        this.lastLocation = new Location(Bukkit.getWorld(rs.getString("lastWorld")), (double) rs.getInt("lastX"), (double) rs.getInt("lastY"), (double) rs.getInt("lastZ"));
        this.homeLocation = new Location(Bukkit.getWorld(rs.getString("homeWorld")), (double) rs.getInt("homeX"), (double) rs.getInt("homeY"), (double) rs.getInt("homeZ"));
        this.koxy = rs.getInt("koxy");
        this.refy = rs.getInt("refy");
        this.perly = rs.getInt("perly");
        this.rzucaki = rs.getInt("rzucaki");
        this.timePlay = rs.getInt("timePlay");
        this.wykopany_obs = rs.getInt("wykopany_obs");
        this.wykopany_stone = rs.getInt("wykopany_stone");
        this.zjedzone_koxy = rs.getInt("zjedzone_koxy");
        this.otworzone_skrzynki = rs.getInt("otworzone_skrzynki");
        this.coinsy = rs.getInt("coinsy");
        this.firstIp = rs.getString("firstIp");
        this.lastIp = rs.getString("lastIp");
        this.lastJoin = rs.getLong("lastJoin");
        this.lastJoin = rs.getLong("lastJoin");
        this.kitVIPTime = rs.getLong("kitVIPTime");
        this.kitSVIPTime = rs.getLong("kitSVIPTime");
        this.kitMVIPTime = rs.getLong("kitMVIPTime");
        this.checks = rs.getInt("checks");

    }
    public User(String nickname) {
      super();
        this.uuid = Bukkit.getOfflinePlayer(nickname).getUniqueId();
        this.lastName = nickname;
        this.lastLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
        this.homeLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
        this.koxy = 0;
        this.refy = 0;
        this.perly = 0;
        this.rzucaki = 0;
        this.timePlay = 0;
        this.wykopany_obs = 0;
        this.wykopany_stone = 0;
        this.zjedzone_koxy = 0;
        this.zjedzone_refy = 0;
        this.otworzone_skrzynki = 0;
        this.coinsy = 0;
        this.firstIp = "admin-register";
        this.lastIp = "admin-register";
        this.lastJoin = System.currentTimeMillis();
        this.kitVIPTime = 0L;
        this.kitSVIPTime = 0L;
        this.kitMVIPTime = 0L;
        this.checks = 0;
        this.insert();
        UserManager.getUsers().put(this.uuid, this);

    }
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.uuid);
    }


    public Location getHome() {
        return this.homeLocation;
    }

    public void setHome(Location loc) {
        this.homeLocation = loc;
        this.update(false);
    }


    public Location getLocCheck() {
        return this.lastLocCheck;
    }

    public void setLocCheck(Location loc) {
        this.lastLocCheck = loc;
    }


    public void addChecks(int paramInt) {
        this.checks += paramInt;
        this.update(false);
    }

    public void addCoins(int paramInt) {
        this.coinsy += paramInt;
        this.update(false);
    }

    public void removeKoxy(int paramInt) {
        this.koxy -= paramInt;
        this.update(false);
    }

    public void removeRefy(int paramInt) {
        this.refy -= paramInt;
        this.update(false);
    }

    public void removePerly(int paramInt) {
        this.perly -= paramInt;
        this.update(false);
    }

    public void removeCoins(int paramInt) {
        this.coinsy -= paramInt;
        this.update(false);
    }

    public void removeRzucaki(int paramInt) {
        this.rzucaki -= paramInt;
        this.update(false);
    }


    public void addObs(int paramInt) {
        this.wykopany_obs += paramInt;
        this.update(false);
    }

    public void addStone(int paramInt) {
        this.wykopany_stone += paramInt;
        this.update(false);
    }
    public void addSkrzynki(int paramInt) {
        this.otworzone_skrzynki += paramInt;
        this.update(false);
    }
    public void addEatenKox(int paramInt) {
        this.zjedzone_koxy += paramInt;
        this.update(false);
    }
    public void addEatenRef(int paramInt) {
        this.zjedzone_refy += paramInt;
        this.update(false);
    }
    public void addKoxy(int paramInt) {
        this.koxy += paramInt;
        this.update(false);
    }

    public void addRefy(int paramInt) {
        this.refy += paramInt;
        this.update(false);
    }

    public void addPerly(int paramInt) {
        this.perly += paramInt;
        this.update(false);
    }

    public void addRzucaki(int paramInt) {
        this.rzucaki += paramInt;
        this.update(false);
    }


    public void addTimePlay(int paramInt) {
        this.timePlay += paramInt;
        this.update(false);
    }

    public void insert() {
        Main.getStore().update(false, "INSERT INTO `mvti_users`(`uuid`, `lastName`, `lastWorld`, `lastX`, `lastY`, `lastZ`, `homeWorld`, `homeX`, `homeY`, `homeZ`, `koxy`, `refy` , `perly` , `rzucaki` , `timePlay`, `wykopany_obs`, `wykopany_stone`, `zjedzone_koxy`, `zjedzone_refy`, `otworzone_skrzynki`, `coinsy`, `firstIp`, `lastIp`,`lastJoin`,`kitVIPTime`,`kitSVIPTime`,`kitMVIPTime`,`checks`) VALUES (\'" + this.getUuid() + "\',\'" + this.getLastName() + "\',\'" + this.getLastLocation().getWorld().getName() + "\',\'" + this.getLastLocation().getBlockX() + "\',\'" + this.getLastLocation().getBlockY() + "\',\'" + this.getLastLocation().getBlockZ() + "\',\'" + this.getHomeLocation().getWorld().getName() + "\',\'" + this.getHomeLocation().getBlockX() + "\',\'" + this.getHomeLocation().getBlockY() + "\',\'" + this.getHomeLocation().getBlockZ() + "\',\'" + this.getkoxy() + "\',\'" + this.getRefy() + "\',\'" + this .getPerly() + "\',\'" + this.getRzucaki() + "\',\'" + this.getTimePlay() + "\',\'" + this.getWykopany_obs() + "\',\'" + this.getWykopany_stone() + "\',\'" + this.getZjedzone_koxy() + "\',\'" + this.getZjedzone_refy() + "\',\'" + this.getOtworzone_skrzynki() + "\',\'" + this.getCoinsy() + "\', \'" + this.getFirstIp() + "\', \'" + this.getLastIp() + "\', \'" + this.getLastJoin() + "\', \'" + this.getKitVIPTime() + "\', \'" + this.getKitSVIPTime() + "\', \'" + this.getKitMVIPTime() + "\', \'" + this.getChecks() + "\')");
    }

    public void update(boolean now) {
            Main.getStore().update(now, "UPDATE `mvti_users` SET `lastName`=\'" + this.getLastName() + "\',`lastWorld`=\'" + this.getLastLocation().getWorld().getName() + "\',`lastX`=\'" + this.getLastLocation().getBlockX() + "\',`lastY`=\'" + this.getLastLocation().getBlockY() + "\',`lastZ`=\'" + this.getLastLocation().getBlockZ() + "\',`homeWorld`=\'" + this.getHomeLocation().getWorld().getName() + "\',`homeX`=\'" + this.getHomeLocation().getBlockX() + "\',`homeY`=\'" + this.homeLocation.getBlockY() + "\',`homeZ`=\'" + this.getHomeLocation().getBlockZ() + "\',`koxy`=\'" +this.getkoxy() + "\',`refy`=\'" + this.getRefy() + "\',`perly`=\'" + this.getPerly() + "\',`rzucaki`=\'" + this.getRzucaki() + "\',`timePlay`=\'" + this.getTimePlay() + "\',`wykopany_obs`=\'" + this.getWykopany_obs() + "\',`wykopany_stone`=\'" + this.getWykopany_stone() + "\',`zjedzone_koxy`=\'" + this.getZjedzone_koxy() + "\',`zjedzone_refy`=\'" + this.getZjedzone_refy() + "\',`otworzone_skrzynki`=\'" + this.getOtworzone_skrzynki() + "\',`coinsy`=\'" + this.getCoinsy() + "\',`firstIp`=\'" + this.getFirstIp() + "\',`lastIp`=\'" + this.getLastIp() + "\',`lastJoin`=\'" + this.getLastJoin() + "\', `kitVIPTime`=\'" + this.getKitVIPTime() + "\', `kitSVIPTime`=\'" + this.getKitSVIPTime() + "\', `kitMVIPTime`=\'" + this.getKitMVIPTime() + "\', `checks`=\'" + this.getChecks() + "\' WHERE `uuid`=\'" + this.getUuid() + "\'");
    }

    public void delete() {
        Main.getStore().update(false, "DELETE FROM `mvti_users` WHERE `uuid` = \'" + this.getUuid() + "\'");
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public World getWorld() {
        return this.lastLocation.getWorld();
    }


    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getLastLocation() {
        return this.lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public Location getHomeLocation() {
        return this.homeLocation;
    }


    public int getkoxy() {
        return this.koxy;
    }

    public void setKoxy(int koxy) {
        this.koxy = koxy;
    }

    public int getRefy() {
        return this.refy;
    }

    public void setRefy(int refy) {
        this.refy = refy;
    }

    public int getPerly() {
        return this.perly;
    }

    public int getWykopany_obs() {
        return this.wykopany_obs;
    }

    public int getWykopany_stone() {
        return this.wykopany_stone;
    }

    public int getZjedzone_koxy() {
        return this.zjedzone_koxy;
    }

    public int getZjedzone_refy() {
        return this.zjedzone_refy;
    }

    public int getOtworzone_skrzynki() {
        return this.otworzone_skrzynki;
    }

    public int getCoinsy() {
        return this.coinsy;
    }


    public void setPerly(int perly) {
        this.perly = perly;
    }

    public int getRzucaki() {
        return this.rzucaki;
    }

    public void setRzucaki(int rzucaki) {
        this.rzucaki = rzucaki;
    }


    public int getChecks() {
        return this.checks;
    }


    public long getKitVIPTime() {
        return this.kitVIPTime;
    }

    public void setKitVIPTime(long kitVIPTime) {
        this.kitVIPTime = kitVIPTime;
    }

    public long getKitSVIPTime() {
        return this.kitSVIPTime;
    }

    public void setKitSVIPTime(long kitSVIPTime) {
        this.kitSVIPTime = kitSVIPTime;
    }

    public long getKitMVIPTime() {
        return this.kitMVIPTime;
    }

    public void setKitMVIPTime(long kitMVIPTime) {
        this.kitMVIPTime = kitMVIPTime;
    }

    public int getTimePlay() {
        return this.timePlay;
    }

    public long getLastJoin() {
        return this.lastJoin;
    }

    public void setLastJoin(long lastJoin) {
        this.lastJoin = lastJoin;
    }

    public String getFirstIp() {
        return this.firstIp;
    }

    public String getLastIp() {
        return this.lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean Check) {
        this.check = Check;
    }

    public void toggleCheck() {
        setCheck(!isCheck());
    }

    public boolean isChecko() {
        return this.checko;
    }

    public void setChecko(boolean Checko) {
        this.checko = Checko;
    }

    public void toggleChecko() {
        setChecko(!isChecko());
    }

    public String getChecker() {
        return this.checker;
    }

    public void setChecker(String Checker) {
        this.checker = Checker;
    }

    public long getStartCheck() {
        return this.startcheck;
    }

    public void setStartCheck(long StartCheck) {
        this.startcheck = StartCheck;
    }




}
