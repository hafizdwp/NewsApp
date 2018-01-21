package hafizdwp.me.newsapp.feature.category

import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.data.response.Everything
import hafizdwp.me.newsapp.feature.detail.DetailActivity
import hafizdwp.me.newsapp.util.Constant
import kotlinx.android.synthetic.main.item_category.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by B195 on 1/21/2018.
 */
class CategoryFragmentAdapter(private val glide: RequestManager) : RecyclerView.Adapter<CategoryFragmentAdapter.ViewHolder>() {

    var items = ArrayList<Everything.Article>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(items[position], glide)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Everything.Article, glide: RequestManager) {
            with(itemView){
                glide.load(article.urlToImage)
                        .placeholder(R.drawable.background_home_image)
                        .centerCrop()
                        .into(item_category_image)
                item_category_title.text = article.title
                item_category_source.text = article.source?.name
                item_category_description.text = article.description
                item_category_timestamp.text = convertUTC(article.publishedAt)

                //expand & collapse
                item_category_arrow.setOnClickListener{
                    val el = item_category_el
                    if(el.isExpanded){
                        item_category_arrow.background = ContextCompat.getDrawable(context, R.drawable.ic_arrow_down_32dp)
                        el.collapse()
                    }else{
                        item_category_arrow.background = ContextCompat.getDrawable(context, R.drawable.ic_arrow_up_32dp)
                        el.expand()
                    }
                }

                //detail click listener
                val url = article.url
                item_category_title.setOnClickListener { url?.let { it1 -> DetailActivity.start(context, it1) } }
                item_category_image.setOnClickListener { url?.let { it1 -> DetailActivity.start(context, it1) } }
            }
        }

        private fun convertUTC(utcTime: String?): String {
            if (utcTime == null) return "-"

            val df: DateFormat = SimpleDateFormat(Constant.DATE_FORMAT_TES)
            val date: Date = df.parse(utcTime)
            return df.format(date)
        }

    }

    fun addItems(list: ArrayList<Everything.Article>?) {
        items.clear()
        list?.let { items.addAll(it) }
        notifyDataSetChanged()
    }
}