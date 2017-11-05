package viacheslavtitov.image.search.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Viacheslav Titov on 05.11.2017.
 */

public class DeviceUtils {

    private Context mContext;

    public DeviceUtils(Context context) {
        mContext = context;
    }

    public boolean hasInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return true;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
