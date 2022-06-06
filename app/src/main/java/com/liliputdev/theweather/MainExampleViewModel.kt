package com.liliputdev.theweather

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.liliputdev.theweather.repository.WebRepository
import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchByModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainExampleViewModel constructor(private val repository: WebRepository) : BaseObservable() {
    val searchedCityByName = MutableLiveData<ApiModelSearchByModel>()
    val searchedCityError = MutableLiveData<String>()
    private val inputCity = ObservableField<String>("Istanbul")
    private val inputCityError = ObservableField<String>("")
    private val isErrorEnable = ObservableBoolean(false)


    fun setInputCity(value: String) {
        inputCity.set(value)
    }

    fun getInputCity(): ObservableField<String> {
        return inputCity
    }


    fun setInputCityError(value: String) {
        inputCityError.set(value)
    }

    fun getInputCityError(): ObservableField<String> {
        return inputCityError
    }


    fun setIsErrorEnable(value: Boolean) {
        isErrorEnable.set(value)
    }

    fun getIsErrorEnable(): ObservableBoolean {
        return isErrorEnable
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        when {
            s.isEmpty() -> {
                setIsErrorEnable(true)
                setInputCityError("please insert city name")
            }
            s.length < 3 -> {
                setInputCityError("at least 3 character needs")
                setIsErrorEnable(true)
            }
            else -> {
                setInputCityError("")
                setIsErrorEnable(false)
            }
        }
    }

    fun searchWeatherByCity(city: String) {
        val response = repository.searchByCity(city)
        response.enqueue(object : Callback<ApiModelSearchByModel> {
            override fun onResponse(
                call: Call<ApiModelSearchByModel>,
                response: Response<ApiModelSearchByModel>
            ) {
                searchedCityByName.postValue(response.body())
            }

            override fun onFailure(call: Call<ApiModelSearchByModel>, t: Throwable) {
                searchedCityError.postValue(t.message)
            }
        })
    }
}