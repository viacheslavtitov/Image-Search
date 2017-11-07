package viacheslavtitov.image.search.app.modules;

import javax.inject.Singleton;

import dagger.Component;
import viacheslavtitov.image.search.app.ui.MainActivity;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

@Component(modules = {
        AppModule.class, ApiModule.class, UtilsModule.class, RepositoryModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
}
