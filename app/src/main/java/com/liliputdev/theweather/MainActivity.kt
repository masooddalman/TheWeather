package com.liliputdev.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.liliputdev.theweather.databinding.ActivityMainBinding
import com.liliputdev.theweather.repository.WebRepository
import com.liliputdev.theweather.repository.retrofit.RetrofitService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainExampleViewModel
    private val retrofitService = RetrofitService.getInstance(RetrofitService.TYPE_LOCATION)
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = MainExampleViewModel(this,WebRepository(retrofitService))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Bind layout with ViewModel
        binding.viewModel = viewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this


    }
}