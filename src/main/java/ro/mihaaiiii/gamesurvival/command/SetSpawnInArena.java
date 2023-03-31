package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class SetSpawnInArena implements CommandExecutor {
    private GameSurvival plugin;
    private SetUpGame setUpGame = SetUpGame.getInstance(plugin);

    public SetSpawnInArena(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getCommand("first_spawn").setExecutor(this);
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
        if (args.length == 0 || args.length < 1) {
            return false;
        }
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        String world = player.getLocation().getWorld().getName();
        plugin.getConfig().set("spawn1.x", x);
        plugin.getConfig().set("spawn1.y", x);
        plugin.getConfig().set("spawn1.z", x);
        plugin.getConfig().set("spawn1.world", world);
        plugin.saveConfig();
        System.out.println("set first spawn");
        return true;

    }
}
