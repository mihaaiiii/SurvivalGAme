package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.event.Listener;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class EventClickChest implements Listener {

    private GameSurvival plugin;

    public EventClickChest(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
