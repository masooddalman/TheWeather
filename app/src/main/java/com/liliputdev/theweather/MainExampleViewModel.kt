package com.liliputdev.theweather

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.liliputdev.theweather.repository.WebRepository
import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchedCity
import com.liliputdev.theweather.repository.retrofit.apiModel.searchByCity.ApiModelSearchedCityItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainExampleViewModel(private val context: Context, private val repository: WebRepository) :
    BaseObservable() {

    var searchCityInputValue = MutableLiveData<String>()
    var searchCityInputError = MutableLiveData<String>()
    var searchCityInputErrorEnable = MutableLiveData<Boolean>()

    var citySuggestionAdapter = MutableLiveData<CitySuggestionAdapter>()

    init {
        citySuggestionAdapter.postValue(CitySuggestionAdapter())

        citySuggestionAdapter.value?.setOnItemClickListener { _adapter, _view, _position ->
            citySuggestionAdapter.value?.let {
                    item ->
                 searchCityInputValue.postValue(item.data[_position].name)
                    citySuggestionAdapter.value?.data?.clear();
                    citySuggestionAdapter.value?.notifyDataSetChanged()

            }
        }

    }


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        when {
            s.isEmpty() -> {
                searchCityInputErrorEnable.postValue(true)
                searchCityInputError.postValue("please insert city name")
            }
            s.length < 3 -> {
                searchCityInputError.postValue("at least 3 character needs")
                searchCityInputErrorEnable.postValue(true)
                citySuggestionAdapter.value?.data?.clear();
                citySuggestionAdapter.value?.notifyDataSetChanged()
            }
            else -> {
                searchCityInputError.postValue("")
                searchCityInputErrorEnable.postValue(false)
                // search for city in google place
                searchCity(s.toString())
            }
        }
    }

    private fun searchCity(city: String) {
        Log.v("masood", "text to search city : $city")
        val response = repository.searchByCity(city)
        response.enqueue(object : Callback<ApiModelSearchedCity> {
            override fun onResponse(
                call: Call<ApiModelSearchedCity>,
                response: Response<ApiModelSearchedCity>
            ) {
                Log.v("masood", "response : ${response.body()}")
                response.body()?.let { list ->
                    citySuggestionAdapter.value?.setNewInstance(list)
                    citySuggestionAdapter.value?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ApiModelSearchedCity>, t: Throwable) {
                Log.v("masood", "cannot find city \n $t")
            }
        })
    }
}