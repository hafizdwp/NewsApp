package hafizdwp.me.newsapp.feature.category

import android.annotation.SuppressLint
import android.util.Log
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.response.Everything
import hafizdwp.me.newsapp.feature.base.BasePresenter
import hafizdwp.me.newsapp.util.NetworkError
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by B195 on 1/21/2018.
 */
class CategoryFragmentPresenter(private val dataManager: DataManager)
    : BasePresenter<CategoryFragmentView>() {

    private val TAG = "CategoryFragmentPresenter"
    private var subscription: Subscription? = null

    @SuppressLint("LongLogTag")
    fun getEverything(sourceId: String) {
        if (!isViewAttached) return

        subscription = dataManager.getEverything(sourceId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    //onNext()
                    response: Everything ->
                    Log.d(TAG, response.toString())
                    mvpView?.onGetEverythingSuccess(response.articles)
                }, {
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