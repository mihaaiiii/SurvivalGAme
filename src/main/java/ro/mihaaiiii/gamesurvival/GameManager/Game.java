package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

import java.awt.*;

@Getter
public class Game {
    private GameSurvival plugin;
    private ArenaManager arenaManager;
    private TimerType timerType;
    private Arena arena;

    // teleporteaza jucatorii la locatiile stabilite
    // stabileste un tip pentru loot apoi fa ca o bariera sa se miste


    public Game(Arena arena, GameSurvival plugin) {
        this.arena = arena;
        this.plugin = plugin;
        arenaManager = ArenaManager.getInstance(plugin);
        arena.setArenaState(ArenaState.WAITING);
        timerType = TimerType.valueOf(DefaultConfig.getTimerTipe());
        System.out.println("GCE SE PETRECE AICI");


        System.out.println("AICI ESTE GAME");
    }

    private void startArena() {

        DefaultConfig.getArenaSpaws().forEach(location ->
                arena.getPlayers().forEach(player -> {

                })
        );

        arena.setArenaState(ArenaState.START);
        System.out.println("game is started");

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println(Color.CYAN + "Arena is started");
                arena.setArenaState(ArenaState.WAITING);
                arena.getPlayers().clear();
                System.out.println(Color.red + arenaManager.getArena().getPlayers().toString());

            }
        }, delay());

    }

    private void stopArena() {
    }

    private Long delay() {
        timerType = TimerType.valueOf(plugin.getConfig().getString("timer_Type").toUpperCase());
        long seconds = plugin.getConfig().getLong("round_tine") * 20;
        long minutes = seconds * 60;
        switch (timerType) {
            case MINUTES:
                return minutes;
            default:
                return seconds;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "timerType=" + timerType +
                '}';
    }
}

enum TimerType {
    SECONDS, MINUTES;
}
