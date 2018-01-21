package hafizdwp.me.newsapp.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.feature.detail.DetailActivity
import hafizdwp.me.newsapp.util.Constant
import kotlinx.android.synthetic.main.item_home.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by B195 on 1/19/2018.
 */
class HomeAdapter(val glide: RequestManager) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var items = ArrayList<Headlines.Article>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(items[position], glide)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Headlines.Article, glide: RequestManager) {
            with(itemView){
                item_home_title.background.alpha = 180
                item_home_title.text = article.title
                item_home_description.text = article.description
                item_home_source.text = article.source?.name
                item_home_timestamp.text = convertUTC(article.publishedAt)

                glide.load(article.urlToImage)
                        .placeholder(R.drawable.background_home_image)
                        .fitCenter()
                        .into(item_home_image)

                setOnClickListener {
                    article.url?.let { it -> DetailActivity.start(context, it) }
                }
            }
        }

        private fun convertUTC(publishedAt: String?): String? {
            if(publishedAt==null) return "-"

            val df: DateFormat = SimpleDateFormat(Constant.DATE_FORMAT_TES)
            val date: Date = df.parse(publishedAt)
            return df.format(date)
        }
    }

    fun addItems(list: ArrayList<Headlines.Article>?) {
        if (list != null) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }
    }
}