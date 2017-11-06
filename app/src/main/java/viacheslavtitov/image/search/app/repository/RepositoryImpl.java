package viacheslavtitov.image.search.app.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import viacheslavtitov.image.search.app.repository.net.ApiService;
import viacheslavtitov.image.search.app.repository.net.model.ImageModel;
import viacheslavtitov.image.search.app.repository.net.model.SearchImagesResultModel;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

public class RepositoryImpl extends AbstractBaseRepository {

    private final ApiService mApiService;

    public RepositoryImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void fetchImages(Observer<List<ImageModel>> observer, String phrase) {
        fetchImages(observer, "id,title,thumb", "best_match", phrase);
    }

    @Override
    public void fetchImages(Observer<List<ImageModel>> observer,
                            String fields,
                            String sortOrder,
                            String phrase) {
        mApiService.searchImages(fields, sortOrder, phrase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchImagesResultModel>() {

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        observer.onSubscribe(d);
                    }

                    @Override
                    public void onNext(SearchImagesResultModel searchImagesResultModel) {
                        Timber.d("onNext %d", searchImagesResultModel.images.size());
                        observer.onNext(searchImagesResultModel.images);
                    }
                });
    }
}
