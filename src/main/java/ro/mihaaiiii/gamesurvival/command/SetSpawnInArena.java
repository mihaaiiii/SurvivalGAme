package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class SetSpawnInArena implements CommandExecutor {
    int count = 0;
    private GameSurvival plugin;
    private SetUpGame setUpGame = SetUpGame.getInstance(plugin);

    public SetSpawnInArena(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getCommand("setPspawn").setExecutor(this);
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
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        String world = player.getLocation().getWorld().getName();
        plugin.getConfig().set("spawnPlayer." + count + ".x", x);
        plugin.getConfig().set("spawnPlayer." + count + ".y", y);
        plugin.getConfig().set("spawnPlayer." + count + ".z", z);
        plugin.getConfig().set("spawnPlayer." + count + ".world", world);
        plugin.saveConfig();
        count++;
        System.out.println("The spawn whit number " + count + " has ben set!");
        return true;
    }
}
