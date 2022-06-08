package com.liliputdev.theweather.repository.retrofit

import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchedCity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient
import retrofit2.http.*


interface RetrofitService {

    companion object {
        private var retrofitLocationService: RetrofitService? = null
        private var retrofitWeatherService: RetrofitService? = null
        val TYPE_LOCATION=0
        val TYPE_WRATHER=1
        fun getInstance(type:Int= TYPE_WRATHER): RetrofitService {
            when (type) {
                TYPE_LOCATION -> {
                    if (retrofitLocationService == null) {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("http://api.openweathermap.org/geo/1.0/")
                            .client(OkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        retrofitLocationService = retrofit.create(RetrofitService::class.java)
                    }
                    return retrofitLocationService!!
                }
                TYPE_WRATHER -> {
                    if (retrofitWeatherService == null) {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://community-open-weather-map.p.rapidapi.com/")
                            .client(OkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        retrofitWeatherService = retrofit.create(RetrofitService::class.java)
                    }
                    return retrofitWeatherService!!
                }
                else ->{ return retrofitWeatherService!!}
            }
        }
    }

    @GET("direct?")
    fun searchByCity(
        @Query("q") city: String,
        @Query("appId") appId:String,
        @Query("limit") limit:Int
    ): Call<ApiModelSearchedCity>
}