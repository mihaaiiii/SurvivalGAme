package ro.mihaaiiii.gamesurvival.Game;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Arena {

    private int idArena;
    private List<LootChest> chests;
    private List<Location> locationSpawn;
    private List<Player> players;
    private int maxPlayers;
    private ArenaState arenaState;

    public Arena() {
        idArena = 1;
        chests = new ArrayList<>();
        players = new ArrayList<>();
        arenaState = ArenaState.WAITING;
        System.out.println("AICI ESTE ARENA");

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
