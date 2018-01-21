package hafizdwp.me.newsapp.feature.base

/**
 * Created by B195 on 1/19/2018.
 */
interface Presenter<V: MvpView> {
    fun attachView(mvpView: V)

    fun detachView()
}