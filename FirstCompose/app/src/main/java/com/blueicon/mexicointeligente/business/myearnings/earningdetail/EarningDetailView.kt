package com.blueicon.mexicointeligente.business.myearnings.earningdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
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
import com.blueicon.mexicointeligente.business.myearnings.infoView
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningDetailView(navController: NavController, step: Int) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Detalle de operación", navController)
        }
    ) {
        ContentEarningDetailView(navController, step)
    }
}

@Composable
fun ContentEarningDetailView(navController: NavController, step: Int) {

    var showMoreInfo by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

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
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 32.dp)
                    .fillMaxWidth() // Ajusta el ancho al máximo disponible
                    .wrapContentHeight(), // EL AJUSTE DINÁMICO
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    ) {

                    }

                    infoView(R.drawable.identificador, "Operación ID", "Valor valor")
                    infoView(R.drawable.location, "Dirección", "Valor valor")
                    infoView(R.drawable.home, "Uso del inmueble", "Valor valor")
                    infoView(R.drawable.inquilino, "Inquilino", "Valor valor")
                    infoView(R.drawable.money, "Monto de la renta mensual", "Valor valor")

                    if (showMoreInfo) {
                        infoView(R.drawable.money, "Pena convencional", "Valor valor")
                        infoView(R.drawable.money, "Depósito en garantía", "Valor valor")
                        infoView(R.drawable.calendar, "Fecha de inicio de vigencia", "Valor valor")
                        infoView(
                            R.drawable.calendar,
                            "Fecha de entrega de la propiedad",
                            "Valor valor"
                        )
                    }

                    ClickableText(
                        text = AnnotatedString(if (!showMoreInfo) "Más información" else "Menos información"),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.redTitles),
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline
                        ),
                        onClick = {
                            showMoreInfo = !showMoreInfo
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 24.dp, bottom = 30.dp, top = 8.dp)
                    )

                }//End Column

            }//End Card

            Text(
                text = "Proceso de renta",
                fontSize = 20.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.redTitles),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Da click sobre cad tarjeta para ver el detalle de los pasos de la operación",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 13.dp, start = 24.dp, end = 24.dp)
            )

            progressLine(7, step)

            Text(
                text = "Perfiles e investigación",
                fontSize = 18.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
            stepContent(R.drawable.inquilino, "Perfil de inquilino",
                "Falta crear el perfil desde portal de inquilino", "Pendiente")
            stepContent(R.drawable.search, "Investigación",
                "Se necesitan perfiles completos y pago de investigación", "No disponible")

            Text(
                text = "Contratos",
                fontSize = 18.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
            stepContent(R.drawable.propietario, "Información de propietario",
                "Falta subir la información de propietario", "Pendiente")
            stepContent(R.drawable.ventapoliza, "Contratos",
                "Estará disponible más adelante", "No disponible")

            Text(
                text = "Pagos y firma",
                fontSize = 18.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )
            stepContent(R.drawable.money, "Pagos",
                "Pendiente", "Pendiente")
            stepContent(R.drawable.home, "Firma",
                "Podrás agendar la firma más adelante en el proceso", "No disponible")
            stepContent(R.drawable.home, "Detalle de producto",
                "El producto ha sido confirmado", "Completo")
        }//End Column

    }//End Box

}

@Composable
fun progressLine(
    totalSegments: Int,
    completedSegments: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 13.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        // La barra con sus segmentos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre segmentos
        ) {
            repeat(totalSegments) { index ->
                val isCompleted = index < completedSegments
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(50)) // Bordes redondeados como en la foto
                        .background(
                            if (isCompleted) colorResource(id = R.color.redTitles) // Rojo para completados
                            else Color(0xFFB0B0B0) // Gris para pendientes
                        )
                )
            }
        }

        //Spacer(modifier = Modifier.height(8.dp))

        // Texto indicador
        Text(
            text = "$completedSegments / $totalSegments completadas",
            fontSize = 14.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.grayText),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun stepContent(
    icon: Int,
    title: String,
    description: String,
    status: String,
) {
    var colorStroke by remember { mutableStateOf(Color.White) }
    var iconStatus by remember { mutableStateOf(R.drawable.home) }

    when (status) {
        "Pendiente" -> {
            colorStroke = colorResource(id = R.color.orangeStatus)
            iconStatus = R.drawable.alerta
        }
        "No disponible" -> {
            colorStroke = colorResource(id = R.color.grayStatus)
            iconStatus = R.drawable.prohibicion
        }
        "Completo" -> {
            colorStroke = colorResource(id = R.color.greenStatus)
            iconStatus = R.drawable.cheque
        }
    }

    Card(
        shape = RoundedCornerShape(15.dp), // Rounded corners
        border = BorderStroke(2.dp, colorStroke), // Border
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
            .fillMaxWidth() // Ajusta el ancho al máximo disponible
            .wrapContentHeight(), // EL AJUSTE DINÁMICO
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        )
        {
            optionsContent2(title, icon)

            statusComponent(status, iconStatus, colorStroke)

            Text(
                text = description,
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )

        }//End Column

    }

}

@Composable
fun optionsContent2(title: String, icon: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Image(
            painter = painterResource(icon), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(25.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 14.sp,
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
                .size(22.dp)
        )

    }

}

@Composable
fun statusComponent(title: String, icon: Int, color: Color) {

    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Image(
            painter = painterResource(icon), // Reference your image resource
            contentDescription = null, // Mandatory for accessibility
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .size(15.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            fontSize = 13.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
            color = color,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
        )
    }

}