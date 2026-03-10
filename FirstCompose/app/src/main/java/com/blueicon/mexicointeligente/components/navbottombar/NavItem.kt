package com.blueicon.mexicointeligente.components.navbottombar

import com.blueicon.mexicointeligente.R

data class NavItem(
    val title: String,
    val icon: Int,
    val route: String,
)

object NavItemList {
    val navItemList = listOf(
        NavItem("Inicio", R.drawable.home, "home"),
        NavItem("Mis ganancias", R.drawable.money, "myearnings"),
        NavItem("Mi cuenta", R.drawable.usuarion, "myaccount"),
    )
}