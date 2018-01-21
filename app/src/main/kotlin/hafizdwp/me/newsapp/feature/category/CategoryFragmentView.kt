package hafizdwp.me.newsapp.feature.category

import hafizdwp.me.newsapp.data.response.Everything
import hafizdwp.me.newsapp.feature.base.MvpView

/**
 * Created by B195 on 1/21/2018.
 */
interface CategoryFragmentView : MvpView {

    fun onGetEverythingSuccess(list: ArrayList<Everything.Article>?)
}