package hafizdwp.me.newsapp.feature.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by B195 on 1/19/2018.
 */
open class BaseActivity : AppCompatActivity() {

    var mToast: Toast? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun baseShowError(errorMessage: String) {
        if (mToast != null) mToast?.cancel()
        mToast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        mToast?.show()
    }
}