package hafizdwp.me.newsapp.feature

import android.os.Bundle
import android.os.Handler
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.feature.base.BaseActivity
import hafizdwp.me.newsapp.util.Constant
import hafizdwp.me.newsapp.util.SharedPref

/**
 * Created by B195 on 1/21/2018.
 */
class Splashscreen : BaseActivity() {

    val SPLASH_TIME: Long = 1000
    val sp: SharedPref = SharedPref.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({

            val status = sp.getBoolean(Constant.Pref.FIRST_TIME, true)
            if(status){
                sp.putString(Constant.Pref.LANGUAGE, "id")
                sp.putBoolean(Constant.Pref.FIRST_TIME, false)
            }

            MainActivity.start(this)
            this.finish()

        }, SPLASH_TIME)
    }
}