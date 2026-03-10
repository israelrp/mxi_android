package com.blueicon.mexicointeligente.business.steps.six

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.genericTopAppBarWithoutBack
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepSixView(navController: NavController) {

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
            genericTopAppBarWithoutBack("Resumen de la Operación", navController)
        }
    ) {
        ContentStepSixView(navController)
    }

}

@Composable
fun ContentStepSixView(navController: NavController) {

    val scrollState = rememberScrollState()

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
            StepIndicator("6", "Información del Inmueble", true)

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
                text = "Revisa toda la información antes de registrar",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Información del Inmueble",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Dirección: AND MARTIRES 7 DE ENERO UH MARTIRES 7 DE ENERO Edif 10, Centro",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Interior: 4545",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Estado: Puebla",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Municipio: San Martín Texmelucan",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "CP: 74000",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Uso: Local comercial",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Renta mensual: $45.00",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }//End Column
            }//End Card

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Protección",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Paquete: Paquete Cobertura Amplia",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Pagado por: 50% Inquilino - 50% Propietario",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }//End Column
            }

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Inquilino y Garantías",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Inquilino: Jorge A.",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Requiere fiador: Sí",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Requiere obligado solidario: No",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }//End Column
            }

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Detalles del Contrato",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Área: 23 m²",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Fecha de inicio: 7/3/2026",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }//End Column
            }

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = "Seguro de Propiedad",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Paquete: Seguro Básico",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Costo anual: $1.00 MXN (IVA incluido)",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Suma asegurada: $1,000,000.00 MXN",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }//End Column
            }
        }

        Button(
            onClick = {
                navController.navigate("MenuNavigation") {
                    popUpTo(0)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.redTitles)
            ),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
        )
        {
            Text(
                text = "Registrar operación",
                fontSize = 18.sp,
                color = colorResource(id = R.color.white),
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }

    }//End Box
}