package hafizdwp.me.newsapp.feature.home

import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.feature.base.MvpView

/**
 * Created by B195 on 1/19/2018.
 */
interface HomeView : MvpView {

    fun onGetHeadlinesSuccess(list: ArrayList<Headlines.Article>?)
}