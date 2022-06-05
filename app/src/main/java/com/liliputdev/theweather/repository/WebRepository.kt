package com.liliputdev.theweather.repository

import com.liliputdev.theweather.repository.retrofit.RetrofitService

class WebRepository constructor(private val retrofitService: RetrofitService){
    private val host="community-open-weather-map.p.rapidapi.com"
    private val key="14e24a7333mshe360883d2b5b6eap1152cdjsn0ff0bdf838f5"

    fun searchByCity(city:String) = retrofitService.searchByCity(city,host,key)

}