package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class SetCommonChest implements CommandExecutor {

    private GameSurvival plugin;

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
        if (player.getLocation().getBlock().getType() == Material.CHEST) {
//
        } else {
            player.sendMessage("Trebuie sa stai pe un chest!");
        }
        //    plugin.getConfig().getItemStack("common_chest.items");
        return true;
    }
}
