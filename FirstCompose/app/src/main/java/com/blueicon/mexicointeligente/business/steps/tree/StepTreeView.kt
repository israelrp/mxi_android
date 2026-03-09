package com.blueicon.mexicointeligente.business.steps.tree

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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.blueicon.mexicointeligente.business.steps.two.radioProteccion
import com.blueicon.mexicointeligente.components.StepIndicator
import com.blueicon.mexicointeligente.components.genericTopAppBarWithoutBack
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTreeView(navController: NavController) {

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
            genericTopAppBarWithoutBack("Detalles de renta", navController)
        }
    ) {
        ContentStepTreeView(navController)
    }

}

@Composable
fun ContentStepTreeView(navController: NavController) {

    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    val isCheckedFirst = remember { mutableStateOf(false) }
    val firstOptions = listOf(
        "Sí, un tercero será fiador",
        "No"
    )
    val isCheckedSecond = remember { mutableStateOf(false) }
    val secondOptions = listOf(
        "Sí",
        "No"
    )

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
                text = "Ingresa los datos requeridos para continuar con la operación",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 24.dp)
                    .fillMaxWidth()
            )

            inputField(label = "Nombre del inquilino(a) *", value = name,
                type = TypeKeyboard.NORMAL) { name = it }

            Text(
                text = "¿El propietario solicita un fiador o una propiedad en garantía?",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.Start)
            )

            radioGeneric(modifier = Modifier, isCheckedFirst, firstOptions)

            Text(
                text = "¿El propietario solicita un obligado solidario?",
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .align(Alignment.Start)
            )

            radioGeneric(modifier = Modifier, isCheckedSecond, secondOptions)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    .background(
                        color = Color(0xFFECF4FF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Vamos a validar la identidad del inquilino y su número de celular para enviarle la información del contrato. Esto es necesario para continuar con el proceso de manera segura.",
                    fontSize = 12.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.blue),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }
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
                    navController.navigate("StepFour")
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
fun radioGeneric(modifier: Modifier = Modifier, isChecked: MutableState<Boolean>, radiosOpt: List<String>) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radiosOpt[0]) }

    Column(
        modifier
            .selectableGroup()
            .padding(start = 8.dp, end = 16.dp, bottom = 24.dp)
    )
    {
        radiosOpt.forEach { text ->
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

                isChecked.value = selectedOption != radiosOpt[1]
            }
        }
    }
}