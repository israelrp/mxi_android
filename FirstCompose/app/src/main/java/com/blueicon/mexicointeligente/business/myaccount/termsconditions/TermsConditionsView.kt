package com.blueicon.mexicointeligente.business.myaccount.termsconditions

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.blueicon.mexicointeligente.components.dialogConfirmView
import com.blueicon.mexicointeligente.components.genericCenterAlignedTopAppBar
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.utils.StorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsConditionsView(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        },
        topBar = {
            genericCenterAlignedTopAppBar("Aviso de privacidad", navController)
        }
    ) {
        ContentTermsConditionsView(navController)
    }
}

@Composable
fun ContentTermsConditionsView(navController: NavController) {

    val context = LocalContext.current
    val dataStore = StorePreferences(context)
    var showDialog by remember { mutableStateOf(false) }
    var isChecked1 by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(false) }
    var isChecked3 by remember { mutableStateOf(false) }

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

            Text(
                text = "Terminos y condiciones",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 14.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )
            {
                Checkbox(
                    checked = isChecked1,
                    onCheckedChange = { newState ->
                        isChecked1 = newState
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.redTitles), // Color cuando está marcado
                        uncheckedColor = Color.Black, // Color cuando está desmarcado
                        checkmarkColor = Color.White // Color de la palomita
                    )
                )

                Text(
                    text = "Acepto el presente Aviso de Privacidad",
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    color = if (isChecked1) colorResource(id = R.color.redTitles) else Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )
            {
                Checkbox(
                    checked = isChecked2,
                    onCheckedChange = { newState ->
                        isChecked2 = newState
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.redTitles), // Color cuando está marcado
                        uncheckedColor = Color.Black, // Color cuando está desmarcado
                        checkmarkColor = Color.White // Color de la palomita
                    )
                )

                Text(
                    text = "Acepto el presente Aviso de Tratamiento de Datos Personales",
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    color = if (isChecked2) colorResource(id = R.color.redTitles) else Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )
            {
                Checkbox(
                    checked = isChecked3,
                    onCheckedChange = { newState ->
                        isChecked3 = newState
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.redTitles), // Color cuando está marcado
                        uncheckedColor = Color.Black, // Color cuando está desmarcado
                        checkmarkColor = Color.White // Color de la palomita
                    )
                )

                Text(
                    text = "Acepto los presentes Términos y condiciones",
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    color = if (isChecked3) colorResource(id = R.color.redTitles) else Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp,
                color = Color.LightGray)

            Button(
                onClick = {
                    showDialog = true
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
                    text = "Aceptar",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.white),
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            }

        }//End Column

        if (showDialog) {
            dialogConfirmView(
                title = "¡Felicidades!",
                message = "La cuenta se ha creado exitosamente",
                isConfirm = false,
                onDismissRequest = {
                    showDialog = false
                },
                onConfirmation = {
                    showDialog = false

                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveHideCreateRealState(true)
                    }

                    navController.navigate("MenuNavigation") {
                        popUpTo(0)
                    }
                }
            )
        }

    }//End Box

}