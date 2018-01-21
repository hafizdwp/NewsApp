package hafizdwp.me.newsapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by B195 on 1/21/2018.
 */
data class Everything(@SerializedName("status")
                      var status: String? = null,

                      @SerializedName("totalResults")
                      var totalResults: Int = 0,

                      @SerializedName("articles")
                      var articles: ArrayList<Article>? = null){

    data class Article(
            @SerializedName("source")
            var source: Source? = null,

            @SerializedName("author")
            var author: String? = null,

            @SerializedName("title")
            var title: String? = null,

            @SerializedName("description")
            var description: String? = null,

            @SerializedName("url")
            var url: String? = null,

            @SerializedName("urlToImage")
            var urlToImage: String? = null,

            @SerializedName("publishedAt")
            var publishedAt: String? = null)

    data class Source(
            @SerializedName("id")
            var id: String? = null,

            @SerializedName("name")
            var name: String? = null)

}