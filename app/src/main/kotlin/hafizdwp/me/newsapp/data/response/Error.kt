package hafizdwp.me.newsapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by B195 on 1/21/2018.
 */
data class Error(@SerializedName("status")
                 var status: String,
                 @SerializedName("code")
                 var code: String,
                 @SerializedName("message")
                 var message: String)