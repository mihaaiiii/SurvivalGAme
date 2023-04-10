package ro.mihaaiiii.gamesurvival.databases.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDbRepository implements Repository {
    private Connection connection;

    public MariaDbRepository(Connection connection) {
        this.connection = connection;
        createTable();

    }

    //ALTER TABLE my_table ENGINE = InnoDB
    //Create databases
    @Override
    public void createTable() {

        String queri = "CREATE table if not exists player(id_player int not null AUTO_INCREMENT, player_uuid varchar(90) unique, wins int, loss int, kills int, deaths int, PRIMARY KEY (id_player))  ENGINE=InnoDB";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Insert player
    @Override
    public void insertPlayer(String uuid, Integer wins, Integer loss, Integer kills, Integer deaths) {
        if (uuid.equals(getUuid(uuid))) {
            insertAll(uuid, wins, loss, kills, deaths);
        } else {
            String queri = "insert into player(player_uuid, wins,loss,kills,deaths)value(?,?,?,?,?)";
            try (PreparedStatement pr = connection.prepareStatement(queri)) {
                pr.setString(1, uuid);
                pr.setInt(2, wins);
                pr.setInt(3, loss);
                pr.setInt(4, kills);
                pr.setInt(5, deaths);
                pr.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Get from dataBases
    @Override
    public String getUuid(String player_uuid) {
        String queri = "select player_uuid from player where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.execute();
            ResultSet resultSet = pr.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Nu este asa ceva";
    }

    @Override
    public int getWins(String player_uuid) {
        String queri = "select wins from player where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.execute();
            ResultSet resultSet = pr.getResultSet();
            if (pr.getResultSet().next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public int getLoss(String player_uuid) {
        String queri = "select loss from player where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.execute();
            ResultSet resultSet = pr.getResultSet();
            if (pr.getResultSet().next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public int getKills(String player_uuid) {
        String queri = "select kills from player where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.execute();
            ResultSet resultSet = pr.getResultSet();
            if (pr.getResultSet().next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public int getDeaths(String player_uuid) {
        String queri = "select deaths from player where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.execute();
            ResultSet resultSet = pr.getResultSet();
            if (pr.getResultSet().next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    //Update section
    @Override
    public void instertWins(String player_uuid, Integer wins) {
        String queri = "update player set wins = ? where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setInt(1, wins);
            pr.setString(2, player_uuid);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override

    public void instertLoss(String player_uuid, Integer loss) {
        String queri = "update player set loss = ? where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setInt(1, loss);
            pr.setString(2, player_uuid);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override

    public void instertKills(String player_uuid, Integer kills) {
        String queri = "update player set kills = ? where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setInt(1, kills);
            pr.setString(2, player_uuid);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override

    public void instertDeaths(String player_uuid, Integer deaths) {
        String queri = "update player set deaths = ? where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setInt(1, deaths);
            pr.setString(2, player_uuid);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertAll(String player_uuid, Integer kills, Integer deaths, Integer loss, Integer wins) {
        String queri = "update player set player_uuid =?, wins = ?, loss=?, kills=?, deaths =? where player_uuid = ?";
        try (PreparedStatement pr = connection.prepareStatement(queri)) {
            pr.setString(1, player_uuid);
            pr.setInt(2, kills);
            pr.setInt(3, deaths);
            pr.setInt(4, loss);
            pr.setInt(5, wins);
            pr.setString(6, player_uuid);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}





