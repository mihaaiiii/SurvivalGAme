package ro.mihaaiiii.gamesurvival.fileManager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DefaultConfig {
    @Getter
    private static FileConfiguration config;

    public static void setupConfig(GameSurvival plugin) {
        config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    public static void save() {

    }

    public static String getDataBasesTipe() {
        return config.getString("db_type").toLowerCase();
    }

    public static String getUser() {
        return config.getString("db_user");
    }

    public static String getPassword() {
        return config.getString("db_password");
    }

    public static String getUrl() {
        return config.getString("db_url");
    }


    public static Location getLobby() {
        return new Location(Bukkit.getWorld(config.getString("lobyArena.world")), config.getDouble("lobyArena.x"), config.getDouble("lobyArena.y"), config.getDouble("lobyArena.z"));

    }


    public static int getMaxPlayer() {
        return config.getInt("max_player_arena");
    }

    public static int getRandomAmount() {
        return config.getInt("random-amount");
    }

    public static String getTimerTipe() {
        return config.getString("timer_Tipe").toUpperCase();
    }

    public static Set<Location> getSpawnPlayerLocation() {
        Set<Location> locations = new HashSet<>();
        config.getConfigurationSection("spawnPlayer.").getKeys(false).forEach(key -> {

            Location location = new Location(Bukkit.getServer().getWorld(config.getString("spawnPlayer." + key + ".world")),
                    config.getDouble("spawnPlayer." + key + ".x"), config.getDouble("spawnPlayer." + key + ".y"),
                    config.getDouble("spawnPlayer." + key + ".z"));
            locations.add(location);

        });
        return locations;
    }

    public static Set<Location> getChestLocation(String type) {
        Set<Location> chests = new HashSet<>();
        config.getConfigurationSection(type + "_chest.location").getKeys(false).forEach(key -> {

            Location location = new Location(Bukkit.getServer().getWorld(config.getString(type + "_chest.location." + key + ".world")),
                    config.getDouble(type + "_chest.location." + key + ".x"), config.getDouble(type + "_chest.location." + key + ".y"),
                    config.getDouble(type + "_chest.location." + key + ".z"));
            chests.add(location);

        });
        return chests;
    }



    public static List<Location> getArenaSpaws(String name) {
        List<Location> npc = new ArrayList<>();
        config.getConfigurationSection("arenas").getKeys(false).forEach(arenaName -> {
            if (arenaName.equals(name)) {
                config.getConfigurationSection("arenas." + name + ".spawn_location").getKeys(false).forEach(kew -> {
                    Location location = null;
                    for (int i = 0; i < config.getConfigurationSection("arenas." + name + ".spawn_location").getKeys(false).size(); i++) {
                        location = new Location(Bukkit.getServer().getWorld(config.getString("arenas." + name + ".spawn_location." + kew + ".world")),
                                config.getDouble("arenas." + name + ".spawn_location." + kew + ".x"),
                                config.getDouble("arenas." + name + ".spawn_location." + kew + ".y"),
                                config.getDouble("arenas." + name + ".spawn_location." + kew + ".z"));
                        config.getDouble("arenas." + name + ".spawn_location." + kew + ".pitch");
                        config.getDouble("arenas." + name + ".spawn_location." + kew + ".yaw");

                    }
                    npc.add(location);
                });
            }
        });

        return npc;
    }


    public static Location getNpcSpanw() {
        Location location = new Location(Bukkit.getServer().getWorld(config.getString("npc_location.world")), config.getDouble("npc_location.x"), config.getDouble("npc_location.y"), config.getDouble("npc_location.z"));
        config.getDouble("npc_location.pitch");
        config.getDouble("npc_location.yaw");

        return location;
    }
}
