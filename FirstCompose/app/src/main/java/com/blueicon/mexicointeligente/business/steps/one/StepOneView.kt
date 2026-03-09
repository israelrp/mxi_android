package com.blueicon.mexicointeligente.business.steps.one

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.business.home.menuCard
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.components.genericMenu
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
fun StepOneView(navController: NavController) {

    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Información del Inmueble", navController)
        }
    ) {
        ContentStepOneView(navController)
    }

}

@Composable
fun ContentStepOneView(navController: NavController) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var costo by remember { mutableStateOf("") }
    var tipoSelected by remember { mutableStateOf("") }
    val opciones = listOf(
        "Habitacional",
        "Terreno",
        "Oficina",
        "Bodega",
        "Local comercial",
        "Bodega",
    )
    val mapStyleOptions = try {
        MapStyleOptions.loadRawResourceStyle(context,  R.raw.map_style_empty)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
    val mapProperties = MapProperties(
        mapStyleOptions = mapStyleOptions,
        mapType = MapType.NORMAL,
        //mapOptions = MapOptions().mapColorScheme(MapColorScheme.LIGHT),
        // Puedes añadir otras propiedades aquí si es necesario
    )
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()
    val markerState: MarkerState = rememberMarkerState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
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
            StepIndicator("2", "Información del Inmueble", false)
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
                text = "Ingresa los datos de la propiedad en renta",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            inputField(label = "¿Cuál es el costo de renta mensual? *", value = costo,
                type = TypeKeyboard.NUMERIC) { costo = it }

            GoogleMap(
                properties = mapProperties,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                    .fillMaxWidth().height(230.dp),
                cameraPositionState = cameraPositionState
            )
            {
                Marker(
                    state = markerState,
                    title = "Aqui estoy",
                    onClick = {
                        true
                    },
                    icon = bitmapDescriptor(
                        context, R.drawable.marker
                    )
                )
            }//End GoogleMap

            inputField(label = "Calle *", value = costo,
                type = TypeKeyboard.NORMAL) { costo = it }

            inputField(label = "Número interior *", value = costo,
                type = TypeKeyboard.NORMAL) { costo = it }

            inputField(label = "Colonia *", value = costo,
                type = TypeKeyboard.NORMAL) { costo = it }

            inputField(label = "Municipio/Alcaldía *", value = costo,
                type = TypeKeyboard.NORMAL) { costo = it }

            inputField(label = "Estado *", value = costo,
                type = TypeKeyboard.NORMAL) { costo = it }

            inputField(label = "Código Postal *", value = costo,
                type = TypeKeyboard.NUMERIC) { costo = it }

            Text(
                text = "¿Qué uso se dará al inmueble en renta? *",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )

            genericMenu(opciones)
            {
                tipoSelected = it
            }

        }//End Column

        Button(
            onClick = {
                navController.navigate("StepTwo") /*{
                                popUpTo(0)
                            }*/
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
                text = "Siguiente",
                fontSize = 18.sp,
                color = colorResource(id = R.color.white),
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }

    }//End Box

}