package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.model.Arena;
import ro.mihaaiiii.gamesurvival.model.ArenaState;

import java.util.HashSet;


public class ArenaManager {

    @Getter
    private Arena arena;
    @Getter
    private ChestFactory common;
    private ChestFactory rare;
    private ChestFactory epic;
    private ChestFactory legendary;
    @Getter
    private int count = 0;
    private GameSurvival plugin;

    private CountDown countDown;


    private ArenaTimer timer;

    private static ArenaManager arenaInstance;

    public ArenaManager(GameSurvival plugin) {
        this.plugin = plugin;
        arena = startArena("arena1");

        common = new ChestFactory(plugin);
//        rare = new ChestFactory(plugin);
//        epic = new ChestFactory(plugin);
//        legendary = new ChestFactory(plugin);

        this.countDown = CountDown.getContdown();
        arena.setMaxPlayers(plugin.getConfig().getInt("max_player_arena"));


    }

    public Arena startArena(String name) {

        return new Arena(name, false, 2, new HashSet<>(), ArenaState.OWO, DefaultConfig.getArenaSpaws(name));
    }


    public boolean isFull() {
        return arena.getPlayers().size() == arena.getMaxPlayers();
    }

    public void removePlayer(Player player) {

        arena.arenaPlayer().remove(player);
        arena.arenaPlayer().remove(player.getUniqueId().toString());
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
        timer = new ArenaTimer(plugin);
        timer.start();
    }

    public boolean isWaitingOrIsStop() {
        return arena.getArenaState() == ArenaState.STOP || arena.getArenaState() == ArenaState.WAITING;
    }

    public boolean isWaitingOrIsStopOrIsStarted() {
        return arena.getArenaState() == ArenaState.STOP || arena.getArenaState() == ArenaState.WAITING || arena.getArenaState() == ArenaState.START;
    }


    public void chestFill() {
        System.out.println(ChatColor.BLUE + "merge");

        common.addItemsInChest("common");
//        epic.addItemsInChest("epic");
//        rare.addItemsInChest("rare");
//        legendary.addItemsInChest("legendary");

    }

    public void teleportPlayersToArena() {
        if (arena.isStarted()) {
            for (int i = 0; i < arena.arenaPlayer().size(); i++) {
                arena.arenaPlayer().get(i).teleport(arena.getSpawnPlayer().get(i));
            }
        }
    }

    public void teleportPlayersToLobbyArena() {
        if (arena.isStarted()) {
            for (int i = 0; i < arena.arenaPlayer().size(); i++) {
                arena.arenaPlayer().get(i).teleport(DefaultConfig.getLobby());
            }
        }
    }

    public void teleportToSpawn() {
        for (int i = 0; i < arena.arenaPlayer().size(); i++) {
            arena.arenaPlayer().get(i).teleport(plugin.getServer().getWorld("world").getSpawnLocation());
        }
    }


    public static ArenaManager getInstance(GameSurvival plugin) {
        return arenaInstance == null ? arenaInstance = new ArenaManager(plugin) : arenaInstance;
    }

}
