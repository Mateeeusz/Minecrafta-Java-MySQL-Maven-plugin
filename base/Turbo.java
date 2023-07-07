package me.mati.skyblock.base;

import me.mati.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Turbo {

    private String nick;
    private Long expireTime;

    public Turbo() {
    }

    public Turbo(ResultSet set) throws SQLException {
        this.nick = set.getString("nick");
        this.expireTime = set.getLong("expireTime");
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.nick);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.nick);
    }

    public void insert() {
        String update = "INSERT INTO `mvti_turbodrop` (`nick`, `expireTime`) VALUES ('" + this.nick + "', '" + this.expireTime + "')";
        Main.getStore().update(true, update);
    }

    public void delete() {
        String update = "DELETE FROM `mvti_turbodrop` WHERE `nick`='" + this.nick + "'";
        Main.getStore().update(true, update);
    }
}