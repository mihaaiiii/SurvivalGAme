package ro.mihaaiiii.gamesurvival.Game;

import org.bukkit.ChatColor;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class GameManager {
    private GameSurvival plugin;
    private Arena arena;

    private ArenaManager arenaManager;
    private ChestFactory chestFactory;


    public GameManager(ArenaState arenaState, GameSurvival plugin) {
        this.plugin = plugin;
        arenaManager = ArenaManager.getInstance(plugin);
        this.arena = arenaManager.getArena();


        //start count
        // chest fill
        // teleport player
        // start game
        switch (arenaState) {
            case START -> {
                arena.sendMessage("Arena is started");
                System.out.println(ChatColor.RED + arenaManager.getArena().getPlayers().toString());
                arenaManager.teleportPlayersToArena();
                arenaManager.startTimer();
            }
            case WAITING -> {
                arena.sendMessage("Arena is waitng");
                arenaManager.chestFill();
                arena.sendMessage("Arena is started");
                arenaManager.startCountdown();
                //chest fill
                // teleport player
            }
            case RELOAD -> {
                arena.sendMessage("Arena reload");
            }
            case STOP -> {
                arenaManager.getArena().removePlayerFromArena();
                arena.sendMessage("Arena is stopped");
                plugin.getServer().getScheduler().cancelTasks(plugin);
            }
        }

    }

}
