package com.omdeep.jetpackcompose.data.network

import com.omdeep.jetpackcompose.BuildConfig.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    /*Creating common OkHttpClient:*/
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }


    /*Creating common Retrofit Client:*/
    private fun getRetrofitClient(baseUrl : String) : Retrofit.Builder {
        val retrofitClient : Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
        return retrofitClient
    }

    /*Creating common Api Service:*/
    fun getApiService() : ApiService {
        val apiInterface : ApiService by lazy {
            getRetrofitClient(BASE_URL).build().create(ApiService::class.java)
        }
        return apiInterface
    }

    /*Creating Api service to change the base url*/
    fun getNewApiService(baseUrl: String) : ApiService {
        val apiInterface : ApiService by lazy {
            getRetrofitClient(baseUrl).build().create(ApiService::class.java)
        }
        return apiInterface
    }
}