package ro.mihaaiiii.gamesurvival.utils;

import org.bukkit.ChatColor;

public class MessageUtils {
    public static String message(String message) {

        return ChatColor.translateAlternateColorCodes('&',"&7 ->> &r" +message);
    }
}
