package me.mati.skyblock.Commands.admin;

import me.mati.skyblock.Config;
import me.mati.skyblock.api.commands.PlayerCommand;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public class ChatLock extends PlayerCommand {
    private static boolean chat = false;

    public ChatLock() {
        super("chatlock", "zmienianie statusu czatu", "/chatlock", "sky.cl", "cl");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        setChat(isChat());
        if (isChat() == true) {
            for (Player o : Bukkit.getOnlinePlayers()) {
                for (int i = 0; i < 10; ++i) {
                    o.sendMessage("");
                }
            }
            Utill.sendMsg(Bukkit.getOnlinePlayers(), Config.COMMAND_CHAT_OFF.replace("<GRACZ>", p.getName()));
            setChat(false);
        } else if (isChat() == false) {
            for (Player o : Bukkit.getOnlinePlayers()) {
                for (int i = 0; i < 10; ++i) {
                    o.sendMessage("");
                }
            }
            Utill.sendMsg(Bukkit.getOnlinePlayers(), Config.COMMAND_CHATLOCK_ON.replace("<GRACZ>", p.getName()));
            setChat(true);
        }
        return false;
    }




    public static boolean isChat() {
        return chat;
    }

    public static void setChat(boolean chat) {
        ChatLock.chat = chat;
    }
}

