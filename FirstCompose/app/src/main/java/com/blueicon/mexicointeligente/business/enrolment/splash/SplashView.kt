package com.blueicon.mexicointeligente.business.enrolment.splash

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.utils.StorePreferences
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashView(navController: NavController) {

    var isVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val dataStore = StorePreferences(context)
    val store = dataStore.getStatusOnboarding.collectAsState(initial = false)
    val userId = dataStore.getUserId.collectAsState(initial = 0)

    Scaffold {

        /*LaunchedEffect(key1 = true) {
            delay(1500)

            if (userId.value != 0) {
                navController.navigate("MainTwo") {
                    popUpTo(0)
                }
            } else {
                navController.navigate(if (store.value) "Login" else "Onboarding") {
                    popUpTo(0)
                }
            }
        }*/

        LaunchedEffect(key1 = true) {
            delay(1500)
            isVisible = false

            delay(500)
            if (userId.value != 0) {
                navController.navigate("MenuNavigation") {
                    popUpTo(0)
                }
            } else {
                navController.navigate(if (store.value) "PhoneNumber" else "Onboarding") {
                    popUpTo(0)
                }
            }
        }

        ContentSplashView(navController, isVisible)
    }
}

@ExperimentalMaterial3Api
@Composable
fun ContentSplashView(navController: NavController, isVisible: Boolean) {

    val gradientColors = listOf(
        Color(red = 164, green = 0, blue = 41, alpha = 255),
        Color(red = 41, 18, 44, alpha = 255)
    )
    val alpha by animateFloatAsState(if (isVisible) 1f else 0f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            //Degradado
            /*.background(
                brush = Brush.horizontalGradient(
                    colors = gradientColors,
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY // Ends at the bottom of the column
                )
            )*/
    ) {

        Image(
            painter = painterResource(id = R.drawable.background), // Reference your image resource
            contentDescription = "A description of the image", // Mandatory for accessibility
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
            // Optional parameters: contentScale, colorFilter, alpha, etc.
        )

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo), // Reference your image resource
                contentDescription = "A description of the image", // Mandatory for accessibility
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(240.dp, height = 140.dp)
                    .fillMaxWidth()
                //.alpha(alpha)
                // Optional parameters: contentScale, colorFilter, alpha, etc.
            )
        }

    }

}