package ro.mihaaiiii.gamesurvival.databases.commectionDB.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ro.mihaaiiii.gamesurvival.databases.repository.MariaDbRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDB extends DataBases {

    private HikariConfig hikariConfig;

    private HikariDataSource hikariDataSource;

    private Connection connection;

    public HikariDB(String url, String userName, String password) {
        super(url, userName, password);

        this.hikariDataSource = new HikariDataSource(getHikari());
    }

    private HikariConfig getHikari() {
        hikariConfig = new HikariConfig();
        this.hikariConfig.setJdbcUrl(url);
        this.hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        this.hikariConfig.setUsername(userName);
        this.hikariConfig.setPassword(password);

        this.hikariConfig.setMaximumPoolSize(10);
        this.hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        this.hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        this.hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return this.hikariConfig;
    }

    public Connection getConnection() {


        try {
            return connection == null ? connection = hikariDataSource.getConnection() : connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
