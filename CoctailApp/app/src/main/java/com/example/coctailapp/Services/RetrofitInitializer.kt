package com.example.coctailapp.Services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                    chain.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }
    }

        fun serviceDrinks():DrinkService{
            return retrofitDB.create(DrinkService::class.java)
        }

        fun serviceLogin():AccountService{
            return retrofitLogin.create(AccountService::class.java)
        }

        private val retrofitLogin = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.fluo.work/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val retrofitDB = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

