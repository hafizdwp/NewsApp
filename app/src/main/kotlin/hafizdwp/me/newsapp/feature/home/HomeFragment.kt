package hafizdwp.me.newsapp.feature.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.model.Client
import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.feature.MainActivity
import hafizdwp.me.newsapp.util.Constant
import hafizdwp.me.newsapp.util.SharedPref
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by B195 on 1/19/2018.
 */
class HomeFragment : Fragment(), HomeView {

    private val sp: SharedPref = SharedPref.getInstance()
    var mPresenter: HomePresenter? = null
    var mAdapter: HomeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home ,container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set presenter
        val dataManager = DataManager(Client.getBearerRetrofit())
        mPresenter = HomePresenter(dataManager)
        mPresenter?.attachView(this)

        //set adapter
        mAdapter = HomeAdapter(Glide.with(context))
        home_recyclerview.layoutManager = LinearLayoutManager(context)
        home_recyclerview.isClickable = true
        home_recyclerview.itemAnimator = DefaultItemAnimator()
        home_recyclerview.adapter = mAdapter

        //get headlines based on language preferences
        getHeadline()
    }

    fun getHeadline(){
        //get headlines based on language preferences
        val language = sp.getString(Constant.Pref.LANGUAGE, "id")
        mPresenter?.getHeadlines(language)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onGetHeadlinesSuccess(list: ArrayList<Headlines.Article>?) {
        //hand the list to the adapter
        mAdapter?.addItems(list)
    }

    override fun showError(errorMessage: String) {
        (activity as MainActivity).baseShowError(errorMessage)
    }

}