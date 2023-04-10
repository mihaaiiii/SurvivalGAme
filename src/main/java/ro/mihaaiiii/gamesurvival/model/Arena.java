package ro.mihaaiiii.gamesurvival.model;

import lombok.Data;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class Arena {

    private String nameArena;
    private Set<String> players;
    private List<Location> spawnPlayer;
    private int maxPlayers;
    private ArenaState arenaState;
    private static Arena instance;
    @Setter
    private boolean isStarted;

    public Arena(String nameArena, boolean isStarted, int maxPlayers, Set<String> players, ArenaState arenaState, List<Location> spawnPlayer) {
        this.nameArena = nameArena;
        this.isStarted = isStarted;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.arenaState = arenaState;
        this.spawnPlayer = spawnPlayer;


    }

    public void removePlayerFromArena() {
        System.out.println(ChatColor.BLUE + " s-au dus din arena");
        players.clear();
        arenaPlayer().clear();
    }


    public List<Player> arenaPlayer() {

        List<Player> gamer = new ArrayList<>();
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            if (players.contains(player.getUniqueId().toString())) {
                gamer.add(player);
            }
        });
        return gamer;
    }


    public void sendMessage(String message) {
        for (Player uuid : Bukkit.getServer().getOnlinePlayers()) {
            uuid.sendMessage(message);
        }

    }

    public void sendTitle(String message, String sub) {
        for (Player uuid : Bukkit.getServer().getOnlinePlayers()) {
            uuid.sendTitle(message, sub);
        }


    }

    public static Arena getInstance(String nameArena, boolean isStarted, int maxPlayers, Set<String> players, ArenaState arenaState, List<Location> spawnPlayer) {
        return instance == null ? instance = new Arena(nameArena, isStarted, maxPlayers, players, arenaState, spawnPlayer) : instance;
    }

}
