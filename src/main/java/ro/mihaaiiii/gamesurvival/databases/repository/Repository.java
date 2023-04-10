package ro.mihaaiiii.gamesurvival.databases.repository;

public interface Repository<T> {
    void createTable();
    //Insert player

    void insertPlayer(String uuid, Integer wins, Integer loss, Integer kills, Integer deaths);

    //Read from databases section
    String getUuid(String player_uuid);

    int getWins(String player_uuid);

    int getLoss(String player_uuid);

    int getKills(String player_uuid);

    int getDeaths(String player_uuid);

    //Update section
    void instertWins(String player_uuid, Integer wins);

    void instertLoss(String player_uuid, Integer loss);

    void instertKills(String player_uuid, Integer kills);

    void instertDeaths(String player_uuid, Integer deaths);
    void insertAll(String player_uuid, Integer kills, Integer deaths, Integer loss, Integer wins);

}
