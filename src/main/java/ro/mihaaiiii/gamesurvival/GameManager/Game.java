package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import ro.mihaaiiii.gamesurvival.Game.ArenaState;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.awt.*;

@Getter
public class Game {
    private GameSurvival plugin;
    private SetUpGame setUpGame;
    private TimerType timerType;

    // teleporteaza jucatorii la locatiile stabilite
    // stabileste un tip pentru loot apoi fa ca o bariera sa se miste


    public Game(GameSurvival plugin) {
        this.plugin = plugin;
        setUpGame = SetUpGame.getInstance(plugin);
        setUpGame.getArena().setArenaState(ArenaState.WAITING);
        timerType = TimerType.SECONDS;
        System.out.println("GCE SE PETRECE AICI");
        if (setUpGame.isFull() && setUpGame.getArena().getArenaState() == ArenaState.WAITING) {
            start();
            System.out.println("The game is started" + this.toString());
        }


        System.out.println("AICI ESTE GAME");
    }

    private void start() {

        setUpGame.getArena().setArenaState(ArenaState.START);
        System.out.println("game is started");

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println(Color.CYAN + "Arena is started");
                setUpGame.getArena().setArenaState(ArenaState.WAITING);
                setUpGame.getArena().getPlayers().clear();
                System.out.println(Color.red + setUpGame.getArena().getPlayers().toString());

            }
        }, delay());

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
