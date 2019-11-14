package com.example.movielistdemo.Repo

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HTTPClient {

    private var httpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    val coroutineRestfulAPI: CoroutineRestfulAPI by lazy {
        initializeRetrofit<CoroutineRestfulAPI>(CoroutineCallAdapterFactory())
    }


    private inline fun <reified T> initializeRetrofit(retrofitFactory: CallAdapter.Factory): T {

        if(httpClient == null){
            httpClient = OkHttpClient
                .Builder()
                .readTimeout(120,TimeUnit.SECONDS)
                .connectTimeout(120,TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .build() // your own http client
        }//if any singleton error occurs..change this init without singleton property

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(retrofitFactory)
                .client(httpClient!!)
                .build()
        }
        return retrofit!!.create(T::class.java)


    }




}