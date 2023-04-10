package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.Game.GameManager;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.ArenaState;
import ro.mihaaiiii.gamesurvival.utils.MessageUtils;

public class JoinArenaCommand implements CommandExecutor {

    private GameSurvival plugin;
    private ArenaManager arenaManager;
    private ArenaState arenaState;
    GameManager gameManager;

    public JoinArenaCommand(GameSurvival plugin) {
        this.plugin = plugin;
        gameManager = new GameManager(plugin);
        arenaManager = gameManager.getArenaManager();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;

            if (!arenaManager.isFull()) {
                arenaManager.getArena().getPlayers().add(player.getUniqueId().toString());

                player.sendMessage(MessageUtils.message("&2You are join in the arena"));
                return true;
            } else {
                player.sendMessage(MessageUtils.message("&cArena is full"));
                if (arenaManager.getArena().getArenaState() != ArenaState.START) {
                    player.sendMessage(MessageUtils.message("&2The state area is start"));
                    return true;
                }

            }

        }
        return false;
    }
}
