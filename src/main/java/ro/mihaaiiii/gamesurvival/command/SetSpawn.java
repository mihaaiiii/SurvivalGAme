package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.util.Objects;

public class SetSpawn implements CommandExecutor {
    private final GameSurvival plugin;

    public SetSpawn(GameSurvival plugin) {
        this.plugin = plugin;

        Objects.requireNonNull(plugin.getCommand("set_spawn")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        plugin.saveDefaultConfig();
        plugin.getConfig().set("lobyArena.world", player.getWorld().getName());
        plugin.getConfig().set("lobyArena.x", player.getLocation().getBlockX());
        plugin.getConfig().set("lobyArena.y", player.getLocation().getBlockY());
        plugin.getConfig().set("lobyArenan.z", player.getLocation().getBlockZ());
        plugin.getConfig().set("lobyArena.pitch", player.getLocation().getPitch());
        plugin.getConfig().set("lobyArena.yaw", player.getLocation().getYaw());
        plugin.saveConfig();
        player.sendMessage("The loby spawn in arena has setted");

        return true;
    }

}
