package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

import java.util.Map;
import java.util.Objects;


public class ArenaManager {

    @Getter
    private Arena arena;
    @Getter
    private final ChestFactory common;
    private final ChestFactory rare;
    private final ChestFactory epic;
    private final ChestFactory legendary;
    @Getter
    private final int count = 0;
    private final GameSurvival plugin;
    @Getter
    private CountDown countDown;
    @Getter
    private ArenaTimer timer;


    public ArenaManager() {
        plugin = GameSurvival.getPlugin(GameSurvival.class);
        String nameAr = "arena1";
        common = new ChestFactory();
        rare = new ChestFactory();
        epic = new ChestFactory();
        legendary = new ChestFactory();
        timer = new ArenaTimer();
        countDown = new CountDown(plugin);

        Map<String, Arena> arenaMap = DefaultConfig.fllWhitArena();
        if (arenaMap.containsKey(nameAr)) {
            arena = arenaMap.get(nameAr);
        }


    }


    public boolean isFull() {
        return arena.getPlayers().size() == arena.getMaxPlayers();
    }

    public void removePlayerFromArena() {
        System.out.println(ChatColor.BLUE + "Arena is cleaning");
        arena.getPlayers().clear();
        arena.arenaPlayer().clear();
    }

    public void startCountdown() {
        countDown = new CountDown(plugin);
        try {
            if (isFull() || isWaitingOrIsStop()) {
                countDown.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTimer() {
        timer = new ArenaTimer();
        timer.start(plugin);
    }

    public boolean isWaitingOrIsStop() {
        return arena.getArenaState() == ArenaState.STOP || arena.getArenaState() == ArenaState.WAITING;
    }


    public void chestFill() {
        common.addItemsInChest("common");
        epic.addItemsInChest("epic");
        rare.addItemsInChest("rare");
        legendary.addItemsInChest("legendary");
        System.out.println(ChatColor.BLUE + "All chest items is filled ");
    }

    public void teleportPlayersToArena() {
        System.out.println(arena.arenaPlayer().size());
        if (arena.isStarted()) {
            for (int i = 0; i < arena.arenaPlayer().size(); i++) {
                arena.arenaPlayer().get(i).teleport(arena.getSpawnPlayer().get(i));
            }
        }
    }


    public void teleportToSpawn() {
        for (int i = 0; i < arena.arenaPlayer().size(); i++) {
            arena.arenaPlayer().get(i).teleport(Objects.requireNonNull(plugin.getServer().getWorld("world")).getSpawnLocation());
        }
    }


}
