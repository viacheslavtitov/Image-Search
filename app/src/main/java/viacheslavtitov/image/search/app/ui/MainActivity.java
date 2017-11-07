package viacheslavtitov.image.search.app.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;
import viacheslavtitov.image.search.app.ImageSearchApplication;
import viacheslavtitov.image.search.app.R;
import viacheslavtitov.image.search.app.repository.RepositoryImpl;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;
import viacheslavtitov.image.search.app.utils.DeviceUtils;

public class MainActivity extends AppCompatActivity {

    @Inject
    DeviceUtils mDeviceUtils;

    @Inject
    RepositoryImpl mRepositoryImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageSearchApplication.get().getAppComponent().inject(this);
        if (!mDeviceUtils.hasInternetConnection()) {
            Timber.e("no internet connection");
            return;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(mOnQueryTextListener);

        return true;
    }

    private Observer<List<HistorySearchDBModel>> mObserverImages = new Observer<List<HistorySearchDBModel>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<HistorySearchDBModel> imageModels) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    private SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Timber.d("query text submit --- %s", query);
            mRepositoryImpl.fetchImages(mObserverImages, query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Timber.d("query text change - %s", newText);
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        mRepositoryImpl.onDestroy();
        super.onDestroy();
    }
}
