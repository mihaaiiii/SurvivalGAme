package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import ro.mihaaiiii.gamesurvival.GameSurvival;

public class JoinListener implements Listener {

    private GameSurvival plugin;


    public JoinListener(GameSurvival plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


//    @EventHandler
//    public void playerJoin(PlayerJoinEvent event) {
////        Player player = event.getPlayer();
////        TextComponent textComponent = new TextComponent(MessageUtils.message(" &9Click here to join in arena"));
////        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/addToArena"));
////        player.spigot().sendMessage(textComponent);
////        player.sendMessage(MessageUtils.message(ArenaManager.getInstance(plugin).getArena().getPlayers() + " "));
//
//    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEntityEvent event) {
        System.out.println(ChatColor.GREEN + " asa dw");


    }


}
