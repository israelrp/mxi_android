package com.blueicon.mexicointeligente.business.steps.five

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.business.steps.four.ContentStepFourView
import com.blueicon.mexicointeligente.business.steps.two.servicesComponent
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.genericTopAppBarWithoutBack
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepFiveView(navController: NavController) {

    val focusManager = LocalFocusManager.current
    LocalContext.current

    BackHandler(enabled = true) {
        // No hacer nada o mostrar un mensaje "No puedes salir"
    }

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericTopAppBarWithoutBack("Seguro de Propiedad", navController)
        }
    ) {
        ContentStepFiveView(navController)
    }

}

@Composable
fun ContentStepFiveView(navController: NavController) {

    val scrollState = rememberScrollState()
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
            .background(Color.White)
    )
    {

        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.TopCenter),
        )
        {

            StepIndicator("1", "Información del Inmueble", true)
            StepIndicator("2", "Información del Inmueble", true)
            StepIndicator("3", "Información del Inmueble", true)
            StepIndicator("4", "Información del Inmueble", true)
            StepIndicator("5", "Información del Inmueble", true)
            StepIndicator("6", "Información del Inmueble", false)

        }

        Column(
            modifier = Modifier
                .padding(bottom = 72.dp, top = 64.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
        )
        {

            Text(
                text = "Protege tu inversión (Opcional)",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .background(
                        color = Color(0xFFFEFCE3),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "El seguro de propiedad es opcional. Puedes seleccionar un paquete o continuar sin seguro.",
                    fontSize = 12.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.yellow),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, if (isSelected) colorResource(id = R.color.redTitles) else colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        isSelected = !isSelected
                    }
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) Color(0xFFF8E7E9) else colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Seguro Básico",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Cobertura esencial para tu propiedad",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )

                    servicesComponent("CONTENIDOS 250,000", colorResource(id = R.color.greenStatus))
                    servicesComponent("RESPONSABILIDAD CIVIL FAMILIAS 1,000,000", colorResource(id = R.color.greenStatus))
                    servicesComponent("RESPONSABILIDAD CIVIL ARRENDATARIO", colorResource(id = R.color.greenStatus))
                    servicesComponent("REMOCIÓN ESCOMBROS", colorResource(id = R.color.greenStatus))
                    servicesComponent("CRISTALES", colorResource(id = R.color.greenStatus))
                    servicesComponent("ROBO DE CONTENIDO", colorResource(id = R.color.greenStatus))

                    Text(
                        text = "Precio anual (IVA incluido):",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "$1.00 MXN",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .background(
                                color = colorResource(id = R.color.greenStatus),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Suma asegurada: $1,000,000.00 MXN.",
                            fontSize = 14.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.white),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }

                }//End Column

            }//End Card
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.grayBtnBack)
                ),
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f)
            )
            {
                Text(
                    text = "Anterior",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.black),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Button(
                onClick = {
                    navController.navigate("StepSix")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.redTitles)
                ),
                modifier = Modifier
                    .padding(start = 8.dp, end = 24.dp, top = 24.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f)
            )
            {
                Text(
                    text = "Continuar",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

    }//End Box

}