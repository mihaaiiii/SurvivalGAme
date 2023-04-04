package ro.mihaaiiii.gamesurvival.GameManager;

import lombok.Getter;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ro.mihaaiiii.gamesurvival.Game.Arena;
import ro.mihaaiiii.gamesurvival.Game.ArenaState;
import ro.mihaaiiii.gamesurvival.Game.ChestType;
import ro.mihaaiiii.gamesurvival.Game.LootChest;
import ro.mihaaiiii.gamesurvival.GameManager.chestSetUp.ChestFactory;
import ro.mihaaiiii.gamesurvival.GameSurvival;

@Getter
public class SetUpGame {
    private Arena arena;
    private LootChest common;
    private LootChest epic;
    private ChestFactory chestFactory;

    private static SetUpGame setUpGameInstance;

    private SetUpGame(GameSurvival plugin) {
        chestFactory = new ChestFactory(ChestType.COMMON, plugin);
        arena = new Arena();
        int arenaID = arena.getIdArena();
        arena.setMaxPlayers(plugin.getConfig().getInt("max_player_arena"));
        arena.setIdArena(arenaID);
        System.out.println(arena);
        startArena(plugin);

        //Adauga jucatori in arena
        //porneste
    }

    private boolean startArena(GameSurvival plugin) {
        if (arena.getArenaState() == ArenaState.WAITING && !isFull()) {
            // pregateste mapa
            //start timer
            if (!arena.getPlayers().isEmpty()) {
                for (int i = 0; i < arena.getPlayers().size(); i++) {
                    arena.getPlayers().get(i).teleport(plugin.getConfig().getLocation("spawnPlayer." + i + ""));
                    //teleporteaza jucatorii la locatii
                }
            }
            return true;

        } else {
            System.out.println("Arena a inceput");
            return false;
        }

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

    private void chestFill(GameSurvival plugin) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                chestFactory.addItemsInChest();
                sendMes("-> Timerul s-a executat cu succes, toate itemele au fost pregatite");
            }, 80l);
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

    private void sendMes(String mess) {
        Bukkit.getServer().broadcast(new TextComponent(mess));
    }

}
