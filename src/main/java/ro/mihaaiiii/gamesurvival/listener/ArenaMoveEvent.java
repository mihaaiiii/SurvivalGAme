package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class ArenaMoveEvent implements Listener {
    private GameSurvival plugin;


    public ArenaMoveEvent(GameSurvival plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void arenaMovePlayer(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (GameManager.getInstance(plugin).getArenaManager().getArena().arenaPlayer().contains(player) && GameManager.getInstance(plugin).getArenaManager().getArena().getArenaState() == ArenaState.WAITING) {
            if (event.hasChangedOrientation() || !event.hasExplicitlyChangedPosition() || !event.hasExplicitlyChangedBlock()) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
            }
        }
    }


}
