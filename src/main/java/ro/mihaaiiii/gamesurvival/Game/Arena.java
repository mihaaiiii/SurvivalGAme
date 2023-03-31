package ro.mihaaiiii.gamesurvival.Game;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

@Data
public class Arena {

    private Long idArena; //id-ul arenei
    private Map<String, Location> locations; // locatia pe harta a jucatorilor
    private Map<Location, LootChest> chests;
    private List<Location> locationSpawn;
    private Set<Player> players;
    private int maxPlayers;
    private ArenaState arenaState;

    public Arena() {
        chests = new HashMap<>();
        players = new HashSet<>();
        locations = new HashMap<>();
        maxPlayers = 2;
        arenaState = ArenaState.WAITING;
        System.out.println("AICI ESTE ARENA");

    }


}
