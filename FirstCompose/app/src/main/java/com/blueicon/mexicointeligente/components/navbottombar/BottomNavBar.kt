package com.blueicon.mexicointeligente.components.navbottombar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.utils.StorePreferences
import kotlinx.coroutines.launch

@Composable
fun BottomNavBar(
    navIteList: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    val dataStore = StorePreferences(context)

    NavigationBar(
        containerColor = colorResource(id = R.color.redNavigation),
        tonalElevation = 1.dp,
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        navIteList.forEachIndexed { index, navItem ->
            val scope = rememberCoroutineScope()
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.red),
                    selectedTextColor = colorResource(id = R.color.red),
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = colorResource(id = R.color.gray),
                    unselectedTextColor = colorResource(id = R.color.gray)
                ),
                selected = selectedIndex == index,
                onClick = {
                    scope.launch {dataStore.saveMenuPosition(index)}
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        // Use painterResource to load the drawable by its ID
                        painter = painterResource(id = navItem.icon),
                        contentDescription = "Icon",
                        Modifier.size(30.dp)
                    )
                },
                label = {
                    Text(
                        text = navItem.title,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                    )
                }
            )
        }
    }    
}