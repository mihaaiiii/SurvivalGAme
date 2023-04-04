package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class JoinListener implements Listener {


    private GameSurvival plugin;
    SetUpGame game = SetUpGame.getInstance(plugin);

    public JoinListener(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (SetUpGame.getInstance(plugin).isFull()) {
            player.sendMessage("Arena is full");
        } else {
            int x = SetUpGame.getInstance(plugin).getArena().getPlayers().size();
            player.sendMessage("Sunt -> " + x + " Player in arena");
        }


    }
}
