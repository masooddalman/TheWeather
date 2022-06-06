package com.liliputdev.theweather.repository

import com.liliputdev.theweather.repository.retrofit.RetrofitService

class WebRepository constructor(private val retrofitService: RetrofitService){
    private val host="community-open-weather-map.p.rapidapi.com"
    private val key="7f5036a166mshef6a10ee3c1b431p1aa45cjsna815b8fecefd"

    fun searchByCity(city:String) = retrofitService.searchByCity(city,host,key)

}