package ro.mihaaiiii.gamesurvival.listener;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.model.PlayerStatus;
import ro.mihaaiiii.gamesurvival.model.PlayerStatusBuilder;


public class ArenaPlayerDeathEvents implements Listener {
    GameSurvival plugin;

    public ArenaPlayerDeathEvents(GameSurvival plugin) {
        this.plugin = plugin;

    }


    @EventHandler
    public void ArenaDeathEvent(PlayerDeathEvent event) {

        Player player = event.getPlayer();
        Player killer = player.getKiller();
        if(player.isFlying())
        {return;}
        PlayerStatus playerStatus = new PlayerStatusBuilder().
                getOwner(player.getUniqueId().toString()).
                getWins(plugin.getDataBasesFactory().getRepository().getWins(player.getUniqueId().toString()) + 2).
                getLoss(1).
                getKills(player.getStatistic(Statistic.PLAYER_KILLS)).
                getdDeaths(player.getStatistic(Statistic.DEATHS)).
                build();
        plugin.getDataBasesFactory().getRepository().insertPlayer(playerStatus.getOwner(), playerStatus.getWins(), playerStatus.getLoss(), playerStatus.getKills(), playerStatus.getDeaths());

    }


}
