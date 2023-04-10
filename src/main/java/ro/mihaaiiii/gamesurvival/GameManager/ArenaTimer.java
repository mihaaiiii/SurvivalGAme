package ro.mihaaiiii.gamesurvival.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class ArenaTimer extends BukkitRunnable {
    private static ArenaTimer arenaTimer;
    private GameSurvival plugin;
    private Arena arena;
    int contdow = 59;
    private GameManager gameManager;
    private ArenaManager arenaManager;


    public static ArenaTimer getArenaTimer() {
        return arenaTimer;
    }

    public ArenaTimer(GameSurvival plugin) {
        arenaTimer = this;
        this.plugin = plugin;
        gameManager = GameManager.getInstance(plugin);
        arenaManager = gameManager.getArenaManager();


    }

    public void start() {
        runTaskLater(plugin, contdow * 20);
    }

    @Override
    public void run() {
        if (this != null) {
            cancel();
        }

        Bukkit.getWorld("test").getWorldBorder().setSize(60);
        System.out.println(" ARENA TIMER: YEY" + contdow);
        System.out.println("Ai oprit yey");
        gameManager.getArenaManager().getArena().setArenaState(ArenaState.STOP);
        gameManager.gameState(gameManager.getArenaManager().getArena().getArenaState());


    }
}
