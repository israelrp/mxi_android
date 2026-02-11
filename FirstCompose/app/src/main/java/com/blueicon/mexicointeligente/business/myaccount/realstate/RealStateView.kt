package com.blueicon.mexicointeligente.business.myaccount.realstate

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.components.genericMenu
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RealStateView(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Nueva inmobiliaria", navController)
        }
    ) {
        ContentRealStateViewView(navController)
    }
}

@Composable
fun ContentRealStateViewView(navController: NavController) {

    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var numAdvisors by remember { mutableStateOf("0") }
    var selectedIndex by remember { mutableStateOf(-1) }
    var estadoSelected by remember { mutableStateOf("") }

    val opciones = listOf(
        "Ciudad de México",
        "Jalisco",
        "Puebla",
        "Otro"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .background(Color.White)
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
        )
        {

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
                //border = BorderStroke(2.dp, Color.Blue), // Border
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp)
                    .fillMaxWidth() // Ajusta el ancho al máximo disponible
                    .wrapContentHeight(), // EL AJUSTE DINÁMICO
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
                )
                {
                    Text(
                        text = "Ingresa tus datos y crea tu cuenta de asesor inmobiliario.",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 14.dp, start = 24.dp, end = 24.dp)
                    )

                    // Campo: Nombre del perfil
                    inputField(
                        label = "Nombre",
                        value = name,
                        type = TypeKeyboard.NORMAL
                    ) { name = it }

                    inputField(
                        label = "Correo electronico",
                        value = email,
                        type = TypeKeyboard.EMAIL
                    ) { email = it }

                    inputField(
                        label = "Empresa",
                        value = company,
                        type = TypeKeyboard.NORMAL
                    ) { company = it }

                    inputField(
                        label = "Número de asesores en tu inmobiliaria",
                        value = company,
                        type = TypeKeyboard.NUMERIC
                    ) { company = it }

                    Text(
                        text = "Selecciona la plaza donde operas principalmente",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                    )
                    genericMenu(opciones)
                    {
                        estadoSelected = it
                    }

                }//End Column

            }//End Card

            Button(
                onClick = {
                    navController.navigate("TermsConditions") /*{
                                popUpTo(0)
                            }*/
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.redTitles)
                ),
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 24.dp)
                    .width(240.dp)
                    .height(50.dp)
            )
            {
                Text(
                    text = "Registrarme",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }

        }//End Column

    }//End Box

}