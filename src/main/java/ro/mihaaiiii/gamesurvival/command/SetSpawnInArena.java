package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class SetSpawnInArena implements CommandExecutor {
    int count = 1;
    private GameSurvival plugin;

    public SetSpawnInArena(GameSurvival plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (!player.isOp()) {
            return false;
        }
        double x = player.getLocation().getBlockX();
        double y = player.getLocation().getBlockY();
        double z = player.getLocation().getBlockZ();
        double pitch = player.getLocation().getPitch();
        double yaw = player.getLocation().getYaw();
        String world = player.getLocation().getWorld().getName();
        if (args.length == 1) {
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".world", world);
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".x", x);
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".y", y);
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".z", z);
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".pitch", pitch);
            plugin.getConfig().set("arenas." + args[0] + ".spawn_location." + count + ".yaw", yaw);
            plugin.saveConfig();
            count++;
            System.out.println("The spawn whit number " + count + " has ben set!");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "please use /setparena <nameArena>");
        }
        return false;
    }
}
