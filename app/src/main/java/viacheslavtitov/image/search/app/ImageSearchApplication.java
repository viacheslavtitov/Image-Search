package viacheslavtitov.image.search.app;

import android.app.Application;

import viacheslavtitov.image.search.app.modules.AppComponent;
import viacheslavtitov.image.search.app.modules.AppModule;
import viacheslavtitov.image.search.app.modules.DaggerAppComponent;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

public class ImageSearchApplication extends Application {

    private AppComponent mAppComponent;

    private static ImageSearchApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mAppComponent = buildComponent();
    }

    public AppComponent getAppComponent() { return mAppComponent; }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static ImageSearchApplication get() { return sApp; }
}
