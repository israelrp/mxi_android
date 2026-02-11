package com.blueicon.mexicointeligente.business.enrolment.phonenumber

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueicon.mexicointeligente.utils.StorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ViewModel() {

    private val _navigationEvents = Channel<String>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    val dataStore = StorePreferences(applicationContext)

    fun validateFields() {
        /*if (state.email.isEmpty()) {
            msgError = "El email no puede estar vacio."
            showToast = true
            //Toast.makeText(applicationContext, "Hello from Compose!", Toast.LENGTH_SHORT).show()
        } else {
            if (validateEmail()) {
                if (state.password.isEmpty()) {
                    msgError = "El password no puede estar vacio."
                    showToast = true
                } else {
                    if (state.password.count() < 8) {
                        msgError = "El password debe tener al menos 8 caracteres"
                        showToast = true
                    } else {
                        showToast = false
                    }

                    Log.e("TAG", "responseLogin")
                    responseLogin()
                }
            } else {
                msgError = "El email no es valido."
                showToast = true
            }
        }*/
    }

    fun onNavigateToDetail(itemId: String) {
        viewModelScope.launch {
            _navigationEvents.send("MainTwo")
        }
    }
}