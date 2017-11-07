package viacheslavtitov.image.search.app.presenters;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public abstract class AbstractPresenter<V extends IMvpView> {

    protected V mView;


    public void attachView(V view) {
        mView = view;
        onViewAttached();
    }

    public void onDestroy() {

    }

    protected V getView() {
        return mView;
    }

    public abstract void onViewAttached();
}