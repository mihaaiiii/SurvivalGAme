package ro.mihaaiiii.gamesurvival.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class CountDown extends BukkitRunnable {
    private static CountDown countDown;
    private static GameSurvival plugin;

    private GameManager gameManager;

    int contdow = 16;


    public CountDown(GameSurvival plugin) {
        this.plugin = plugin;

    }

    public void start() {
        gameManager = GameManager.getInstance(plugin);
        gameManager.getArenaManager().chestFill();
        runTaskTimer(plugin, 0, 20);
    }

    public static CountDown getContdown() {
        return new CountDown(plugin);
    }

    @Override
    public void run() {

        if (contdow == 0) {
            if (this != null) {
                cancel();
            }
            WorldBorder worldBorder = Bukkit.getServer().getWorld("test").getWorldBorder();
            worldBorder.setSize(60);
            worldBorder.setCenter(new Location(Bukkit.getServer().getWorld("test"), -527, 72, 32));
            worldBorder.setSize(3, 60);
            worldBorder.setWarningDistance(40);
            worldBorder.setDamageAmount(0.2);
            gameManager.getArenaManager().teleportPlayersToArena(); // trebuie sa teleportez jucatorii inainte de a se opri timerul
            gameManager.getArenaManager().getArena().sendMessage(ChatColor.RED + "teleport Player");
            if (!gameManager.getArenaManager().isFull()) {
                start();
            }
            gameManager.getArenaManager().getArena().setArenaState(ArenaState.START);
            gameManager.gameState(gameManager.getArenaManager().getArena().getArenaState());

        }

        if (contdow <= 10 || contdow % 15 == 0) {

            gameManager.getArenaManager().getArena().sendMessage(ChatColor.RED + "Game start in " + contdow + " " + (contdow == 1 ? "" : " s") + ".");
        }

        contdow--;
    }
}
