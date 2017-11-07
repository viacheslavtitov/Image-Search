package viacheslavtitov.image.search.app.repository.db;

import java.util.List;

import io.realm.Realm;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public class DataBaseHelper {

    private final Realm mRealm;

    public DataBaseHelper(Realm realm) {
        mRealm = realm;
    }

    /**
     * Create new database object or update of #HistorySearchDBModel
     *
     * @param model
     */
    public void writeObjectHistory(HistorySearchDBModel model) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(model));
    }


    /**
     * Create new database objects or update list of objects of #HistorySearchDBModel
     *
     * @param model
     */
    public void writeObjectsHistory(List<HistorySearchDBModel> model) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(model));
    }

    public HistorySearchDBModel getObjectHistoryById(String id) {
        return mRealm.where(HistorySearchDBModel.class).equalTo("id", id).findFirst();
    }

    public HistorySearchDBModel getObjectHistoryByTitle(String title) {
        return mRealm.where(HistorySearchDBModel.class).equalTo("title", title).findFirst();
    }

    public List<HistorySearchDBModel> getAllObjectsHistory() {
        return mRealm.where(HistorySearchDBModel.class).findAll();
    }

    public void closeRealm() {
        if (!mRealm.isClosed())
            mRealm.close();
    }

}
