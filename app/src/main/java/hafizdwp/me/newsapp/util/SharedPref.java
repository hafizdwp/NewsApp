package hafizdwp.me.newsapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import hafizdwp.me.newsapp.NewsApp;

/**
 * Created by B195 on 1/21/2018.
 */

public class SharedPref {
    private static SharedPref sharedPref = new SharedPref(NewsApp.getContext());
    private static SharedPreferences mPref;

    private SharedPref() {
    }

    private SharedPref(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPref getInstance() {
        return sharedPref;
    }

    public String getString(String key, String defValue) {
        return mPref.getString(key, defValue);
    }

    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }
}
