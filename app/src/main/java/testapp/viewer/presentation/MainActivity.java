package testapp.viewer.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.List;

import testapp.viewer.R;
import testapp.viewer.database.DataProvider;
import testapp.viewer.database.RealmDataProvider;
import testapp.viewer.database.model.RFileModel;
import testapp.viewer.model.FileModel;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "URAN";
    private DataProvider mProvider = new RealmDataProvider();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDataForDB();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_AM, new ViewerFragment())
                .commit();
    }

    private void createDataForDB() {
        List<RFileModel> fileModelList = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            FileModel fileModel = new FileModel(i);

            RFileModel rFileModel = new RFileModel(fileModel.getFileName(), fileModel.isFolder(),
                    new DateTime(fileModel.getModDate()).withZone(DateTimeZone.UTC).toString("d MMMM, yyyy"),
                    String.valueOf(fileModel.getFileType()), fileModel.isOrange(), fileModel.isBlue());

            fileModelList.add(rFileModel);
        }

        mProvider.open();
        mProvider.clear();
        mProvider.writeFileModel(fileModelList);
    }

    @Override protected void onDestroy() {
        mProvider.close();
        super.onDestroy();
    }
}
