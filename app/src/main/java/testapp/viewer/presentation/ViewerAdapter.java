package testapp.viewer.presentation;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testapp.viewer.R;
import testapp.viewer.database.model.RFileModel;
import testapp.viewer.model.FileModel;

/**
 * Created by Den on 28.12.16.
 */

public class ViewerAdapter extends RecyclerView.Adapter<ViewerAdapter.ViewHolder> {

    private List<RFileModel> list;
    private Fragment fragment;

    public ViewerAdapter() {
    }

    public void setData (List<RFileModel> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
        notifyDataSetChanged();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file_model, parent, false);
        return new ViewHolder(itemView);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        int drawable = R.drawable.ic_all_inclusive_blue_400_24dp;
        if (!list.get(position).isFolder()) {

            if (list.get(position).getFileType().equals(FileModel.FileType.IMAGE.toString())) {
                drawable = R.drawable.ic_image_blue_400_24dp;
            }

            if (list.get(position).getFileType().equals(FileModel.FileType.PDF.toString())) {
                drawable = R.drawable.ic_picture_as_pdf_blue_400_24dp;
            }

            if (list.get(position).getFileType().equals(FileModel.FileType.MOVIE.toString())) {
                drawable = R.drawable.ic_movie_blue_400_24dp;
            }

            if (list.get(position).getFileType().equals(FileModel.FileType.MUSIC.toString())) {
                drawable = R.drawable.ic_queue_music_blue_400_24dp;
            }

        } else {
            drawable = R.drawable.ic_folder_blue_400_24dp;
        }

        Picasso.with(holder.icon.getContext()).load(drawable).into(holder.icon);

        holder.title.setText(list.get(position).getFileName());
//        holder.modifiedTime.setText(list.get(position).getModDate().toString());

        if (list.get(position).isBlue() && list.get(position).isOrange()) {
            holder.firstColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.orange));
            holder.secondColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.blue));
        }

        if (list.get(position).isBlue() && !list.get(position).isOrange()) {
            holder.firstColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.blue));
            holder.secondColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.blue));
        }

        if (!list.get(position).isBlue() && list.get(position).isOrange()) {
            holder.firstColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.orange));
            holder.secondColorIndicator.setBackground(holder.firstColorIndicator.getContext().getResources().getDrawable(R.color.orange));
        }


        holder.itemView.setOnClickListener(v -> {

            if (list.get(position).isFolder()){
                fragment.getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out)
                        .replace(R.id.container_AM, new ViewerFragment(), null)
                        .addToBackStack(null)
                        .commit();
                Log.d(MainActivity.TAG, "This is a folder");
            } else {
                Log.d(MainActivity.TAG, "This is a file");
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            ChooseDialog dialog = new ChooseDialog();
            dialog.show(fragment.getActivity().getSupportFragmentManager(), null);
            return true;
        });

    }

    @Override public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivIcon_RFM)
        ImageView icon;
        @BindView(R.id.tv_title_RFM)
        TextView title;
        @BindView(R.id.tv_modified_time_RFM)
        TextView modifiedTime;
        @BindView(R.id.firstColorIndicator_RFM)
        View firstColorIndicator;
        @BindView(R.id.secondColorIndicator_RFM)
        View secondColorIndicator;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
