package viacheslavtitov.image.search.app.repository.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

public class ImageModel {

    @SerializedName("id")
    public String id;

    @SerializedName("display_sizes")
    public List<ThumbModel> thumbModels;

    @SerializedName("title")
    public String title;

    public String getThumbUrl(int count) {
        return thumbModels != null && !thumbModels.isEmpty() && count > 0 && count < thumbModels.size()
                ? thumbModels.get(count).uri : "";
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equalsIgnoreCase(this.toString());
    }
}
