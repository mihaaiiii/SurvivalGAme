package ro.mihaaiiii.gamesurvival.databases.commectionDB;

import org.bukkit.ChatColor;
import ro.mihaaiiii.gamesurvival.GameSurvival;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection {
    private GameSurvival plugin;
    private String url;
    private int port;
    private String dbName;
    private String host;
    private String userName;
    private String password;
    private Connection connection;


    public MariaDBConnection(GameSurvival plugin) {
        this.plugin = plugin;
        host = plugin.getConfig().getString("db_host");
        dbName = plugin.getConfig().getString("db_name");
        port = plugin.getConfig().getInt("db_port");
        url = "jdbc:mysql://" + host + "/" + dbName;
        userName = plugin.getConfig().getString("db_user");
        password = plugin.getConfig().getString("db_password");
        getConnection();
        System.out.println("AICI ESTE DBCONNECTION");

    }

    public Connection getConnection() {

        try {
            return connection == null ? connection = DriverManager.getConnection(url, userName, password) : connection;
        } catch (SQLException e) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            throw new RuntimeException(e);
        }
    }

}
