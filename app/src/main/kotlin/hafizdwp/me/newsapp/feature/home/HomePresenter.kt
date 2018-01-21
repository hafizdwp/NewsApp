package hafizdwp.me.newsapp.feature.home

import android.util.Log
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.feature.base.BasePresenter
import hafizdwp.me.newsapp.util.NetworkError
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by B195 on 1/19/2018.
 */
class HomePresenter(val dataManager: DataManager) : BasePresenter<HomeView>() {

    val TAG = "HomePresenter"
    var subscription: Subscription? = null

    fun getHeadlines(country: String){
        subscription = dataManager.getHeadlines(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    //onNext()
                    response: Headlines ->
                    Log.d(TAG, response.toString())
                    mvpView?.onGetHeadlinesSuccess(response.articles)
                },{
                    //onError
                    error: Throwable? ->
                    Log.e(TAG, error.toString())
                    val ne = NetworkError(error)
                    mvpView?.showError(ne.appErrorMessage)
                })
    }

    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }
}