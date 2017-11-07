package viacheslavtitov.image.search.app.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import viacheslavtitov.image.search.app.ImageSearchApplication;
import viacheslavtitov.image.search.app.R;
import viacheslavtitov.image.search.app.presenters.main.IMainView;
import viacheslavtitov.image.search.app.presenters.main.MainPresenter;
import viacheslavtitov.image.search.app.presenters.main.MainPresenterImpl;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ImageSearchRecyclerAdapter mAdapter;

    @BindView(R.id.text_view_error)
    TextView mTextViewError;

    @Inject
    MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImageSearchApplication.get().getAppComponent().inject(this);

        setUpView();
        mMainPresenter.attachView(this);
        mMainPresenter.fetchOfflineData();
    }

    private void setUpView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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

    private SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Timber.d("query text submit --- %s", query);
            mMainPresenter.fetchOnlineData(query);
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
        mRecyclerView.setAdapter(null);
        mMainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showError(String error) {
        mRecyclerView.setVisibility(View.GONE);
        mTextViewError.setVisibility(View.VISIBLE);
        mTextViewError.setText(error);
    }

    @Override
    public void displayResult(List<HistorySearchDBModel> imageModels) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mTextViewError.setVisibility(View.GONE);
        mAdapter = new ImageSearchRecyclerAdapter(imageModels);
        mRecyclerView.setAdapter(mAdapter);
    }
}
