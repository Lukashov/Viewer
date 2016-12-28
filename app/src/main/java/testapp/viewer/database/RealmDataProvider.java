package testapp.viewer.database;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import testapp.viewer.database.DataProvider;
import testapp.viewer.database.model.RFileModel;

/**
 * Created by Den on 28.12.16.
 */

public class RealmDataProvider implements DataProvider {

    private static final String BASENAME = "testapp.viewer.database.RealmDataProvider";

    private Realm mInstance;
    private final Handler mHandler;

    public RealmDataProvider() {
        super();

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        mHandler = new Handler();
        mHandler.getLooper();
    }

    @Override public void open() {
        mInstance = Realm.getInstance(
                new RealmConfiguration.Builder()
                        .name(BASENAME)
                        .deleteRealmIfMigrationNeeded()
                        .build());
    }

    @Override public void close() {
        if (mInstance != null && !mInstance.isClosed()) {
            mInstance.close();
        }
    }

    @Override public void clear() {
        if (mInstance != null && !mInstance.isClosed()) {
            mInstance.beginTransaction();
            mInstance.deleteAll();
            mInstance.commitTransaction();
        }
    }

    @Override public void writeFileModel(List<RFileModel> list) {
        mInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(list));
    }

    @Override public List<RFileModel> readFileModel() {
        RealmResults<RFileModel> realmResult = mInstance
                .where(RFileModel.class)
                .findAll();
        return realmResult;
    }
}
