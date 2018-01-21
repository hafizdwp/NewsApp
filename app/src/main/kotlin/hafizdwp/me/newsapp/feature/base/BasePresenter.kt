package hafizdwp.me.newsapp.feature.base

/**
 * Created by B195 on 1/19/2018.
 */
open class BasePresenter<T: MvpView> : Presenter<T> {

    var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    val isViewAttached : Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting listData to the Presenter")
}