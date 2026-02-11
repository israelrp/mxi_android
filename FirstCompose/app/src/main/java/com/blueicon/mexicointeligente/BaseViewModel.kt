package com.blueicon.mexicointeligente

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): ViewModel() {

    var msgError by mutableStateOf("")
        protected set

    var showToast by mutableStateOf(false)
        protected set

    var showLoading by mutableStateOf(false)
        protected set

    init {

    }

    fun onValueError(value: Boolean) {
        showToast = value
    }

    fun onValueLoading(value: Boolean) {
        showLoading = value
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}