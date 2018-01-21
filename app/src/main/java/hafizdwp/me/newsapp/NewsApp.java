package hafizdwp.me.newsapp;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by B195 on 1/19/2018.
 */

public class NewsApp extends Application{

    public static NewsApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

        //set default font using custom font to be used in entire app
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
