package com.example.prova3.Services

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

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://mockup.fluo.site/v1/pbh/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun serviceCemiterio(): CemiterioService {
        return retrofit.create(CemiterioService::class.java)
    }

    fun serviceEquipe(): EquipeService{
        return retrofit.create(EquipeService::class.java)
    }
    //https://mockup.fluo.site/v1/pbh/EQUIP_HAB_CREAR
}