package com.blueicon.mexicointeligente.business.steps.two

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.business.myaccount.bankdetail.radioFacturacion
import com.blueicon.mexicointeligente.business.myearnings.earningdetail.optionsContent2
import com.blueicon.mexicointeligente.business.myearnings.earningdetail.statusComponent
import com.blueicon.mexicointeligente.business.myearnings.infoView
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.components.genericMenu
import com.blueicon.mexicointeligente.components.genericTopAppBarWithoutBack
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.views.map.bitmapDescriptor
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTwoView(navController: NavController) {

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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
            genericTopAppBarWithoutBack("Paquete de Protección", navController)
        }
    ) {
        ContentStepTwoView(navController)
    }

}

@Composable
fun ContentStepTwoView(navController: NavController) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var costo by remember { mutableStateOf("") }
    var tipoSelected by remember { mutableStateOf("") }
    val isCheckedOk = remember { mutableStateOf(false) }
    val isCheckedPackage = remember { mutableStateOf(false) }

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
            StepIndicator("3", "Información del Inmueble", false)
            StepIndicator("4", "Información del Inmueble", false)
            StepIndicator("5", "Información del Inmueble", false)
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
                text = "Selecciona el paquete que mejor se adapte a tus necesidades",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .background(
                        color = Color(0xFFF8E7E9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Monto de renta protegido por mes: $45.00 MXN",
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.redTitles),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }

            radioPackage(modifier = Modifier, isCheckedPackage)

            Text(
                text = "¿Quién cubrirá el costo de la protección? *",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.Start)
            )

            radioProteccion(modifier = Modifier, isCheckedOk)

        }//End Column

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
                    navController.navigate("StepTree")
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
                    text = "Siguiente",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }//End Box

}

@Composable
fun radioProteccion(modifier: Modifier = Modifier, isCheckedOk: MutableState<Boolean>) {
    val radioOptions = listOf(
        "100% propietario.",
        "100% inquilino",
        "50% inquilino - 50% propietario"
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier
            .selectableGroup()
            .padding(start = 8.dp, end = 16.dp, bottom = 24.dp)
    )
    {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null, // null recommended for accessibility with screen readers
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(id = R.color.redTitles), // Color cuando está seleccionado
                        unselectedColor = Color.Black // Color cuando no está seleccionado
                    )
                )

                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 16.dp),
                    color = if (text == selectedOption) colorResource(id = R.color.redTitles) else Color.Black,
                )

                isCheckedOk.value = selectedOption != radioOptions[1]
            }
        }
    }
}

@Composable
fun radioPackage(modifier: Modifier = Modifier, isCheckedPackage: MutableState<Boolean>) {
    val radioOptions = listOf(
        "Paquete Básico",
        "Paquete Cobertura Amplia"
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier
            .selectableGroup()
            .padding(start = 8.dp, end = 8.dp)
    )
    {
        radioOptions.forEach { text ->

            Card(
                shape = RoundedCornerShape(15.dp), // Rounded corners
                border = BorderStroke(1.dp, if (text == selectedOption) colorResource(id = R.color.redTitles) else colorResource(id = R.color.grayRegistrada)), // Border
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    ),
            )
            {
                Column(
                    modifier = Modifier
                        .background(
                            color = if (text == selectedOption) Color(0xFFF8E7E9) else colorResource(id = R.color.white),
                        )
                )
                {
                    Text(
                        text = text,
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
                        text = "Servicios jurídicos, investigación y asistencia 24/7",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )

                    servicesComponent("Servicios jurídicos", colorResource(id = R.color.redTitles))
                    servicesComponent("Investigación de prospecto y fiador", colorResource(id = R.color.redTitles))
                    servicesComponent("Contrato de arrendamiento elaborado por especialistas", colorResource(id = R.color.redTitles))
                    servicesComponent("Asistencia y respaldo jurídico 24/7", colorResource(id = R.color.redTitles))

                    Text(
                        text = "No hay precio configurado para este monto de renta",
                        fontSize = 13.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )

                }//End Column

                isCheckedPackage.value = selectedOption != radioOptions[1]

            }//End Card

        }//End foreach

    }//End Column
}

@Composable
fun servicesComponent(title: String, color: Color) {

    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Image(
            painter = painterResource(R.drawable.cheque), // Reference your image resource
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
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
        )
    }

}