package com.blueicon.mexicointeligente.business.myaccount.createadvisor

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAdvisorView(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Registrar", navController)
        }
    ) {
        ContentCreateAdvisorView(navController)
    }
}

@Composable
fun ContentCreateAdvisorView(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var isAdvisor by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .background(Color.White)
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
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
                    Text("")
                    // Campo: Nombre del perfil
                    inputField(label = "Nombre", value = nombre, type = TypeKeyboard.NORMAL) { nombre = it }
                    inputField(label = "Número celular", value = celular, type = TypeKeyboard.NUMERIC) { celular = it }
                    inputField(label = "Correo electronico", value = correo, type = TypeKeyboard.EMAIL) { correo = it }


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    )
                    {

                        Text(
                            text = "¿Es administrador?",
                            fontSize = 14.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                        )

                        Switch(
                            checked = isAdvisor,
                            onCheckedChange = {
                                isAdvisor = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colorResource(id = R.color.white),
                                checkedTrackColor = colorResource(id = R.color.redTitles),
                                uncheckedThumbColor = colorResource(id = R.color.grayRegistrada),
                                uncheckedTrackColor = colorResource(id = R.color.white),
                            ),
                            modifier = Modifier
                        )

                    }//End Row

                }//End Column

            }//End Card

            Button(
                onClick = {
                    //navController.navigate("EnterCode")
                    /*{
                                popUpTo(0)
                            }*/
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.redTitles)
                ),
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp)
                    .width(240.dp)
                    .height(50.dp)
            )
            {
                Text(
                    text = "Crear usuario",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            ClickableText (
                text = AnnotatedString("Eliminar usuario"),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.redTitles),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    Log.e("OnboardingView", "Omitiendo onboarding")
                    CoroutineScope(Dispatchers.IO).launch {
                        //dataStores.saveStatusOnboarding(true)
                    }

                    navController.navigate("PhoneNumber") {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 24.dp, bottom = 30.dp, top = 16.dp)
            )

        }//End Column

    }//End Box

}