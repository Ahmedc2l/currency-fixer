package com.ahmedc2l.currencyfixer.data.network

import com.ahmedc2l.currencyfixer.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object FixerNetwork {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("charset", "UTF-8")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .baseUrl(BuildConfig.FIXER_BASE_URL)
        .build()

    val fixerApiServices: FixerApiServices by lazy {
        retrofit.create(FixerApiServices::class.java)
    }
}

interface FixerApiServices {
    // "latest" endpoint - request the most recent exchange rate data
    @GET("api/latest?access_key=520ea67c656a30b78a48036ef0cae619&format=1")
    fun getLatestExchangeRatesAsync(): Deferred<Response<ResponseBody>>
}

