package me.mati.skyblock.base;

import me.mati.skyblock.Main;
import me.mati.skyblock.store.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Ban implements Entry {
    private UUID uuid;
    private String reason;
    private String admin;
    private String username;
    private long createTime;
    private long expireTime;
    private boolean unban;

    public Ban(UUID uuid, String reason, String admin, String username, long expireTime) {
        super();
        this.uuid = uuid;
        this.username = username;
        this.reason = reason;
        this.admin = admin;
        this.createTime = System.currentTimeMillis();
        this.expireTime = expireTime;
        this.unban = false;
        this.insert();
    }

    public Ban(ResultSet rs) throws SQLException {
        super();
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.reason = rs.getString("reason");
        this.admin = rs.getString("admin");
        this.admin = rs.getString("username");
        this.createTime = rs.getLong("createTime");
        this.expireTime = rs.getLong("expireTime");
        this.unban = (rs.getInt("unban") == 1);
    }

    public boolean isAlive() {
        return this.unban || (this.expireTime != 0L && this.expireTime < System.currentTimeMillis());
    }

    public void insert() {
        Main.getStore().update(false, "INSERT INTO `mvti_bans`(`uuid`, `admin`, `username`, `reason`, `createTime`, `expireTime`, `unban`) VALUES ('" + this.getUuid() + "','" + this.getAdmin() + "','" + this.getUsername() + "','" + this.getReason() + "','" + this.getCreateTime() + "','" + this.getExpireTime() + "','" + (this.unban ? 1 : 0) + "')");
    }

    public void update(boolean paramBoolean) {
        String query = "UPDATE `mvti_bans` SET `admin` = '" + this.getAdmin() + "', `username` = '" + this.getUsername() + "', `reason` = '" + this.getReason() + "', `createTime` = '" + this.getCreateTime() + "', `expireTime` = '" + this.getExpireTime() + "', `unban` = '" + (this.isUnban() ? 1 : 0) + "' WHERE `uuid` = '" + this.getUuid() + "'";
        Main.getStore().update(paramBoolean, query);
    }

    public void delete() {
        Main.getStore().update(false, "DELETE FROM `mvti_bans` WHERE `uuid` = '" + this.getUuid() + "'");
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

    public String getAdmin() {
        return this.admin;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isUnban() {
        return this.unban;
    }

    public void setUnban(boolean unban) {
        this.unban = unban;
    }
}