package com.liliputdev.theweather.dataBindingAdapter

import com.google.android.material.textfield.TextInputLayout

import androidx.databinding.BindingAdapter
import android.widget.ArrayAdapter

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView


object DataBindingAdapter {
    @BindingAdapter("bind:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}