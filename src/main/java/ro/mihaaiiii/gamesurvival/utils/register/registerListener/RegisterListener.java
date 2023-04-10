package ro.mihaaiiii.gamesurvival.utils.register.registerListener;

import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.listener.*;
import ro.mihaaiiii.gamesurvival.utils.register.Registration;

public class RegisterListener implements Registration {
    private GameSurvival plugin;

    public RegisterListener(GameSurvival plugin) {
        this.plugin = plugin;
        register(plugin);
    }


    @Override
    public void register(GameSurvival plugin) {
        plugin.getServer().getPluginManager().registerEvents(new InteractWhitnpc(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinListener(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new QuitListener(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ArenaPlayerDeathEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ArenaMoveEvent(plugin), plugin);

    }
}
