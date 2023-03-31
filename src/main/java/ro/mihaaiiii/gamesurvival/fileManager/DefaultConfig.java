package ro.mihaaiiii.gamesurvival.fileManager;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.io.File;
import java.io.IOException;

@Getter
public class DefaultConfig {
    private final GameSurvival plugin;
    private File file;
    private FileConfiguration fileConfiguration;
    private static DefaultConfig instance;


    private DefaultConfig(GameSurvival plugin) {
        this.plugin = plugin;
        setUp();

    }

    public void setUp() {
        file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);

    }


    public void save() {
        try {
            fileConfiguration.options().copyDefaults(true);
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        try {
            fileConfiguration.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static DefaultConfig getInstante(GameSurvival plugin) {
        return instance == null ? instance = new DefaultConfig(plugin) : instance;
    }
}
