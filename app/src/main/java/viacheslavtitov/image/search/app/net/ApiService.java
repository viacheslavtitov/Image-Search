package viacheslavtitov.image.search.app.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import viacheslavtitov.image.search.app.net.model.SearchImagesResultModel;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

public interface ApiService {

    //https://api.gettyimages.com/v3/search/images?fields=id,title,thumb&sort_order=best&phrase=cat
    @GET("search/images")
    Call<SearchImagesResultModel> searchImages(@Query("fields") String fields,
                                               @Query("sort_order") String sortOrder,
                                               @Query("phrase") String phrase);

}
