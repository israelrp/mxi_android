package com.blueicon.mexicointeligente.business.menunavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.business.home.HomeView
import com.blueicon.mexicointeligente.business.home.HomeViewModel
import com.blueicon.mexicointeligente.business.myaccount.MyAccountView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsViewModel
import com.blueicon.mexicointeligente.components.navbottombar.BottomNavBar
import com.blueicon.mexicointeligente.components.navbottombar.NavItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNavigationView(navController: NavController,
                       homeViewModel: HomeViewModel,
                       myearningsViewModel: MyearningsViewModel
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navIteList = NavItemList.navItemList,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) {
        ContentScreen(selectedIndex, navController, homeViewModel, myearningsViewModel)
    }
}

@Composable
fun ContentScreen(selectedIndex: Int, navController: NavController,
                  homeViewModel: HomeViewModel,
                  myearningsViewModel: MyearningsViewModel
) {
    /*navController.navigate("HomeView") {
        // Pop up to the start destination of the graph to avoid
        // building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true // Optionally save the state of the first view
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }*/
    when(selectedIndex) {
        0 -> HomeView(navController, homeViewModel)
        1 -> MyearningsView(navController, myearningsViewModel)
        2 -> MyAccountView(navController)
    }
}