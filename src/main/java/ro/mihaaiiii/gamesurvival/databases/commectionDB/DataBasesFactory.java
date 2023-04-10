package ro.mihaaiiii.gamesurvival.databases.commectionDB;

import lombok.Getter;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.model.DataBases;
import ro.mihaaiiii.gamesurvival.databases.commectionDB.model.HikariDB;
import ro.mihaaiiii.gamesurvival.databases.repository.MariaDbRepository;
import ro.mihaaiiii.gamesurvival.databases.repository.Repository;
import ro.mihaaiiii.gamesurvival.fileManager.DefaultConfig;

public class DataBasesFactory {


    @Getter
    private Repository repository;

    public DataBasesFactory() {

        DataBases dataBases = new HikariDB(DefaultConfig.getUrl(), DefaultConfig.getUser(), DefaultConfig.getPassword());
        repository = new MariaDbRepository(dataBases.getConnection());

    }


}
