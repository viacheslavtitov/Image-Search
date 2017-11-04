package viacheslavtitov.image.search.app.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

public class SearchImagesResultModel extends ErrorModel {

    @SerializedName("images")
    public List<ImageModel> images;

}
