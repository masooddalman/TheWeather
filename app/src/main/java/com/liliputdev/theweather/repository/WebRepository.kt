package com.liliputdev.theweather.repository

import com.liliputdev.theweather.repository.retrofit.RetrofitService

class WebRepository constructor(private val retrofitService: RetrofitService){
    private val appId="fccb4d16e46cd29c6ede546f5b471f71";

    fun searchByCity(city:String) = retrofitService.searchByCity(city,appId,5)

}