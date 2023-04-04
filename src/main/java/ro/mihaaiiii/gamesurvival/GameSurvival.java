package ro.mihaaiiii.gamesurvival;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ro.mihaaiiii.gamesurvival.GameManager.Game;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.command.SetCommonChest;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.MariaDBConnection;
import ro.mihaaiiii.gamesurvival.listener.QuitListener;

//TODO SA NU UITI DE MESAJE
public class GameSurvival extends JavaPlugin {
    private Game game;
    private MariaDBConnection db;


    @Override
    public void onEnable() {
        SetUpGame.getInstance(this);
     //   game = new Game(this);
        Bukkit.getServer().createWorld(new WorldCreator("test"));
//        ChestFactory chestFactory = new ChestFactory(ChestType.COMMON, this);
        new SetCommonChest(this);
//        System.out.println("The plugin in online");
//        this.getCommand("set").setExecutor(this);
//        //  new EventClickChest(this);
//        new JoinListener(this);

        this.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
//        db = new MariaDBConnection(this);
//        System.out.println("AICI ESTE SURVIVALGAME");
        load();

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
