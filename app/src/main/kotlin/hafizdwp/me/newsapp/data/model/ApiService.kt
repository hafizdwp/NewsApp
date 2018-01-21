package hafizdwp.me.newsapp.data.model

import hafizdwp.me.newsapp.data.response.Everything
import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.data.response.Sources
import hafizdwp.me.newsapp.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by B195 on 1/19/2018.
 */
interface ApiService {

    @GET(Constant.URL.HEADLINES)
    fun getHeadlines(@Query("country") country: String) : Observable<Headlines>

    @GET(Constant.URL.SOURCES)
    fun getSources(@Query("category") category: String) : Observable<Sources>

    @GET(Constant.URL.EVERYTHING + "?pageSize=15")
    fun getEverything(@Query("sources") sourceId: String) : Observable<Everything>
}