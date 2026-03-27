package com.blueicon.mexicointeligente.business.menunavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.business.home.HomeView
import com.blueicon.mexicointeligente.business.home.HomeViewModel
import com.blueicon.mexicointeligente.business.myaccount.MyAccountView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsViewModel
import com.blueicon.mexicointeligente.components.navbottombar.NavItemList
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNavigationView(navController2: NavController,
                       homeViewModel: HomeViewModel,
                       myearningsViewModel: MyearningsViewModel
) {
    val context = LocalContext.current
    val items = NavItemList.navItemList
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            NavigationBar(
                containerColor = colorResource(id = R.color.redNavigation),
                tonalElevation = 1.dp,
                windowInsets = NavigationBarDefaults.windowInsets
            ) { // Usando Material 3 (común hace 2 años)
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.red),
                            selectedTextColor = colorResource(id = R.color.red),
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = colorResource(id = R.color.gray),
                            unselectedTextColor = colorResource(id = R.color.gray)
                        ),
                        icon = {
                            Icon(
                                // Use painterResource to load the drawable by its ID
                                painter = painterResource(id = screen.icon),
                                contentDescription = "Icon",
                                Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // 1. Volver al destino inicial para evitar acumular pilas
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true // GUARDA EL ESTADO de la pestaña actual
                                }
                                // 2. Evita múltiples copias del mismo destino
                                launchSingleTop = true
                                // 3. RESTAURA EL ESTADO previo de la pestaña destino
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        // Definición del NavHost
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeView(navController2, homeViewModel) }
            composable("myearnings") { MyearningsView(navController2, myearningsViewModel) }
            composable("myaccount") { MyAccountView(navController2) }
        }
    }
}