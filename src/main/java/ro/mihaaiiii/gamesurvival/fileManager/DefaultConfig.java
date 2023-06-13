package ro.mihaaiiii.gamesurvival.fileManager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

import java.util.*;
import java.util.stream.Collectors;


public class DefaultConfig {
    @Getter
    private static FileConfiguration config;

    public static void setupConfig(GameSurvival plugin) {
        config = plugin.getConfig();
        plugin.saveDefaultConfig();
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
        return new Location(Bukkit.getWorld(config.getString("lobyArena.world")),
                config.getDouble("lobyArena.x"),
                config.getDouble("lobyArena.y"),
                config.getDouble("lobyArena.z"));

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

    public static Set<Location> getChestLocation(String type) {
        Set<Location> chests = new HashSet<>();
        config.getConfigurationSection("arenas").getKeys(false).forEach(arena -> {
            config.getConfigurationSection("arenas." + arena + "." + type + "_chest.location").getKeys(false).forEach(key -> {

                Location location = new Location(Bukkit.getServer().getWorld(config.getString("arenas." + arena + "." + type + "_chest.location." + key + ".world")),
                        config.getDouble("arenas." + arena + "." + type + "_chest.location." + key + ".x"), config.getDouble("arenas." + arena + "." + type + "_chest.location." + key + ".y"),
                        config.getDouble("arenas." + arena + "." + type + "_chest.location." + key + ".z"));

                chests.add(location);
            });

        });

        return chests;
    }

    public static boolean isArenaInList(String nameArena) {
        return DefaultConfig.getConfig().getConfigurationSection("arenas").getKeys(false).contains(nameArena);

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


    public static HashMap<String, Arena> fllWhitArena() {
        List<String> name = config.getConfigurationSection("arenas").getKeys(false).stream().collect(Collectors.toList());
        HashMap<String, Arena> arenas = new HashMap<>();
        for (int i = 0; i < name.size(); i++) {
            arenas.put(name.get(i),
                    new Arena(name.get(i),
                            false, DefaultConfig.getMaxPlayer(),
                            new HashSet<>(), ArenaState.OWO,
                            DefaultConfig.getArenaSpaws(name.get(i))));
        }
        return arenas;
    }

    public static Location getNpcSpanw() {
        Location location = new Location(Bukkit.getServer().getWorld(config.getString("npc_location.world")), config.getDouble("npc_location.x"), config.getDouble("npc_location.y"), config.getDouble("npc_location.z"));
        config.getDouble("npc_location.pitch");
        config.getDouble("npc_location.yaw");

        return location;
    }

}
