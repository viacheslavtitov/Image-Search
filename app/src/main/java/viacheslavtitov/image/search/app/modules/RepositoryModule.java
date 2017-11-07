package viacheslavtitov.image.search.app.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import viacheslavtitov.image.search.app.repository.RepositoryImpl;
import viacheslavtitov.image.search.app.repository.db.DataBaseHelper;
import viacheslavtitov.image.search.app.repository.net.ApiService;
import viacheslavtitov.image.search.app.utils.DeviceUtils;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

@Module
public class RepositoryModule {

    private static final long REALM_VERSION = 1;

    @Provides
    RealmConfiguration provideRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name("history_search.realm")
                .schemaVersion(REALM_VERSION)
                .build();
    }

    @Singleton
    @Provides
    Realm provideRealm(RealmConfiguration configuration) {
        return Realm.getInstance(configuration);
    }

    @Provides
    DataBaseHelper provideDataBaseHelper(Realm realm) {
        return new DataBaseHelper(realm);
    }

    @Provides
    RepositoryImpl provideRepository(ApiService apiService, DataBaseHelper dataBaseHelper, DeviceUtils deviceUtils) {
        return new RepositoryImpl(apiService, dataBaseHelper, deviceUtils);
    }

}
