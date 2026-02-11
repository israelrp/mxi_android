package com.blueicon.mexicointeligente.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.components.Space
import com.blueicon.mexicointeligente.components.btnNormal
import com.blueicon.mexicointeligente.components.btnOutline
import com.blueicon.mexicointeligente.components.imageGral
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeView(navController: NavController) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        ContentWelcomeView(navController)
    }
}

@ExperimentalMaterial3Api
@Composable
fun ContentWelcomeView(navController: NavController) {

    val gradientColors = listOf(Color(red = 164, green = 0, blue = 41, alpha = 255), Color(red = 41, 18, 44, alpha = 255))

    //Contenedor
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = gradientColors,
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY // Ends at the bottom of the column
                )
            ),
        verticalArrangement = Arrangement.Top,
    ) {

        Space()

        imageGral()

        Space()

        Text(
            text = "Welcome",
            fontSize = 32.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 24.dp, bottom = 24.dp)
        )

        Space()

        btnOutline(name = "SIGN IN", outlineColor = Color.White, colorText = Color.White) {
            //navController.navigate("Home")
        }

        Space()

        btnNormal(name = "SIGN UP", backColor = Color.White, colorText = Color.Black) {
            navController.navigate("Register")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp, start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.Bottom, // Aligns all children to the bottom
        ) {
            Text(
                text = "Version 1.0",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }
}