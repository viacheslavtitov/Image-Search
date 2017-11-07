package viacheslavtitov.image.search.app.presenters.main;

import java.util.List;

import viacheslavtitov.image.search.app.presenters.IMvpView;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;

/**
 * Created by User on 07.11.2017.
 */

public interface IMainView extends IMvpView{

    void displayResult(List<HistorySearchDBModel> imageModels);

}
