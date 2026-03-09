package com.blueicon.mexicointeligente.business.steps.four

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
import com.blueicon.mexicointeligente.business.steps.tree.ContentStepTreeView
import com.blueicon.mexicointeligente.business.steps.tree.radioGeneric
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.datePicker
import com.blueicon.mexicointeligente.components.genericTopAppBarWithoutBack
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.components.textAreaField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepFourView(navController: NavController) {

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
            genericTopAppBarWithoutBack("Detalles del Contrato", navController)
        }
    ) {
        ContentStepFourView(navController)
    }

}

@Composable
fun ContentStepFourView(navController: NavController) {

    val scrollState = rememberScrollState()
    var metros by remember { mutableStateOf("") }
    var fechaContrato by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

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
            StepIndicator("2", "Información del Inmueble", true)
            StepIndicator("3", "Información del Inmueble", true)
            StepIndicator("4", "Información del Inmueble", true)
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
                text = "Información adicional sobre el arrendamiento",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            inputField(label = "M² de renta", value = metros,
                type = TypeKeyboard.NUMERIC) { metros = it }

            datePicker(label = "Fecha de inicio del contrato *", fechaContrato) { fechaContrato = it }

            textAreaField(label = "Comentarios adicionales (opcional)", "Agrega cualquier comentario relevante sobre el contrato...",
                value = comentarios, type = TypeKeyboard.NORMAL) { comentarios = it }
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
                    navController.navigate("StepFive")
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