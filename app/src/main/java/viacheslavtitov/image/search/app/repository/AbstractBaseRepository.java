package viacheslavtitov.image.search.app.repository;

import java.util.List;

import io.reactivex.Observer;
import viacheslavtitov.image.search.app.repository.net.model.ImageModel;

/**
 * Created by Viacheslav Titov on 06.11.2017.
 */

public abstract class AbstractBaseRepository {

    public abstract void fetchImages(Observer<List<ImageModel>> observer,
                                     String phrase);

    public abstract void fetchImages(Observer<List<ImageModel>> observer,
                                     String fields,
                                     String sortOrder,
                                     String phrase);

}
