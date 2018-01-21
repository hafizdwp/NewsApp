package hafizdwp.me.newsapp.feature.explore

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.feature.category.CategoryActivity
import kotlinx.android.synthetic.main.item_explore.view.*

/**
 * Created by B195 on 1/21/2018.
 */
class ExploreAdapter(val glide: RequestManager) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    var itemTitle = ArrayList<String>()
    var itemImage = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_explore, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(itemTitle[position], itemImage[position], glide)
    }

    override fun getItemCount(): Int {
        return itemTitle.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String, image: Int, glide: RequestManager) {
            with(itemView) {
                item_explore_title.text = title.toUpperCase()
                glide.load(image)
                        .placeholder(R.drawable.background_home_image)
                        .into(item_explore_image)

                //click listener to CategoryActivity
                setOnClickListener {
                    CategoryActivity.start(context, title)
                }
            }
        }
    }

    fun addItems(title: ArrayList<String>, image: ArrayList<Int>) {
        itemTitle.addAll(title)
        itemImage.addAll(image)
    }
}