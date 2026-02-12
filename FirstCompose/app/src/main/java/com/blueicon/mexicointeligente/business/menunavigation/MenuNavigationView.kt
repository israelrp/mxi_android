package com.blueicon.mexicointeligente.business.menunavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.business.home.HomeView
import com.blueicon.mexicointeligente.business.home.HomeViewModel
import com.blueicon.mexicointeligente.business.myaccount.MyAccountView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsView
import com.blueicon.mexicointeligente.business.myearnings.MyearningsViewModel
import com.blueicon.mexicointeligente.components.navbottombar.BottomNavBar
import com.blueicon.mexicointeligente.components.navbottombar.NavItemList
import com.blueicon.mexicointeligente.utils.StorePreferences

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNavigationView(navController: NavController,
                       homeViewModel: HomeViewModel,
                       myearningsViewModel: MyearningsViewModel
) {
    val context = LocalContext.current
    val dataStore = StorePreferences(context)
    var selectedIndexw = dataStore.getMenuPosition.collectAsState(initial = 0)

    var selectedIndex by remember {
        mutableStateOf(selectedIndexw.value)
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
        ContentScreen(selectedIndexw, navController, homeViewModel, myearningsViewModel)
    }
}

@Composable
fun ContentScreen(
    selectedIndex: State<Int>, navController: NavController,
    homeViewModel: HomeViewModel,
    myearningsViewModel: MyearningsViewModel
) {
    when(selectedIndex.value) {
        0 -> HomeView(navController, homeViewModel)
        1 -> MyearningsView(navController, myearningsViewModel)
        2 -> MyAccountView(navController)
    }
}