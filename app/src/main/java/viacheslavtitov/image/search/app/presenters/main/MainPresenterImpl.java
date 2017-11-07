package viacheslavtitov.image.search.app.presenters.main;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import viacheslavtitov.image.search.app.repository.RepositoryImpl;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public class MainPresenterImpl extends MainPresenter {

    private final RepositoryImpl mRepositoryImpl;

    public MainPresenterImpl(RepositoryImpl repository) {
        mRepositoryImpl = repository;
    }

    @Override
    public void fetchOnlineData(String query) {
        mRepositoryImpl.fetchImages(mObserverImages, query);
    }

    @Override
    public void fetchOfflineData() {
        mRepositoryImpl.fetchOfflineImages(mObserverImages);
    }

    private Observer<List<HistorySearchDBModel>> mObserverImages = new Observer<List<HistorySearchDBModel>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<HistorySearchDBModel> imageModels) {
            getView().displayResult(imageModels);
        }

        @Override
        public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRepositoryImpl.onDestroy();
    }
}
