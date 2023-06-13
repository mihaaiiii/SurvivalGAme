package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractWhitnpc implements Listener {




    public InteractWhitnpc() {

    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Bukkit.getServer().dispatchCommand(player, "join");
    }

}

