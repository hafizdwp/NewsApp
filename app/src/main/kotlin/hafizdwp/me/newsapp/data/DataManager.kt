package hafizdwp.me.newsapp.data

import hafizdwp.me.newsapp.data.model.ApiService
import hafizdwp.me.newsapp.data.response.Everything
import hafizdwp.me.newsapp.data.response.Headlines
import hafizdwp.me.newsapp.data.response.Sources
import rx.Observable

/**
 * Created by B195 on 1/19/2018.
 */
class DataManager(val apiService: ApiService) {

    fun getHeadlines(country: String): Observable<Headlines>
            = apiService.getHeadlines(country)

    fun getSources(category: String): Observable<Sources>
            = apiService.getSources(category)

    fun getEverything(sourceId: String): Observable<Everything>
            = apiService.getEverything(sourceId)
}