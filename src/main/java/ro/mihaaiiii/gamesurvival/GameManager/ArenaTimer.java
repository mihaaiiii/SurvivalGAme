package ro.mihaaiiii.gamesurvival.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;

public class ArenaTimer extends BukkitRunnable {
    int contdow = 59;
    private GameManager gameManager;


    public ArenaTimer() {


    }

    public void start(GameSurvival plugin) {
        gameManager = GameManager.getInstance(plugin);
        runTaskLater(plugin, contdow * 20);
    }

    @Override
    public void run() {
        if (this != null) {
            cancel();
        }

        Bukkit.getWorld("test").getWorldBorder().setSize(60);


    }
}
