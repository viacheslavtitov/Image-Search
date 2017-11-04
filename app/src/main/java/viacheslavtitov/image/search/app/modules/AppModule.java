package viacheslavtitov.image.search.app.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import viacheslavtitov.image.search.app.ImageSearchApplication;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

@Module
public final class AppModule {

    private final ImageSearchApplication mApplication;

    public AppModule(ImageSearchApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

}
