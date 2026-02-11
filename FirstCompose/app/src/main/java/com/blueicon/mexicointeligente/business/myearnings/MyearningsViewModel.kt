package com.blueicon.mexicointeligente.business.myearnings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyearningsViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ViewModel() {

    private val _itemsOperations = MutableStateFlow<List<OperationsItem>>(emptyList())
    val itemsOperations: StateFlow<List<OperationsItem>> = _itemsOperations.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        _itemsOperations.value = listOf(
            OperationsItem(0, 1, "95898", "", "Mexico 89",
                "Jorge Espinoza", "","", OperationalStatus.Registrada.name,
                Status.Pendiente.name, TypeOperation.Activas),
            OperationsItem(1, 5, "84786", "", "Ave Palo alto 11901 B",
                "Israel Romero", "","", OperationalStatus.Habitacional.name,
                Status.Disponible.name, TypeOperation.Activas),
            OperationsItem(2, 3, "85686", "", "Ave Palo solo 130B F603",
                "Jacqueline Wood Gayou", "Jorge Arturo Lopez Perez","01 diciembre 2026",
                OperationalStatus.Vigente.name, Status.Disponible.name, TypeOperation.Cerradas),
            OperationsItem(3, 7, "95284", "", "Ave Palo solo 130B F603",
                "Jacqueline Wood Gayou", "Jorge Arturo Lopez Perez","01 diciembre 2026",
                OperationalStatus.LocalComercial.name, Status.Disponible.name, TypeOperation.Activas),
        )
    }

    val filteredItems = combine(_searchQuery, itemsOperations) { query, items ->
        if (query.isBlank()) {
            items
        } else {
            items.filter { it.typeOpe.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }
}

data class OperationsItem(
    val id: Int,
    val step: Int,
    val operationsId: String,
    val contract: String,
    val address: String,
    val tenant: String,
    val landlord: String,
    val dateRenovation: String,
    val operationalStatus: String,
    val status: String,
    val typeOpe: TypeOperation,
)

enum class OperationalStatus(val displayValue: String) {
    Registrada("Registrada"),
    Habitacional("Habitacional"),
    LocalComercial("Local comercial"),
    Vigente("Vigente")
}

enum class Status(val displayValue: String) {
    Pendiente("Pendiente"),
    Disponible("Disponible")
}

enum class TypeOperation {
    Activas,
    Cerradas,
    Renovaciones
}