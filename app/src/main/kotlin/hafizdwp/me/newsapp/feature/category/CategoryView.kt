package hafizdwp.me.newsapp.feature.category

import hafizdwp.me.newsapp.data.response.Sources
import hafizdwp.me.newsapp.feature.base.MvpView

/**
 * Created by B195 on 1/21/2018.
 */
interface CategoryView : MvpView {
    fun showLoading(status: Boolean)
    fun onGetSourcesSuccess(source: ArrayList<Sources.Source>?)
}