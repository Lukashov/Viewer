package testapp.viewer.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testapp.viewer.R;
import testapp.viewer.database.DataProvider;
import testapp.viewer.database.RealmDataProvider;
import testapp.viewer.database.model.RFileModel;

/**
 * Created by Den on 28.12.16.
 */

public class ViewerFragment extends Fragment {

    @BindView(R.id.rvFilelist_AM)
    RecyclerView recyclerView;

    private DataProvider mProvider = new RealmDataProvider();
    private List<RFileModel> fileModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewer, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mProvider.open();
        fileModelList = mProvider.readFileModel();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewerAdapter adapter = new ViewerAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setData(fileModelList, this);

    }
}
