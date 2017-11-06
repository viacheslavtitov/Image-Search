package viacheslavtitov.image.search.app.repository.net.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Viacheslav Titov on 04.11.2017.
 */

public class ErrorModel {

    @SerializedName("ErrorCode")
    public String errorCode;

    @SerializedName("ErrorMessage")
    public String errorMessage;

    public boolean hasError() {
        return TextUtils.isEmpty(errorCode);
    }

}
