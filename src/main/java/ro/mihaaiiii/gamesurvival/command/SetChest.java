package ro.mihaaiiii.gamesurvival.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class SetChest implements CommandExecutor {
    private GameSurvival plugin;
    private SetUpGame setUP = SetUpGame.getInstance(plugin);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        setUP.getCommon().getItems().forEach(itemStack -> player.sendMessage(itemStack.toString()));


        return true;
    }
}
