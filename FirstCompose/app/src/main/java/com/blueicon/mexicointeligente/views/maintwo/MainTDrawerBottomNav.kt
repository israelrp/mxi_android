package com.blueicon.mexicointeligente.views.maintwo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.components.TitleBar
import com.blueicon.mexicointeligente.views.map.MapView
import com.blueicon.mexicointeligente.components.navbottombar.BottomNavBar
import com.blueicon.mexicointeligente.components.navbottombar.NavItemList
import com.blueicon.mexicointeligente.views.profile.ProfileView
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTDrawerBottomNav(navController: NavController) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Text(text = "Gola")
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                val coroutineScope = rememberCoroutineScope()

                CenterAlignedTopAppBar(
                    title = {
                        TitleBar(
                            title = NavItemList.navItemList.get(selectedIndex).title
                        )},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
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
            ContentScreen2(selectedIndex, navController)
        }
    }


}

@Composable
fun ContentScreen2(selectedIndex: Int, navController: NavController) {
    when(selectedIndex) {
        //0 -> HomeView(navController)
        0 -> MapView()
        1 -> ProfileView(navController)
    }
}
