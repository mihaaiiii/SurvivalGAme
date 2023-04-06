package ro.mihaaiiii.gamesurvival.model;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.GameManager.CountDown;
import ro.mihaaiiii.gamesurvival.GameManager.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Arena {

    private int idArena;
    private List<LootChest> chests;
    private List<Location> locationSpawn;
    private Set<String> players;
    private int maxPlayers;
    private ArenaState arenaState;
    private Game game;
    private CountDown countDown;

    public Arena() {
        idArena = 1;
        chests = new ArrayList<>();
        players = new HashSet<>();

    }

    public void removePlayerFromArena() {
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


    @Override
    public String toString() {
        return "Arena{" +
                "idArena=" + idArena +
                ", chests=" + chests +
                ", locationSpawn=" + locationSpawn +
                ", players=" + players +
                ", maxPlayers=" + maxPlayers +
                ", arenaState=" + arenaState +
                '}';
    }
}
