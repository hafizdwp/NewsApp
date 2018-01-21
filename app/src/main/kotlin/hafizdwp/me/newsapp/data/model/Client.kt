package hafizdwp.me.newsapp.data.model

import hafizdwp.me.newsapp.util.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by B195 on 1/19/2018.
 */
class Client {
    companion object {
        fun getBearerRetrofit(): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constant.URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getBearerAuth())
                    .build()

            return retrofit.create(ApiService::class.java)
        }

        private fun getBearerAuth(): OkHttpClient {
            val bearer = "Bearer " + Constant.API_KEY

            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                        .addHeader("Authorization", bearer)
                        .build()
                return@addInterceptor chain.proceed(request)
            }
            return builder.build()
        }
    }
}