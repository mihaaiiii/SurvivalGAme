package ro.mihaaiiii.gamesurvival;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaTimer;
import ro.mihaaiiii.gamesurvival.GameManager.CountDown;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.DataBasesFactory;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.utils.register.registerCommands.RegisterCommands;
import ro.mihaaiiii.gamesurvival.utils.register.registerListener.RegisterListener;

import java.util.Objects;

//TODO SA NU UITI DE MESAJE
public class GameSurvival extends JavaPlugin {

    @Getter
    private DataBasesFactory dataBasesFactory;


    @Override
    public void onEnable() {
        DefaultConfig.setupConfig(this);


        DefaultConfig.fllWhitArena().forEach(System.out::printf);

        World world = Bukkit.getServer().createWorld(new WorldCreator("test"));
        Objects.requireNonNull(Bukkit.getWorld("Test")).getWorldBorder().setSize(60);
        assert (world != null) : "The world is null";
        world.setAutoSave(false);
        dataBasesFactory = new DataBasesFactory();
        dataBasesFactory.getRepository().createTable();
        WorldCreator creator = new WorldCreator("testsd2").copy(world);
        creator.createWorld();

        new RegisterListener(this);
        new RegisterCommands(this);

        new ArenaTimer();
        new CountDown(this);


    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }


}
