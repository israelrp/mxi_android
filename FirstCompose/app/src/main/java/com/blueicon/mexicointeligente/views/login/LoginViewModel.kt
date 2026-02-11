package com.blueicon.mexicointeligente.views.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.biometric.BiometricManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueicon.mexicointeligente.api.models.LoginResponse
import com.blueicon.mexicointeligente.enums.FieldLogin
import com.blueicon.mexicointeligente.utils.StorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: LoginRepository,
    @ApplicationContext private val applicationContext: Context
    ) : ViewModel() {

    private val _navigationEvents = Channel<String>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    var state by mutableStateOf(DataLogin())
        private set

    var msgError by mutableStateOf("")
        private set

    var showToast by mutableStateOf(false)
        private set

    var showLoading by mutableStateOf(false)
        private set

    var showBiometric by mutableStateOf(false)
        private set

    private val _login = MutableStateFlow<LoginResponse>(LoginResponse(false, "",
        "", 0, 0))
    val login = _login.asStateFlow()

    val dataStore = StorePreferences(applicationContext)

    private fun responseLogin() {

        viewModelScope.launch {

            showLoading = true

            withContext(Dispatchers.IO) {
                val result = repo.setLogin(LoginRequest(state.email, state.password))
                if (result != null) {

                    showLoading = false
                    _login.value = result

                    if (result.Message != null) {
                        msgError = "${result.Message}"
                        showToast = true

                        if (result.Status) {
                            //Guardar en data store
                            viewModelScope.launch {
                                dataStore.saveName(result.userName)
                                dataStore.saveId(result.userId)
                                dataStore.saveEmail(state.email)

                                Log.e("", "Id asignado: " + dataStore.getUserId)

                                //navigateToMainTwo = true

                                viewModelScope.launch {
                                    _navigationEvents.send("MainTwo")
                                }
                            }
                        }
                    }
                } else {
                    print("else responseLogin")
                }
            }
        }
    }

    fun onValueState(value: String, text: FieldLogin) {
        when(text) {
            FieldLogin.EMAIL -> state = state.copy(email = value)
            FieldLogin.PASSWORD -> state = state.copy(password = value)
            else -> {}
        }
    }

    fun onValueError(value: Boolean) {
        showToast = value
    }

    fun validateFields() {
        if (state.email.isEmpty()) {
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
        }
    }

    private fun validateEmail(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
    }

    fun setUpAuth() {
        if (BiometricManager.from(applicationContext).canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL) ==
                  BiometricManager.BIOMETRIC_SUCCESS) {
           showBiometric = true
        }
    }

    fun onNavigateToDetail(itemId: String) {
        viewModelScope.launch {
            _navigationEvents.send("MainTwo")
        }
    }
}