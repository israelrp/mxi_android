package com.blueicon.mexicointeligente.business.myaccount.adminteam

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTeamView(navController: NavController, adminTeamViewModel: AdminTeamViewModel) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Administrar equipo", navController)
        }
    ) {
        ContentAdminTeamView(navController, adminTeamViewModel)
    }
}

@Composable
fun ContentAdminTeamView(navController: NavController, adminTeamViewModel: AdminTeamViewModel) {

    val allItems by adminTeamViewModel.itemsTeam.collectAsState()

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
                        text = "Registra a un colaborador",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )

                    Text(
                        text = "Registra al asesor en tu inmobiliaria",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            navController.navigate("CreateAdvisor")
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = colorResource(id = R.color.white),
                            containerColor = colorResource(id = R.color.redTitles)
                        ),
                        modifier = Modifier
                            .padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 24.dp)
                            .width(240.dp)
                            .height(50.dp)
                    )
                    {
                        Text(
                            text = "Registrar colaborador",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.white),
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                }//End Column

            }//End Card

            Text(
                text = "Colaboradores",
                fontSize = 20.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.redTitles),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                items(allItems, key = { it.id }) { item ->
                    teamRowView(item)
                }
            }

        }//End Column

    }//End Box

}

@Composable
fun teamRowView(item: TeamItem) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White // Set the background color to white
            ),
            shape = RoundedCornerShape(15.dp), // Rounded corners
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), // Shadow
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )
        {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Columna para la información textual
                Column {

                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            //.padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                            .wrapContentSize()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = item.cel,
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            //.padding(top = 8.dp, bottom = 4.dp, start = 8.dp)
                            .wrapContentSize()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = item.date,
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.gray),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            //.padding(top = 8.dp, bottom = 4.dp, start = 8.dp)
                            .wrapContentSize()
                    )
                }

                // Texto interactivo "Editar"
                ClickableText (
                    text = AnnotatedString("Editar"),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Start,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        Log.e("OnboardingView", "Llendo a configuracion")
                        /*CoroutineScope(Dispatchers.IO).launch {
                            dataStores.saveStatusOnboarding(true)
                        }*/

                        //navController.navigate("PhoneNumber")
                    },
                    modifier = Modifier
                        .wrapContentSize()
                )
            }

        }//End Card

    }//End Box

}