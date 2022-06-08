package com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity

data class ApiModelSearchedCityItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String
)