package com.blueicon.mexicointeligente.business.myaccount.adminteam

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AdminTeamViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ViewModel() {

    private val _itemsTeam = MutableStateFlow<List<TeamItem>>(emptyList())
    val itemsTeam: StateFlow<List<TeamItem>> = _itemsTeam.asStateFlow()

    init {
        _itemsTeam.value = listOf(
            TeamItem(0, "Rossy Ramirez", "2223668910", "", false, "26 enero 2026"),
            TeamItem(1, "Lourdes Puebla", "2221624927", "", false, "22 dic 2025"),
            TeamItem(2, "Karla Carpinteyro", "2222777105", "", true, "16 julio 2025")
        )
    }
}

data class TeamItem(
    val id: Int,
    val name: String,
    val cel: String,
    val email: String,
    val isAsesor: Boolean,
    val date: String
)