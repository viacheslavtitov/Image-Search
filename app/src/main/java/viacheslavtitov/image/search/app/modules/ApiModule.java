package viacheslavtitov.image.search.app.modules;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import viacheslavtitov.image.search.app.BuildConfig;
import viacheslavtitov.image.search.app.net.ApiService;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

@Module
public final class ApiModule {

    public static final HttpUrl API_ENDPOINT = HttpUrl.parse("https://api.gettyimages.com/v3/");
    public static final String API_KEY = "pxanzy4epm6ayxuw8cacmqf2";

    @Provides
    @Singleton
    HttpUrl provideHttpUrl() {
        return API_ENDPOINT;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    OkHttpClient provideOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Api-Key", API_KEY);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder() //
                .client(client) //
                .baseUrl(baseUrl) //
                .addConverterFactory(GsonConverterFactory.create(gson)) //
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                .build();
    }

    @Provides
    ApiService provideCallService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
