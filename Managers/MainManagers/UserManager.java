package me.mati.skyblock.Managers.MainManagers;

import me.mati.skyblock.Main;
import me.mati.skyblock.base.User;
import me.mati.skyblock.utils.Logger;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserManager {
    private static HashMap<UUID, User> users = new HashMap<>();

    public static User getUser(Player player) {
        return UserManager.users.get(player.getUniqueId());
    }

    public static User getUser(UUID uuid) {
        return UserManager.users.get(uuid);
    }

    public static User getUser(String name) {
        for (User u : UserManager.users.values()) {
            if (u.getLastName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public static Set<User> getUsersByIp(String ip) {
        return UserManager.users.values().stream().filter(u -> u.getLastIp().equalsIgnoreCase(ip) || u.getFirstIp().equalsIgnoreCase(ip)).collect(Collectors.toSet());
    }

    public static void createUser(Player p) {
        User u = new User(p);
        UserManager.users.put(p.getUniqueId(), u);
    }

    public static void joinToGame(Player p) {
        User u = getUser(p);
        if (u == null) {
            createUser(p);
            return;
        }
        u.setLastName(p.getName());
        u.setLastIp(p.getAddress().getHostString());
        u.setLastJoin(System.currentTimeMillis());
    }

    public static void leaveFromGame(Player p) {
        User u = getUser(p);
        if (u == null) {
            Logger.severe("Dane uzytkownika '" + p.getName() + "' przepadly!");
            return;
        }
        u.setLastName(p.getName());
        u.addTimePlay((int) ((System.currentTimeMillis() - u.getLastJoin()) / 1000L));
        u.update(false);
    }

    public static void setup() {
        ResultSet rs = Main.getStore().query("SELECT * FROM `mvti_users`");
        try {
            while (rs.next()) {
                User u = new User(rs);
                UserManager.users.put(u.getUuid(), u);
            }
            Logger.info("Loaded " + UserManager.users.size() + " users!");
        } catch (SQLException e) {
            Logger.warning("An error occurred while loading users!", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HashMap<UUID, User> getUsers() {
        return UserManager.users;
    }
}
