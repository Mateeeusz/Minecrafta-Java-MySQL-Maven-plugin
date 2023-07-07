package me.mati.skyblock.base;

import me.mati.skyblock.Main;
import me.mati.skyblock.store.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Mute implements Entry {
    private UUID uuid;
    private String reason;
    private String username;
    private String admin;
    private long createTime;
    private long expireTime;
    private boolean unmute;

    public Mute(UUID uuid, String username, String reason, String admin, long expireTime) {
        super();
        this.uuid = uuid;
        this.username= username;
        this.reason = reason;
        this.admin = admin;
        this.createTime = System.currentTimeMillis();
        this.expireTime = expireTime;
        this.unmute = false;
        this.insert();
    }

    public Mute(ResultSet rs) throws SQLException {
        super();
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.username = rs.getString("username");
        this.reason = rs.getString("reason");
        this.admin = rs.getString("admin");
        this.createTime = rs.getLong("createTime");
        this.expireTime = rs.getLong("expireTime");
        this.unmute = (rs.getInt("unmute") == 1);
    }

    public boolean isAlive() {
        return this.unmute || (this.expireTime != 0L && this.expireTime < System.currentTimeMillis());
    }

    public void insert() {
        Main.getStore().update(false, "INSERT INTO `mvti_mutes`(`uuid`, `admin`, `username`, `reason`, `createTime`, `expireTime`, `unmute`) VALUES ('" + this.getUuid() + "','" + this.getAdmin() + "','" + this.getUsername() + "','" + this.getReason() + "','" + this.getCreateTime() + "','" + this.getExpireTime() + "','" + (this.unmute ? 1 : 0) + "')");
    }

    public void update(boolean paramBoolean) {
        String query = "UPDATE `mvti_mutes` SET `admin` = '" + this.getAdmin() + "', `reason` = '" + this.getReason()  + "', `username` = '" + this.getUsername() + "', `createTime` = '" + this.getCreateTime() + "', `expireTime` = '" + this.getExpireTime() + "', `unmute` = '" + (this.isUnMute() ? 1 : 0) + "' WHERE `uuid` = '" + this.getUuid() + "'";
        Main.getStore().update(paramBoolean, query);
    }

    public void delete() {
        Main.getStore().update(false, "DELETE FROM `mvti_mutes` WHERE `uuid` = '" + this.getUuid() + "'");
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public boolean isUnMute() {
        return this.unmute;
    }

    public void setUnmute(boolean unmute) {
        this.unmute = unmute;
    }
}
