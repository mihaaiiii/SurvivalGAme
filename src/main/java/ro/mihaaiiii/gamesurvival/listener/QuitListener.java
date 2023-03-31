package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class QuitListener implements Listener {

    private GameSurvival plugin;
    SetUpGame set = SetUpGame.getInstance(plugin);

    public QuitListener(GameSurvival plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        set.removePlayersToArena(player);
        player.sendMessage(set.getArena().getPlayers().toString());
    }
}
