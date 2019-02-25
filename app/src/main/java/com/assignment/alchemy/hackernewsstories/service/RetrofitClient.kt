package com.assignment.alchemy.hackernewsstories.service

import android.content.Context

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val Base_URL = "https://hacker-news.firebaseio.com/"
    private var retrofit: Retrofit? = null


        fun getClient(context: Context): Retrofit? {

            if (retrofit == null) {
                val ok = OkHttpClient.Builder()
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(Base_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build())
                        .build()
            }
            return retrofit

    }

}
