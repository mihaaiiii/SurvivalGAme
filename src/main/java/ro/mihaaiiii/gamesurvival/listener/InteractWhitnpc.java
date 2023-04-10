package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

public class InteractWhitnpc implements Listener {

    private GameSurvival plugin;
    private GameManager gameManager;
    private ArenaManager arenaManager;
    private Arena arena;

    public InteractWhitnpc(GameSurvival plugin) {
        this.plugin = plugin;
        gameManager = GameManager.getInstance(plugin);
        arenaManager = gameManager.getArenaManager();

    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        player.sendMessage(ChatColor.RED + " as");
        if (entity.getType() == EntityType.VILLAGER) {
            if (!arenaManager.getArena().getPlayers().contains(player.getUniqueId().toString()) && arenaManager.getArena().getArenaState() == ArenaState.OWO && !arenaManager.getArena().isStarted() && !arenaManager.isFull()) {
                player.sendMessage(ChatColor.RED + " Ai intrat in arena");
                arenaManager.getArena().getPlayers().add(player.getUniqueId().toString());
                gameManager.gameState(ArenaState.WAITING);
                event.setCancelled(true);
            } else {
                if (arenaManager.isFull()) {
                    //  gameManager = new GameManager(plugin);
                    player.sendMessage(ChatColor.RED + " Arena e full");

                }
            }

        }

    }
}
