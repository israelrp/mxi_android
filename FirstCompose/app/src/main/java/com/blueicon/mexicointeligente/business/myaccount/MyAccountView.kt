package com.blueicon.mexicointeligente.business.myaccount

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.dialogConfirmView
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.utils.StorePreferences

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountView(navController: NavController) {
    Scaffold(

    ) {
        ContentMyAccountView(navController)
    }
}

@Composable
fun ContentMyAccountView(navController: NavController) {

    val context = LocalContext.current
    val dataStore = StorePreferences(context)
    val store = dataStore.getHideCreateRealState.collectAsState(initial = false)
    var showDialog by remember { mutableStateOf(false) }
    var showDialogRealState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(Color.White)
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            //Card 1
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
                    verticalArrangement = Arrangement.Center, // Centrado vertical
                    horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
                )
                {

                    Image(
                        painter = painterResource(R.drawable.usuario), // Reference your image resource
                        contentDescription = null, // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 8.dp)
                            .clip(CircleShape)
                        //.border(0.dp, Color.Black, CircleShape)
                        // Optional parameters: contentScale, colorFilter, alpha, etc.
                    )

                    businessCardHeader()

                    Image(
                        painter = painterResource(id = R.drawable.ic_logo), // Reference your image resource
                        contentDescription = "A description of the image", // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(colorResource(R.color.red)),
                        modifier = Modifier
                            .size(100.dp, height = 22.dp)
                            .fillMaxWidth()
                    )

                    ClickableText (
                        text = AnnotatedString("Configuración"),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.redTitles),
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline
                        ),
                        onClick = {
                            Log.e("OnboardingView", "Llendo a configuracion")
                            /*CoroutineScope(Dispatchers.IO).launch {
                                dataStores.saveStatusOnboarding(true)
                            }*/

                            navController.navigate("Configuration")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp, top = 16.dp)
                    )
                }
            }

            //Card 2
            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
                //border = BorderStroke(2.dp, Color.Blue), // Border
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 30.dp)
                    .fillMaxWidth() // Ajusta el ancho al máximo disponible
                    .wrapContentHeight(), // EL AJUSTE DINÁMICO
            )
            {

                Column(
                    modifier = Modifier
                        .background(Color.White)
                )
                {
                    optionsContent("Datos bancarios y facturación", R.drawable.bank, onClick = {
                        navController.navigate("BankDetail")
                    })

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color.LightGray)

                    if (!store.value) {
                        optionsContent("Crear nueva inmobiliaria", R.drawable.group, onClick = {
                            showDialogRealState = true
                        })
                    } else {
                        optionsContent("Administrar inmobiliaria", R.drawable.group, onClick = {
                            navController.navigate("AdminTeam")
                        })
                    }

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color.LightGray)

                    optionsContent("Cerrar sesión", R.drawable.closesesion, onClick = {
                        showDialog = true
                    })
                }

            }

        }//End Column

        if (showDialog) {
            dialogConfirmView(
                title = "¿Estas seguro de cerrar tu sesión?",
                message = "",
                isConfirm = true,
                onDismissRequest = {
                    showDialog = false
                },
                onConfirmation = {
                    showDialog = false
                }
            )
        }

        if (showDialogRealState) {
            dialogConfirmView(
                title = "¿Qué es una cuenta de inmobiliaria?",
                message = "Con una cuenta de inmobiliaria podrás gestionar a tu equipo de asesores, revisar el avance de sus operaciones y más.",
                isConfirm = true,
                onDismissRequest = {
                    showDialogRealState = false
                },
                onConfirmation = {
                    showDialogRealState = false
                    navController.navigate("RealState")
                }
            )
        }

    }//End Box

}

@Composable
fun businessCardHeader() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {

        Text(
            text = "Jorge Alberto",
            fontSize = 16.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            //modifier = Modifier
            //    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            //    .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(10.dp)
                .background(colorResource(id = R.color.gray), shape = CircleShape)
        )

        Text(
            text = "Asesor",
            fontSize = 16.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.grayText),
            //textAlign = TextAlign.Start,
            //modifier = Modifier
            //    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            //    .fillMaxWidth()
        )
    }

}

@Composable
fun optionsContent(title: String, icon: Int, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null // <--- Esto quita el efecto negro/ripple
            ),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Image(
            painter = painterResource(icon), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(23.dp)
                //.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 13.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
        )

        Image(
            painter = painterResource(R.drawable.arrowrigth), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(18.dp)
            //.padding(top = 8.dp)
        )

    }

}