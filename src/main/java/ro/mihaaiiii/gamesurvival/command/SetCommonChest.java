package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.util.List;

public class SetCommonChest implements CommandExecutor {

    private GameSurvival plugin;
    int count = 0;

    public SetCommonChest(GameSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (args.length < 1 || args.length > 1) {
            System.out.println(ChatColor.GREEN + "Comanda necunoscuta");
            return false;
        }
        switch (args[0]) {
            case "rare" -> {
                // Trebuie rezolvat count;
                plugin.getConfig().set("rare_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                plugin.getConfig().set("rare_chest.location." + count + ".x", player.getLocation().getBlockX());
                plugin.getConfig().set("rare_chest.location." + count + ".y", player.getLocation().getBlockY());
                plugin.getConfig().set("rare_chest.location." + count + ".z", player.getLocation().getBlockZ());
                count++;
                player.sendMessage(ChatColor.DARK_AQUA + "Ai setat rare");

                plugin.saveConfig();
                return true;
            }
            case "epic" -> {
                plugin.getConfig().set("epic_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                plugin.getConfig().set("epic_chest.location." + count + ".x", player.getLocation().getBlockX());
                plugin.getConfig().set("epic_chest.location." + count + ".y", player.getLocation().getBlockY());
                plugin.getConfig().set("epic_chest.location." + count + ".z", player.getLocation().getBlockZ());
                count++;
                player.sendMessage(ChatColor.DARK_AQUA + "Ai setat epic");

                plugin.saveConfig();
                return true;
            }

            case "legendary" -> {
                plugin.getConfig().set("legendary_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                plugin.getConfig().set("legendary_chest.location." + count + ".x", player.getLocation().getBlockX());
                plugin.getConfig().set("legendary_chest.location." + count + ".y", player.getLocation().getBlockY());
                plugin.getConfig().set("legendary_chest.location." + count + ".z", player.getLocation().getBlockZ());
                count++;
                player.sendMessage(ChatColor.DARK_AQUA + "Ai setat legendary");
                plugin.saveConfig();
                return true;
            }
            default -> {
                plugin.getConfig().set("common_chest.location." + count + ".world", player.getLocation().getWorld().getName());
                plugin.getConfig().set("common_chest.location." + count + ".x", player.getLocation().getBlockX());
                plugin.getConfig().set("common_chest.location." + count + ".y", player.getLocation().getBlockY());
                plugin.getConfig().set("common_chest.location." + count + ".z", player.getLocation().getBlockZ());
                count++;
                player.sendMessage(ChatColor.DARK_AQUA + "Ai setat common");
                plugin.saveConfig();
                return true;
            }

        }

    }


    private ItemStack isArmour(ItemStack item, String name) {
        List<String> names = List.of("sword");
        System.out.println(item.getType().name().toUpperCase());
        for (String nam : names) {
            if (item.getType().name().contains(nam.toUpperCase())) {
                item = new ItemStack(Material.matchMaterial(name), 1);
            }
        }
        return item;
    }
}
