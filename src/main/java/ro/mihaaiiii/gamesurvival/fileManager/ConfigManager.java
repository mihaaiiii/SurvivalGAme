package ro.mihaaiiii.gamesurvival.fileManager;

import org.bukkit.configuration.file.FileConfiguration;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class ConfigManager {
    private static FileConfiguration config;

    public static void setupConfig(GameSurvival plugin) {
        ConfigManager.config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }



}
