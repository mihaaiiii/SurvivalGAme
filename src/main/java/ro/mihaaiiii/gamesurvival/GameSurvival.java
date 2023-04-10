package ro.mihaaiiii.gamesurvival;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaTimer;
import ro.mihaaiiii.gamesurvival.GameManager.CountDown;
import ro.mihaaiiii.gamesurvival.GameManager.Game;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.DataBasesFactory;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.utils.register.registerCommands.RegisterCommands;
import ro.mihaaiiii.gamesurvival.utils.register.registerListener.RegisterListener;

//TODO SA NU UITI DE MESAJE
public class GameSurvival extends JavaPlugin {
    private Game game;
    @Getter
    private Villager villager;
    @Getter
    private DataBasesFactory dataBasesFactory;

    @Override
    public void onEnable() {

        DefaultConfig.setupConfig(this);

        World world = Bukkit.getServer().createWorld(new WorldCreator("test"));
        Bukkit.getWorld("Test").getWorldBorder().setSize(60);
        world.setAutoSave(false);
        dataBasesFactory = new DataBasesFactory();
        dataBasesFactory.getRepository().createTable();
        WorldCreator creator = new WorldCreator("testsd2").copy(world);
        creator.createWorld();

        new GameManager(this);

        new RegisterListener(this);
        new RegisterCommands(this);

        new ArenaTimer(this);
        new CountDown(this);


    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        System.out.println(game.getArenaManager().getArena().getPlayers());
        return true;
    }


}
