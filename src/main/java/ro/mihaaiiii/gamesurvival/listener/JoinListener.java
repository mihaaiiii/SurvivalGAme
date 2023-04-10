package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class JoinListener implements Listener {

    private GameSurvival plugin;


    public JoinListener(GameSurvival plugin) {


        this.plugin = plugin;


    }


    @EventHandler
    public void playerInteractEvent(PlayerDeathEvent event) {
        Player p = event.getPlayer();


    }


}
