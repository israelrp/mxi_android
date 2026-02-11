package com.blueicon.mexicointeligente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.blueicon.mexicointeligente.utils.navigation.NavManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = Color(red = 164, green = 0, blue = 41, alpha = 255).toArgb()
        window.navigationBarColor = Color(red = 164, green = 0, blue = 41, alpha = 255).toArgb()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        super.onCreate(savedInstanceState)

        //val loginViewModel: LoginViewModel by viewModels()
        //enableEdgeToEdge()

        setContent {
            /*Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Content()
            }*/
            NavManager(/*loginViewModel*/)
        }
    }
}

//
/*@Composable
fun Content() {
    Column(
        modifier = Modifier
            .fillMaxWidth()//Todo el espacio del ancho
            .wrapContentSize(Alignment.Center),//Centrado
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Space()
        NormalButton()
        Space()
        OnlyTextButton()
        Space()
        FilledButton()
        Space()
        ImageButton()
        Space()
        SwitchButtons()
    }
}*/