package ro.mihaaiiii.gamesurvival.Game;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private static GameManager instance;
    private GameSurvival plugin;
    private Arena arena;
    @Getter
    private ArenaManager arenaManager;
    private ChestFactory chestFactory;
    Map<Integer, Arena> arenas = new HashMap<>();


    public GameManager(GameSurvival plugin) {
        this.plugin = plugin;
        arenaManager = ArenaManager.getInstance(plugin);
        arena = arenaManager.getArena();
        gameState(arena.getArenaState());

    }

    public void gameState(ArenaState arenaState) {
        arena.setArenaState(arenaState);
        switch (arena.getArenaState()) {
            case OWO -> {

            }
            case START -> {
                //player teleprort to
                arena.sendMessage("Arena is started");
                System.out.println(ChatColor.RED + arenaManager.getArena().getPlayers().toString());
                arenaManager.startTimer();
            }
            case WAITING -> {
                //player teleport to lobby arena
                arena.setStarted(true);
                arena.sendMessage(ChatColor.GRAY + "Arena is waitng");
                arenaManager.getArena().arenaPlayer();

                arena.sendMessage(ChatColor.GRAY + "Arena is started");
                arenaManager.teleportPlayersToArena();
                arenaManager.startCountdown();
                //chest fill
                // teleport player
            }
            case RELOAD -> {
                arena.sendMessage("Arena reload");
            }
            case STOP -> {
                arena.sendMessage(ChatColor.GRAY + "Am sters din arena");
                arenaManager.teleportToSpawn();
                arenaManager.getArena().removePlayerFromArena();
                arena.sendMessage(ChatColor.GRAY + "Arena is stopped");
                System.out.println(ChatColor.GRAY + " " + arenaManager.getArena().arenaPlayer());
                arenaManager.getArena().setArenaState(ArenaState.OWO);

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
