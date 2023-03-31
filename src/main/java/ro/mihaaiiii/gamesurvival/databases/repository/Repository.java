package ro.mihaaiiii.gamesurvival.databases.repository;

public interface Repository<T> {

    void createTable();

    T read();

    void update();

    void delete();

}
