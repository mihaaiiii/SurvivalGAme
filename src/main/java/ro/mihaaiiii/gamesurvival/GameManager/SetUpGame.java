package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.Game.Arena;
import ro.mihaaiiii.gamesurvival.Game.LootChest;
import ro.mihaaiiii.gamesurvival.GameSurvival;

@Getter
public class SetUpGame {
    private Arena arena;
    private LootChest common;
    private LootChest epic;

    private static SetUpGame setUpGameInstance;

    private SetUpGame(GameSurvival plugin) {
        arena = new Arena();
        arena.setMaxPlayers(plugin.getConfig().getInt("max_player_arena"));

        System.out.println("AICI ESTE SETUPGAME");
    }


    public boolean addPlayersToArena(Player player) {
        //Trebuie verificat daca numarul de jucatori din lista este egal cu numarul maxim de jucatori
        if (!isFull()) {
            return arena.getPlayers().add(player);
        } else {
            player.sendMessage("Arena is full");
            player.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
            return arena.getPlayers().remove(player);
        }
    }

    public void removePlayersToArena(Player player) {
        arena.getPlayers().remove(player);
    }


    public static SetUpGame getInstance(GameSurvival plugin) {
        return setUpGameInstance == null ? setUpGameInstance = new SetUpGame(plugin) : setUpGameInstance;
    }

    public boolean isFull() {
        System.out.println(arena.getMaxPlayers());
        System.out.println(arena.getPlayers().size());
        if (arena.getMaxPlayers() == 0) {
            System.out.println("Max player can be 0");
            return false;
        } else {
            System.out.println("Arena is full");
            return arena.getPlayers().size() - 1 >= arena.getMaxPlayers();
        }


    }
}
