package ro.mihaaiiii.gamesurvival.databases.commectionDB.model;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public abstract class DataBases {

    private Connection connection;
    protected  String url;
    protected String userName;
    protected  String password;

    public DataBases(String url, String userName, String password) {

        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() {

        try {
            return connection == null ? connection = DriverManager.getConnection(url, userName, password) : connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
