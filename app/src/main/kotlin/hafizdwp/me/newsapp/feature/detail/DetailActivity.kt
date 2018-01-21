package hafizdwp.me.newsapp.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.base_toolbar.*

/**
 * Created by B195 on 1/19/2018.
 */
class DetailActivity : BaseActivity() {

    companion object {
        private val EXTRA_URL = "extraUrl"

        fun start(context: Context, url: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set the webview
        val url = intent.extras[EXTRA_URL].toString()
        detail_webview.webViewClient = WebViewClient()
        detail_webview.settings.javaScriptEnabled = true
        detail_webview.settings.domStorageEnabled = true
        detail_webview.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }
}