package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

public class SetSpawn implements CommandExecutor {
    private DefaultConfig defaultConfig;
    private GameSurvival plugin;

    public SetSpawn(GameSurvival plugin) {
        this.plugin = plugin;

        plugin.getCommand("set_spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        plugin.getConfig().set("default_spawn.world", player.getWorld().getName());
        plugin.getConfig().set("default_spawn.x", player.getLocation().getX());
        plugin.getConfig().set("default_spawn.y", player.getLocation().getY());
        plugin.getConfig().set("default_spawn.z", player.getLocation().getZ());
        plugin.getConfig().set("default_spawn.pitch", player.getLocation().getPitch());
        plugin.getConfig().set("default_spawn.yaw", player.getLocation().getYaw());
        plugin.saveConfig();
        player.sendMessage("the spawh has ben set");

        return true;
    }

}
