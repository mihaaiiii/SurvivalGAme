package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import ro.mihaaiiii.gamesurvival.GameManager.SetUpGame;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class JoinListener implements Listener {


    private GameSurvival plugin;
    SetUpGame game = SetUpGame.getInstance(plugin);

    public JoinListener(GameSurvival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        double x = plugin.getConfig().getDouble("default_spawn.x");
        double y = plugin.getConfig().getDouble("default_spawn.y");
        double z = plugin.getConfig().getDouble("default_spawn.z");
        String world = plugin.getConfig().getString("default_spawn.world");
        Player player = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld(world), x, y, z);

        game.addPlayersToArena(player);
        player.teleport(spawn);


        player.sendMessage(game.getArena().getPlayers().toString());
    }

    @EventHandler
    public void entity(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlock().getType() == Material.CHEST) {
            System.out.println("Chest");
        } else {
          //  System.out.println(event.getPlayer().getLocation().getBlock().getType());
        }
    }
}
