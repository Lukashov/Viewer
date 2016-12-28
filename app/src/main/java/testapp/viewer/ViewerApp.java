package testapp.viewer;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Den on 28.12.16.
 */

public class ViewerApp extends Application {
    private static ViewerApp mApplication;

    public static ViewerApp getApplication() {
        return mApplication;
    }

    @Override public void onCreate() {
        super.onCreate();

        mApplication = this;
        Realm.init(this);
    }
}
