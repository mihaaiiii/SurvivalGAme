package ro.mihaaiiii.gamesurvival.command;

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
    int count;

    public SetCommonChest(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getCommand("common_chest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        plugin.getConfig().set("common_chest.location." + count + ".world", player.getLocation().getWorld().getName());
        plugin.getConfig().set("common_chest.location." + count + ".x", player.getLocation().getBlockX());
        plugin.getConfig().set("common_chest.location." + count + ".y", player.getLocation().getBlockY());
        plugin.getConfig().set("common_chest.location." + count + ".z", player.getLocation().getBlockZ());
        plugin.saveConfig();
        count++;
        return true;
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
