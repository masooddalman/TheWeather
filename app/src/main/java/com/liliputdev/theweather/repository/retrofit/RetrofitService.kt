package com.liliputdev.theweather.repository.retrofit

import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchByModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.http.*


interface RetrofitService {

    companion object {
        var retrofitService: RetrofitService? = null
        var gson = GsonBuilder()
            .setLenient()
            .create()
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://community-open-weather-map.p.rapidapi.com/")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    @GET("weather?")
    fun searchByCity(
        @Query("q") city: String,
        @Header("X-RapidAPI-Host") host: String,
        @Header("X-RapidAPI-Key") key: String
    ): Call<ApiModelSearchByModel>
}