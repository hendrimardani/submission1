package com.example.mysubmission11.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.mysubmission11.BuildConfig

class ApiConfig {

    companion object {

        val mySuperSecretKey = BuildConfig.KEY
        val myBaseUrl = BuildConfig.BASE_URL

        fun getApiService(): ApiService {
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", mySuperSecretKey)
                    .build()
                chain.proceed(requestHeaders)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(myBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}