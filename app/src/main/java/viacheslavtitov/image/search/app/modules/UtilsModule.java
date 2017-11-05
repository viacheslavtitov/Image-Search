package viacheslavtitov.image.search.app.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import viacheslavtitov.image.search.app.utils.DeviceUtils;

/**
 * Created by Viacheslav Titov on 05.11.2017.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    DeviceUtils provideDeviceUtils(Application context) {
        return new DeviceUtils(context);
    }

}
