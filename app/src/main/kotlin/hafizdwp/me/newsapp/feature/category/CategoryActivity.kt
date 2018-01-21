package hafizdwp.me.newsapp.feature.category

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.MenuItem
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.model.Client
import hafizdwp.me.newsapp.data.response.Sources
import hafizdwp.me.newsapp.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.base_toolbar.*

/**
 * Created by B195 on 1/21/2018.
 */
class CategoryActivity : BaseActivity(), CategoryView {

    companion object {
        val EXTRA_CATEGORY = "extraCategory"

        fun start(context: Context, category: String) {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            context.startActivity(intent)
        }
    }

    var mProgressDialog: ProgressDialog? = null
    var mPresenter: CategoryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //init toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.extras[EXTRA_CATEGORY].toString().capitalize()

        //init presenter
        val dataManager = DataManager(Client.getBearerRetrofit())
        mPresenter = CategoryPresenter(dataManager)
        mPresenter?.attachView(this)

        //getSources to init tablayout
        mPresenter?.getSources(intent.extras[EXTRA_CATEGORY].toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onGetSourcesSuccess(source: ArrayList<Sources.Source>?) {

        /* init the tablayout content */
        val viewpagerAdapter = HomeViewPagerAdapter(supportFragmentManager)
        source?.let {
            for (i in it.indices) {
                val sourceObj = source[i]
                viewpagerAdapter.addFragment(
                        CategoryFragment.newInstance(sourceObj.id),
                        sourceObj.name)
            }
        }
        if(source?.size==1){
            category_tablayout.tabMode = TabLayout.MODE_FIXED
        }
        category_viewpager.adapter = viewpagerAdapter
        category_tablayout.setupWithViewPager(category_viewpager)
    }

    override fun showLoading(status: Boolean) {
        if(status){
            mProgressDialog = ProgressDialog(this)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.setMessage("Loading...")
            mProgressDialog?.show()
        }else{
            mProgressDialog?.dismiss()
        }
    }

    override fun showError(errorMessage: String) {
        baseShowError(errorMessage)
    }

    private class HomeViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        var mFragmentList: ArrayList<Fragment> = ArrayList()
        var mFragmentTitleList: ArrayList<String> = ArrayList()

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }
}