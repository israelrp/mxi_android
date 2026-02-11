package com.blueicon.mexicointeligente.views.maintwo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.views.map.MapView
import com.blueicon.mexicointeligente.views.profile.ProfileView
import com.blueicon.mexicointeligente.components.TitleBar
import com.blueicon.mexicointeligente.components.navbottombar.BottomNavBar
import com.blueicon.mexicointeligente.components.navbottombar.NavItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTwoView(navController: NavController) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(
                        title = NavItemList.navItemList.get(selectedIndex).title
                    )},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(red = 164, green = 0, blue = 41, alpha = 255)
                )
            )
        },
        bottomBar = {
            BottomNavBar(
                navIteList = NavItemList.navItemList,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) {
        ContentScreen(selectedIndex, navController)
    }
}

@Composable
fun ContentScreen(selectedIndex: Int, navController: NavController) {
    when(selectedIndex) {
        //0 -> HomeView(navController)
        0 -> MapView()
        1 -> ProfileView(navController)
    }
}