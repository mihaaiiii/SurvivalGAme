package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

public class SetCommonChest implements CommandExecutor {

    private final GameSurvival plugin;
    int count = 0;

    public SetCommonChest(GameSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length < 1 || args.length > 2) {

            player.sendMessage(ChatColor.RED + " Usage /common_chest <arena name> <type of chest>");
            return false;
        }
        DefaultConfig.getConfig().getConfigurationSection("arenas").getKeys(false).forEach(arena -> {
            if (!args[0].equals(arena)) {
                player.sendMessage(ChatColor.RED + "Arena name is not in config");
            } else {
                switch (args[1]) {
                    case "rare" -> {
                        // Trebuie rezolvat count;
                        plugin.getConfig().set("arenas." + arena + ".rare_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                        plugin.getConfig().set("arenas." + arena + ".rare_chest.location." + count + ".x", player.getLocation().getBlockX());
                        plugin.getConfig().set("arenas." + arena + ".rare_chest.location." + count + ".y", player.getLocation().getBlockY());
                        plugin.getConfig().set("arenas." + arena + ".rare_chest.location." + count + ".z", player.getLocation().getBlockZ());
                        count++;
                        player.sendMessage(ChatColor.DARK_AQUA + "Ai setat rare");

                        plugin.saveConfig();
                    }
                    case "epic" -> {
                        plugin.getConfig().set("arenas." + arena + ".epic_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                        plugin.getConfig().set("arenas." + arena + ".epic_chest.location." + count + ".x", player.getLocation().getBlockX());
                        plugin.getConfig().set("arenas." + arena + ".epic_chest.location." + count + ".y", player.getLocation().getBlockY());
                        plugin.getConfig().set("arenas." + arena + ".epic_chest.location." + count + ".z", player.getLocation().getBlockZ());
                        count++;
                        player.sendMessage(ChatColor.DARK_AQUA + "Ai setat epic");

                        plugin.saveConfig();
                    }

                    case "legendary" -> {
                        plugin.getConfig().set("arenas." + arena + ".legendary_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                        plugin.getConfig().set("arenas." + arena + ".legendary_chest.location." + count + ".x", player.getLocation().getBlockX());
                        plugin.getConfig().set("arenas." + arena + ".legendary_chest.location." + count + ".y", player.getLocation().getBlockY());
                        plugin.getConfig().set("arenas." + arena + ".legendary_chest.location." + count + ".z", player.getLocation().getBlockZ());
                        count++;
                        player.sendMessage(ChatColor.DARK_AQUA + "Ai setat legendary");
                        plugin.saveConfig();
                    }
                    default -> {
                        plugin.getConfig().set("arenas." + arena + ".common_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                        plugin.getConfig().set("arenas." + arena + ".common_chest.location." + count + ".x", player.getLocation().getBlockX());
                        plugin.getConfig().set("arenas." + arena + ".common_chest.location." + count + ".y", player.getLocation().getBlockY());
                        plugin.getConfig().set("arenas." + arena + ".common_chest.location." + count + ".z", player.getLocation().getBlockZ());
                        count++;
                        player.sendMessage(ChatColor.DARK_AQUA + "Ai setat common");
                        plugin.saveConfig();
                    }

                }
            }
        });

        return false;
    }

}
