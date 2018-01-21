package hafizdwp.me.newsapp.feature.category

import android.util.Log
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.response.Sources
import hafizdwp.me.newsapp.feature.base.BasePresenter
import hafizdwp.me.newsapp.util.NetworkError
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by B195 on 1/21/2018.
 */
class CategoryPresenter(private val dataManager: DataManager) : BasePresenter<CategoryView>() {

    private val TAG = "CategoryPresenter"
    private var subscription : Subscription? = null

    fun getSources(category: String){
        mvpView?.showLoading(true)
        subscription = dataManager.getSources(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    //onNext
                    response: Sources ->
                    Log.d(TAG, response.toString())
                    mvpView?.showLoading(false)
                    mvpView?.onGetSourcesSuccess(response.source)
                },{
                    //onError
                    error: Throwable? ->
                    Log.e(TAG, error.toString())
                    val ne = NetworkError(error)
                    mvpView?.showLoading(false)
                    mvpView?.showError(ne.appErrorMessage)
                })
    }

    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }
}