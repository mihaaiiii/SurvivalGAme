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


    public static ArenaTimer getArenaTimer() {
        return arenaTimer;
    }

    public ArenaTimer(GameSurvival plugin, Arena arena) {
        arenaTimer = this;
        this.plugin = plugin;
        this.arena = arena;


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
        gameManager = new GameManager(ArenaState.STOP, plugin);
    }
}
