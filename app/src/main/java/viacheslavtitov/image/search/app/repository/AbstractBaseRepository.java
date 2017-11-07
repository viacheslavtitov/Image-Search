package viacheslavtitov.image.search.app.repository;

import java.util.List;

import io.reactivex.Observer;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;
import viacheslavtitov.image.search.app.repository.net.model.ImageModel;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

public abstract class AbstractBaseRepository implements ILifeCycle{

    public abstract void fetchImages(Observer<List<HistorySearchDBModel>> observer,
                                     String phrase);

    public abstract void fetchImages(Observer<List<HistorySearchDBModel>> observer,
                                     String fields,
                                     String sortOrder,
                                     String phrase);

    public abstract void fetchOfflineImages(Observer<List<HistorySearchDBModel>> observer);

}
