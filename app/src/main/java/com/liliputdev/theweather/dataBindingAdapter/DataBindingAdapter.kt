package com.liliputdev.theweather.dataBindingAdapter

import com.google.android.material.textfield.TextInputLayout

import androidx.databinding.BindingAdapter


object DataBindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}