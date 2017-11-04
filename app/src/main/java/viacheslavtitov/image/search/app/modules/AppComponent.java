package viacheslavtitov.image.search.app.modules;

import javax.inject.Singleton;

import dagger.Component;
import viacheslavtitov.image.search.app.MainActivity;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
}
