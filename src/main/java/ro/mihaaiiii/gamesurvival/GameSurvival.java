package ro.mihaaiiii.gamesurvival;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaManager;
import ro.mihaaiiii.gamesurvival.GameManager.ArenaTimer;
import ro.mihaaiiii.gamesurvival.GameManager.CountDown;
import ro.mihaaiiii.gamesurvival.GameManager.Game;
import ro.mihaaiiii.gamesurvival.command.JoinArenaCommand;
import ro.mihaaiiii.gamesurvival.command.SetCommonChest;
import ro.mihaaiiii.gamesurvival.command.SetSpawn;
import ro.mihaaiiii.gamesurvival.command.SetSpawnInArena;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.MariaDBConnection;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;
import ro.mihaaiiii.gamesurvival.listener.JoinListener;
import ro.mihaaiiii.gamesurvival.listener.QuitListener;
import ro.mihaaiiii.gamesurvival.model.Arena;

//TODO SA NU UITI DE MESAJE
public class GameSurvival extends JavaPlugin {
    private Game game;
    private MariaDBConnection db;
    @Getter
    private Villager villager;

    @Override
    public void onEnable() {
        DefaultConfig.setupConfig(this);
        World world = Bukkit.getServer().createWorld(new WorldCreator("test"));
        Bukkit.getWorld("Test").getWorldBorder().setSize(60);
        world.setAutoSave(true);
        new ArenaTimer(this, new Arena());
        new CountDown(this, new Arena());


        new SetCommonChest(this);
        new SetSpawnInArena(this);
        new SetSpawn(this);
        new JoinArenaCommand(this);
        new JoinListener(this);
        this.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        System.out.println(this.getConfig().getConfigurationSection("arenas.spawn_location").getKeys(false).size() + " SIZE DEFAULT CONFIG");
//        villager = (Villager) this.getServer().getWorld("test").spawnEntity(DefaultConfig.getNpcSpanw(), EntityType.VILLAGER);
//        villager.setAI(false);
//        villager.setCollidable(false);
//        villager.setInvulnerable(true);
//        villager.setCustomNameVisible(true);
//        villager.setCustomName(ChatColor.RED + "Click to Join in area");
//        villager.setAgeLock(true);
//        villager.setGliding(false);
//
//        villager.setProfession(Villager.Profession.NONE);
    }


    @Override
    public void onDisable() {
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        System.out.println(game.getArenaManager().getArena().getPlayers());
        return true;
    }


}
