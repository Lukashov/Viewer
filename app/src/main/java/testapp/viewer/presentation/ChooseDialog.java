package testapp.viewer.presentation;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.OnClick;
import testapp.viewer.R;

/**
 * Created by Den on 28.12.16.
 */

public class ChooseDialog extends DialogFragment{

    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View layout = View.inflate(getActivity(), R.layout.dialog, null);

        ButterKnife.bind(this, layout);

        dialog.setTitle("My Dialog Title");
        dialog.setContentView(layout);

        return dialog;
    }

    @OnClick(R.id.tvFavorite)
    void setFavorite() {
        Log.d(MainActivity.TAG, "setFavorite");
        dismiss();
    }

    @OnClick(R.id.tvPermalink)
    void getPermalink() {
        Log.d(MainActivity.TAG, "getPermalink");
        dismiss();
    }

    @OnClick(R.id.tvDelete)
    void onDelete() {
        Log.d(MainActivity.TAG, "onDelete");
        dismiss();
    }


}
