package hafizdwp.me.newsapp.feature.explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import hafizdwp.me.newsapp.NewsApp
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.util.Constant
import hafizdwp.me.newsapp.util.Dummy
import hafizdwp.me.newsapp.util.SharedPref
import kotlinx.android.synthetic.main.fragment_explore.*

/**
 * Created by B195 on 1/19/2018.
 */
class ExploreFragment : Fragment() {

    val GRID_SPAN = 3
    val EXPLORE_TITLE = ArrayList<String>()
    val EXPLORE_IMAGE = ArrayList<Int>()

    var mAdapter: ExploreAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initExploreData()

        //initialize rv adapter
        mAdapter = ExploreAdapter(Glide.with(context))
        explore_recyclerview.layoutManager = GridLayoutManager(context, GRID_SPAN)
        explore_recyclerview.itemAnimator = DefaultItemAnimator()
        explore_recyclerview.adapter = mAdapter
        mAdapter?.addItems(EXPLORE_TITLE, EXPLORE_IMAGE)
    }

    private fun initExploreData() {
        val title = Dummy.EXPLORE_TITLE
        val image = Dummy.EXPLORE_IMAGE
        for (i in title.indices) {
            EXPLORE_TITLE.add(title[i])
            EXPLORE_IMAGE.add(image[i])
        }
    }
}