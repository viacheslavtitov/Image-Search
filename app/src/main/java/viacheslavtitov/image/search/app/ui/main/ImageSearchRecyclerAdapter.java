package viacheslavtitov.image.search.app.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import viacheslavtitov.image.search.app.R;
import viacheslavtitov.image.search.app.repository.db.model.HistorySearchDBModel;

/**
 * Created by Viacheslav Titov on 07.11.2017.
 */

public class ImageSearchRecyclerAdapter extends RecyclerView.Adapter<ImageSearchRecyclerAdapter.ImageSearchViewHolder> {

    private List<HistorySearchDBModel> mData;

    ImageSearchRecyclerAdapter(List<HistorySearchDBModel> data) {
        mData = data;
    }

    @Override
    public ImageSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_image, parent, false);
        return new ImageSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageSearchViewHolder holder, int position) {
        final HistorySearchDBModel dbModel = getItem(position);
        if (dbModel == null) return;
        holder.data = dbModel;
        holder.title.setText(dbModel.getTitle());
        Picasso.with(holder.itemView.getContext()).load(dbModel.getPhotoUrl()).into(holder.photo);
    }

    private HistorySearchDBModel getItem(int position) {
        return mData != null && mData.size() > position && position > -1 ? mData.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class ImageSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_title)
        TextView title;
        @BindView(R.id.image_view_photo)
        ImageView photo;
        public HistorySearchDBModel data;

        ImageSearchViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}