package com.blueicon.mexicointeligente.components.navbottombar

import com.blueicon.mexicointeligente.R

data class NavItem(
    val title: String,
    val icon: Int
)

object NavItemList {
    val navItemList = listOf(
        NavItem("Inicio", R.drawable.home),
        NavItem("Mis ganancias", R.drawable.money),
        NavItem("Mi cuenta", R.drawable.usuarion),
    )
}