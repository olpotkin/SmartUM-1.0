package ru.diver_studio.smartum;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Konsul on 13.03.2015.
 */

public class Utils {
    //-------------------------------------------------------------------------
    // Test internet connection
    // true - connected
    // false - disconnected
    public static boolean IsOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {	// если есть подключение к интернет
            return true;
        }
        // если подключение отсутствует:
        return false;
    }
}
