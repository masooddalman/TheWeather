package com.liliputdev.theweather.repository.retrofit

import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchByModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetrofitService {

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://community-open-weather-map.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    @GET("weather?q={city}&lat=0&lon=0&callback=test&id=2172797&lang=null&units=imperial&mode=xml")
    fun searchByCity(
        @Path("city") city: String,
        @Header("X-RapidAPI-Host") host: String,
        @Header("X-RapidAPI-Key") key: String,
    ): Call<ApiModelSearchByModel>
}