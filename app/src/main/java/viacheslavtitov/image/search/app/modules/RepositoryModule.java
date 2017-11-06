package viacheslavtitov.image.search.app.modules;

import dagger.Module;
import dagger.Provides;
import viacheslavtitov.image.search.app.repository.RepositoryImpl;
import viacheslavtitov.image.search.app.repository.net.ApiService;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

@Module
public class RepositoryModule {

    @Provides
    RepositoryImpl provideRepository(ApiService apiService) {
        return new RepositoryImpl(apiService);
    }

}
