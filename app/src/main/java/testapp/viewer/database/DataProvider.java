package testapp.viewer.database;

import java.util.List;

import testapp.viewer.database.model.RFileModel;

/**
 * Created by Den on 28.12.16.
 */

public interface DataProvider {

    void open();

    void close();

    void clear();

    void writeFileModel(List<RFileModel> list);

    List<RFileModel> readFileModel();
}
