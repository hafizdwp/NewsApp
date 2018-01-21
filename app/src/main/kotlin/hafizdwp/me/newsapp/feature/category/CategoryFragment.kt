package hafizdwp.me.newsapp.feature.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.data.DataManager
import hafizdwp.me.newsapp.data.model.Client
import hafizdwp.me.newsapp.data.response.Everything
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by B195 on 1/21/2018.
 */
class CategoryFragment : Fragment(), CategoryFragmentView, SwipeRefreshLayout.OnRefreshListener{

    companion object {
        private val KEY_SOURCE = "source"

        fun newInstance(sourceId: String): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putString(KEY_SOURCE, sourceId)
            fragment.arguments = args

            return fragment
        }
    }

    var mAdapter: CategoryFragmentAdapter? = null
    var mPresenter: CategoryFragmentPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init presenter
        val dataManager = DataManager(Client.getBearerRetrofit())
        mPresenter = CategoryFragmentPresenter(dataManager)
        mPresenter?.attachView(this)

        //init rv adapter
        mAdapter = CategoryFragmentAdapter(Glide.with(context))
        category_frag_recyclerview.layoutManager = LinearLayoutManager(context)
        category_frag_recyclerview.itemAnimator = DefaultItemAnimator()
        category_frag_recyclerview.adapter = mAdapter

        //init listener swipe refresh layout
        category_frag_srl.setOnRefreshListener(this)

        //trigger onRefresh once to iniate first call
        category_frag_srl.post{
            category_frag_srl.isRefreshing = true
            val sourceId = arguments[KEY_SOURCE].toString()
            mPresenter?.getEverything(sourceId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onRefresh() {
        //get news by bundled source
        val sourceId = arguments[KEY_SOURCE].toString()
        mPresenter?.getEverything(sourceId)
    }

    override fun onGetEverythingSuccess(list: ArrayList<Everything.Article>?) {
        category_frag_srl.isRefreshing = false
        mAdapter?.addItems(list)
    }

    override fun showError(errorMessage: String) {
        (activity as CategoryActivity).baseShowError(errorMessage)
    }
}