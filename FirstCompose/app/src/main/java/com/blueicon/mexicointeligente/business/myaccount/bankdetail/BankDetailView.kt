package com.blueicon.mexicointeligente.business.myaccount.bankdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.components.genericMenu
import com.blueicon.mexicointeligente.components.inputField
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankDetailView(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Configuración", navController)
        }
    ) {
        ContentBankDetailView(navController)
    }
}

@Composable
fun ContentBankDetailView(navController: NavController) {

    val scrollState = rememberScrollState()
    var nombre by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var regimenSelectd by remember { mutableStateOf("") }
    val isCheckedOk = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    val opciones = listOf(
        "General de Ley Personas Morales",
        "Personas Morales con fines no Lucrativos",
        "Sueldos y Salarios e Ingresos asimilados a Salarios",
        "Arrendamiento"
    )

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

                    Text(
                        text = "Datos bancarios",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 14.dp)
                    )

                    Text(
                        text = "Datos bancarios para pago de ganancias por operaciones",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 12.dp, start = 24.dp, end = 24.dp)
                    )

                    // Campo: Nombre del perfil
                    inputField(
                        label = "Beneficiario",
                        value = nombre,
                        type = TypeKeyboard.NORMAL
                    ) { nombre = it }
                    inputField(
                        label = "CLABE interbancaria",
                        value = celular,
                        type = TypeKeyboard.NUMERIC
                    ) { celular = it }
                    inputField(
                        label = "Banco",
                        value = correo,
                        type = TypeKeyboard.NORMAL
                    ) { correo = it }

                }//End Column

            }//End Card

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

                    Text(
                        text = "Facturación",
                        fontSize = 16.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.redTitles),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 14.dp)
                    )

                    Text(
                        text = "¿Quieres recibir los pagos de las ganancias de tu inmobiliaria con IVA?",
                        fontSize = 14.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 12.dp, start = 24.dp, end = 24.dp)
                    )

                    radioFacturacion(modifier = Modifier, isCheckedOk)

                    if (isCheckedOk.value) {
                        Text(
                            text = "Elige tu régimen fiscal (si tienes más de uno,  elige con cuál emitirás la factura)",
                            fontSize = 14.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                        )

                        genericMenu(opciones)
                        {
                            regimenSelectd = it
                        }
                    }

                }//End Column

            }//End Card 2

            Button(
                onClick = {
                    navController.navigate("EnterCode") /*{
                                popUpTo(0)
                            }*/
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.redTitles)
                ),
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 24.dp)
                    .width(240.dp)
                    .height(50.dp)
            )
            {
                Text(
                    text = "Guardar cambios",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }

        }//End Column

    }//End Box

}

@Composable
fun radioFacturacion(modifier: Modifier = Modifier, isCheckedOk: MutableState<Boolean>) {
    val radioOptions = listOf(
        "Sí, estoy consciente que debo enviar las facturas correspondientes a Mexico Inteligente.",
        "No, no quiero recibir los pagos de mis ganancias con IVA."
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier
            .selectableGroup()
            .padding(start = 8.dp, end = 16.dp)
    )
    {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
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