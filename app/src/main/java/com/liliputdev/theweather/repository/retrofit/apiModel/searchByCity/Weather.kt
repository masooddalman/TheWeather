package com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)