package com.blueicon.mexicointeligente.business.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.blueicon.mexicointeligente.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ViewModel() {

    private val _itemsOperations = MutableStateFlow<List<OperationsHome>>(emptyList())
    val itemsOperations: StateFlow<List<OperationsHome>> = _itemsOperations.asStateFlow()

    init {
        _itemsOperations.value = listOf(
            OperationsHome(1,"Expediente inmobiliario", R.drawable.expediente),
            OperationsHome(2,"Investigacion", R.drawable.investigacion),
            OperationsHome(3,"Venta de polizas", R.drawable.ventapoliza),
        )
    }
}

data class OperationsHome(
    val id: Int,
    val title: String,
    val imagen: Int
)