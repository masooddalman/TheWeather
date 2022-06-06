package com.liliputdev.theweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liliputdev.theweather.repository.WebRepository

class ExampleMainViewModelFactory constructor(val repository: WebRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainExampleViewModel::class.java))
        {
            MainExampleViewModel(this.repository) as T
        }else
        {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}