package ro.mihaaiiii.gamesurvival.Game;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class GameManager {

    private static GameManager instance;
    private GameSurvival plugin;
    private Arena arena;
    @Getter
    private ArenaManager arenaManager;
    private ArenaState arenaState;


    public GameManager(GameSurvival plugin) {
        this.plugin = plugin;
        arenaManager = new ArenaManager();
        arena = arenaManager.getArena();
        arenaState = arena.getArenaState();

    }

    public void gameState(ArenaState arenaState) {
        this.arenaState = arenaState;
        switch (arenaState) {
            case OWO -> {

            }
            case START -> {
                //player teleprort to
                arena.sendMessage("Arena is started");
                System.out.println(ChatColor.RED + arena.getPlayers().toString());
                System.out.println(ChatColor.RED + arena.getNameArena() + " Is started");
                arenaManager.startTimer();
            }
            case WAITING -> {
                arena.setStarted(true);
                arena.sendMessage(ChatColor.GRAY + "Arena is waitng");
                arena.arenaPlayer();
                arenaManager.startCountdown();
            }
            case RELOAD -> {
                arena.sendMessage("Arena reload");
            }
            case STOP -> {
                arena.sendMessage(ChatColor.GRAY + "Am sters din arena");
                arenaManager.teleportToSpawn();
                arenaManager.removePlayerFromArena();
                arena.setStarted(false);
                arena.sendMessage(ChatColor.GRAY + "Arena is stopped");
                arena.setArenaState(ArenaState.OWO);

            }
            default -> {
                Bukkit.getLogger().warning("Default state");
            }
        }
    }


    public static GameManager getInstance(GameSurvival plugin) {
        return instance == null ? instance = new GameManager(plugin) : instance;
    }


}
