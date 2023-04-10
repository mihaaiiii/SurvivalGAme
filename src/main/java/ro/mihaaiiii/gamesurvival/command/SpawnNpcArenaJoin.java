package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

public class SpawnNpcArenaJoin implements CommandExecutor {
    private GameSurvival plugin;
    private Villager villager;

    public SpawnNpcArenaJoin(GameSurvival plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("setNpc")) {
                if (args.length == 0) {


                    villager = (Villager) plugin.getServer().getWorld("world").spawnEntity(DefaultConfig.getNpcSpanw(), EntityType.VILLAGER);
                    villager.setAI(false);
                    villager.setCollidable(false);
                    villager.setInvulnerable(true);
                    villager.setCustomNameVisible(true);
                    villager.setCustomName(ChatColor.RED + "Click to Join in area");
                    villager.setAdult();
                    villager.setAgeLock(true);

                    villager.setProfession(Villager.Profession.NONE);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + " try /setnpc");
                }
            }
        }
        return false;
    }
}
