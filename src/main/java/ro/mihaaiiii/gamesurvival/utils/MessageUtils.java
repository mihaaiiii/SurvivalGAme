package ro.mihaaiiii.gamesurvival.utils;

import org.bukkit.ChatColor;

public class MessageUtils {
    public static String message(String message) {
        StringBuilder mess = new StringBuilder("&7 ->> &r ");

        return ChatColor.translateAlternateColorCodes('&', mess.append(message).toString());
    }
}
