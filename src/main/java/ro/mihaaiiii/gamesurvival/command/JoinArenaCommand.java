package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

public class JoinArenaCommand implements CommandExecutor {

    private final ArenaManager arenaManager;
    GameManager gameManager;

    public JoinArenaCommand(GameSurvival plugin) {
        gameManager = GameManager.getInstance(plugin);
        arenaManager = gameManager.getArenaManager();
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if ((sender instanceof Player player)) {


            player.teleport(DefaultConfig.getArenaSpaws(arenaManager.getArena().getNameArena()).get(0));


        }
        return false;
    }
}



