package com.blueicon.mexicointeligente.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.components.MainIconButton
import com.blueicon.mexicointeligente.components.Space
import com.blueicon.mexicointeligente.components.TitleBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController, id: Int, optional: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(title = "Detalle") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Blue
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        },
        /*floatingActionButton = {
            ActionButton()
        }*/
    ) {
        ContentDetailView(navController, id, optional)
    }
}

@Composable
fun ContentDetailView(navController: NavController, id: Int, optional: String?) {
//Contenedor
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TitleView(name = "Detalle jajaja ${optional}")
        Space()
        //TitleView(name = id.toString())
        /*MainButton(name = "Enviar", backColor = Color.Red, colorText = Color.White) {
            navController.popBackStack()
        }*/
    }
}