package viacheslavtitov.image.search.app.repository;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public interface ILifeCycle {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
