package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;
import ro.mihaaiiii.gamesurvival.model.ChestType;
import ro.mihaaiiii.gamesurvival.model.LootChest;

@Getter
public class ArenaManager {
    private LootChest common;
    private Arena arena;
    private ChestFactory chestFactory;
    private int count = 0;
    private GameSurvival plugin;
    private CountDown countDown;
    private ArenaTimer timer;

    private static ArenaManager arenaInstance;

    private ArenaManager(GameSurvival plugin) {
        this.plugin = plugin;
        common = new LootChest(plugin);
        arena = new Arena();
        chestFactory = new ChestFactory(ChestType.COMMON, plugin);
        this.countDown = CountDown.getContdown();
        arena.setMaxPlayers(plugin.getConfig().getInt("max_player_arena"));


    }


    public boolean isFull() {
        return arena.getPlayers().size() == arena.getMaxPlayers();
    }

    public void startCountdown() {
        countDown = new CountDown(plugin, arena);
        try {
            if (isFull() || isWaitingOrIsStop()) {
                countDown.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTimer() {
        timer = new ArenaTimer(plugin, arena);
        timer.start();
    }

    public boolean isWaitingOrIsStop() {
        return arena.getArenaState() == ArenaState.STOP || arena.getArenaState() == ArenaState.WAITING;
    }

    public boolean isWaitingOrIsStopOrIsStarted() {
        return arena.getArenaState() == ArenaState.STOP || arena.getArenaState() == ArenaState.WAITING || arena.getArenaState() == ArenaState.START;
    }


    public void chestFill() {
        chestFactory.addItemsInChest();
    }

    public void teleportPlayersToArena() {
        for (int i = 0; i < arena.arenaPlayer().size(); i++) {
            arena.arenaPlayer().get(i).teleport(DefaultConfig.getArenaSpaws().get(i));
        }
    }


    public static ArenaManager getInstance(GameSurvival plugin) {
        return arenaInstance == null ? arenaInstance = new ArenaManager(plugin) : arenaInstance;
    }

}
