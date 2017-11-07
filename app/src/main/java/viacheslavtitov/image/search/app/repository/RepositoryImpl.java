package viacheslavtitov.image.search.app.repository;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import viacheslavtitov.image.search.app.repository.db.DataBaseHelper;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;
import viacheslavtitov.image.search.app.repository.net.ApiService;
import viacheslavtitov.image.search.app.repository.net.model.ImageModel;
import viacheslavtitov.image.search.app.repository.net.model.SearchImagesResultModel;
import viacheslavtitov.image.search.app.utils.DeviceUtils;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

public class RepositoryImpl extends AbstractBaseRepository {

    private final ApiService mApiService;
    private final DataBaseHelper mDataBaseHelper;
    private final DeviceUtils mDeviceUtils;

    public RepositoryImpl(ApiService apiService, DataBaseHelper dataBaseHelper, DeviceUtils deviceUtils) {
        mApiService = apiService;
        mDataBaseHelper = dataBaseHelper;
        mDeviceUtils = deviceUtils;
    }

    @Override
    public void fetchImages(Observer<List<HistorySearchDBModel>> observer, String phrase) {
        fetchImages(observer, "id,title,thumb", "best_match", phrase);
    }

    @Override
    public void fetchImages(Observer<List<HistorySearchDBModel>> observer,
                            String fields,
                            String sortOrder,
                            String phrase) {
        phrase = phrase.trim();
        if (TextUtils.isEmpty(phrase) || !mDeviceUtils.hasInternetConnection()) {
            fetchOfflineImages(observer);
            return;
        }
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
                        List<HistorySearchDBModel> convertedData = convertOnlineDataToOffline(searchImagesResultModel);
                        mDataBaseHelper.writeObjectsHistory(convertedData);
                        observer.onNext(convertedData);
                    }
                });
    }

    @Override
    public void fetchOfflineImages(Observer<List<HistorySearchDBModel>> observer) {
        List<HistorySearchDBModel> listHistory = mDataBaseHelper.getAllObjectsHistory();
        if (listHistory == null || listHistory.isEmpty()) {
            Timber.e("tried to load offline data, but data is empty");
            observer.onError(new Throwable("Nothing to display"));
            return;
        }
        Timber.d("load offline data: %d", listHistory.size());
        observer.onNext(listHistory);
    }

    private List<HistorySearchDBModel> convertOnlineDataToOffline(SearchImagesResultModel searchImagesResultModel) {
        List<HistorySearchDBModel> convertedData = new ArrayList<>();
        for (ImageModel imageModel : searchImagesResultModel.images) {
            if (TextUtils.isEmpty(imageModel.getThumbUrl(0))) continue;
            HistorySearchDBModel historySearchDBModel = new HistorySearchDBModel();
            historySearchDBModel.setId(imageModel.id);
            historySearchDBModel.setTitle(imageModel.title);
            historySearchDBModel.setPhotoUrl(imageModel.getThumbUrl(0));
            convertedData.add(historySearchDBModel);
        }
        return convertedData;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mDataBaseHelper.closeRealm();
    }
}
