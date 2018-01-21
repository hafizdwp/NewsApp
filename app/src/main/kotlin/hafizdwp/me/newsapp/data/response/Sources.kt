package hafizdwp.me.newsapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by B195 on 1/21/2018.
 */
data class Sources(@SerializedName("status")
                   var status: String? = null,

                   @SerializedName("sources")
                   var source: ArrayList<Source>? = null) {

    data class Source(@SerializedName("id")
                      var id: String,

                      @SerializedName("name")
                      var name: String,

                      @SerializedName("description")
                      var description: String? = null,

                      @SerializedName("url")
                      var url: String? = null,

                      @SerializedName("category")
                      var category: String? = null,

                      @SerializedName("language")
                      var language: String? = null,

                      @SerializedName("country")
                      var country: String? = null)

}