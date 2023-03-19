package com.offneo.maximustask.networks

import android.annotation.SuppressLint
import android.content.Context
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class ApiClient(mContext: Context) {


    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiUri.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .client(getClient())
    .build()

    private var apiInterface: ApiInterface? = retrofit.create(ApiInterface::class.java)


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var apiClient: ApiClient? = null

        fun getInstance(mContext: Context): ApiClient? {
            if (apiClient == null) {
                setInstance(ApiClient(mContext))
            }
            return apiClient
        }

        private fun setInstance(apiClient: ApiClient) {
            ApiClient.apiClient = apiClient
        }
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val builder: Request.Builder = chain.request().newBuilder()
                builder.addHeader("Accept", "application/json")
                builder.addHeader("Content-Type", "application/json")
                chain.proceed(builder.build())
            })
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }


    fun getApiData(): ApiInterface? {
        return apiInterface
    }
}