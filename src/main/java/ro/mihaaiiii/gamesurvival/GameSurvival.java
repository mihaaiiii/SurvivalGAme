package ro.mihaaiiii.gamesurvival;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ro.mihaaiiii.gamesurvival.GameManager.Game;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.command.SetSpawn;
import ro.mihaaiiii.gamesurvival.command.SetSpawnInArena;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.MariaDBConnection;
import ro.mihaaiiii.gamesurvival.listener.EventClickChest;
import ro.mihaaiiii.gamesurvival.listener.JoinListener;
import ro.mihaaiiii.gamesurvival.listener.QuitListener;

public class GameSurvival extends JavaPlugin {
    private SetUpGame setUP = SetUpGame.getInstance(this);
    private Game game;
    private MariaDBConnection db;


    @Override
    public void onEnable() {
        System.out.println("The plugin in online");
        this.getCommand("set").setExecutor(this);
        new EventClickChest(this);
        new JoinListener(this);
        new SetSpawn(this);
        new SetSpawnInArena(this);
        this.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        db = new MariaDBConnection(this);
        System.out.println("AICI ESTE SURVIVALGAME");
        load();

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        game = new Game(this);
        System.out.println(game.getSetUpGame().getArena().getPlayers());
        return true;
    }


    private void load() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

}
