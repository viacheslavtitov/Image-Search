package viacheslavtitov.image.search.app.presenters.main;

import viacheslavtitov.image.search.app.presenters.AbstractPresenter;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public abstract class MainPresenter extends AbstractPresenter<IMainView> {

    public abstract void fetchOnlineData(String query);
    public abstract void fetchOfflineData();

    @Override
    public void onViewAttached() {

    }
}
