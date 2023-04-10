package ro.mihaaiiii.gamesurvival.utils.register.registerCommands;

import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.command.*;
import ro.mihaaiiii.gamesurvival.utils.register.Registration;

public class RegisterCommands implements Registration {
    private GameSurvival plugin;

    public RegisterCommands(GameSurvival plugin) {
        this.plugin = plugin;
        register(plugin);
    }


    @Override
    public void register(GameSurvival plugin) {
        plugin.getCommand("join").setExecutor(new JoinArenaCommand(plugin));
        plugin.getCommand("common_chest").setExecutor(new SetCommonChest(plugin));
        plugin.getCommand("set_spawn").setExecutor(new SetSpawn(plugin));
        plugin.getCommand("setPspawn").setExecutor(new SetSpawnInArena(plugin));
        plugin.getCommand("setNpc").setExecutor(new SpawnNpcArenaJoin(plugin));
    }
}
