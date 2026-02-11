package com.blueicon.mexicointeligente.business.enrolment.entercode

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.business.enrolment.phonenumber.PhoneNumberViewModel
import com.blueicon.mexicointeligente.components.backBtn
import com.blueicon.mexicointeligente.ui.theme.openSansFamily
import com.blueicon.mexicointeligente.utils.StorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterCodeView(navController: NavController, viewModel: PhoneNumberViewModel) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        }
    ) {
        ContententerCodeView(navController, viewModel)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun ContententerCodeView(navController: NavController, viewModel: PhoneNumberViewModel) {

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var completeCode by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(30) }
    var isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isRunning) {
        if (isRunning && timeLeft > 0) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            ),
        verticalArrangement = Arrangement.Top,
    )
    {
        backBtn(R.drawable.atras) {
            navController.popBackStack()
        }

        Column(
            modifier = Modifier
                //.fillMaxSize()
                .padding(top = 30.dp)
        ) {

            Text(
                text = "Bienvenido a tu App",
                fontSize = 20.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.ic_logo), // Reference your image resource
                contentDescription = "A description of the image", // Mandatory for accessibility
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(
                    color = colorResource(id = R.color.redTitles),
                    blendMode = BlendMode.SrcIn
                ),
                modifier = Modifier
                    .size(width = 300.dp, height = 65.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp)
            )

            Text(
                text = "Ingresa el código que te enviamos",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            Text(text = buildAnnotatedString {
                append("al número ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("22 2164 2245")
                }
            }
                ,fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            )

            //Campos de texto codigo sms
            smsFieldCode(completeCode, navController)

            if (isRunning) {
                Text(
                    text = "No recibí el código, enviar nuevamente en ${(timeLeft % 60).toString().padStart(2, '0')}s",
                    fontSize = 12.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.red),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            } else {
                Text(
                    text = "No recibí el código, enviar nuevamente.",
                    fontSize = 13.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.red),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

        }//End Column

    }//End Column
}

@ExperimentalMaterial3Api
@Composable
fun smsFieldCode(completeCode: String, navController: NavController) {

    val context = LocalContext.current
    val dataStore = StorePreferences(context)

    val focusManager = LocalFocusManager.current
    var completeCodeH = completeCode
    var codeOne by remember { mutableStateOf("") }
    var codeTwo by remember { mutableStateOf("") }
    var codeThree by remember { mutableStateOf("") }
    var codeFour by remember { mutableStateOf("") }
    var codeFive by remember { mutableStateOf("") }
    var codeSix by remember { mutableStateOf("") }

    val focusOne = remember { FocusRequester() }
    val focusTwo = remember { FocusRequester() }
    val focusThree = remember { FocusRequester() }
    val focusFour = remember { FocusRequester() }
    val focusFive = remember { FocusRequester() }
    val focusSix = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .padding(start = 36.dp, end = 36.dp, top = 48.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        genericSmsTxt(
            value = codeOne,
            focusRequester = focusOne,
            onValueChange = { newText ->
                codeOne = newText
                if (newText != "") {
                    focusTwo.requestFocus()
                }
            })

        genericSmsTxt(
            value = codeTwo,
            focusRequester = focusTwo,
            onValueChange = { newText ->
                codeTwo = newText
                if (newText != "") {
                    focusThree.requestFocus()
                } else {
                    focusOne.requestFocus()
                }
            })

        genericSmsTxt(
            value = codeThree,
            focusRequester = focusThree,
            onValueChange = { newText ->
                codeThree = newText
                if (newText != "") {
                    focusFour.requestFocus()
                } else {
                    focusTwo.requestFocus()
                }
            })

        genericSmsTxt(
            value = codeFour,
            focusRequester = focusFour,
            onValueChange = { newText ->
                codeFour = newText
                if (newText != "") {
                    focusFive.requestFocus()
                } else {
                    focusThree.requestFocus()
                }
            })

        genericSmsTxt(
            value = codeFive,
            focusRequester = focusFive,
            onValueChange = { newText ->
                codeFive = newText
                if (newText != "") {
                    focusSix.requestFocus()
                } else {
                    focusFour.requestFocus()
                }
            })

        genericSmsTxt(
            value = codeSix,
            focusRequester = focusSix,
            onValueChange = { newText ->
                codeSix = newText
                if (newText != "") {
                    focusManager.clearFocus()

                    completeCodeH = "${codeOne}${codeTwo}${codeThree}" +
                            "${codeFour}${codeFive}${codeSix}"
                    Log.e("smsFieldCode", "CompleteCode ${completeCodeH}")

                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveId(12)
                        dataStore.saveName("Jorge Alberto")
                        dataStore.savePhone("2221642245")
                    }

                    navController.navigate("MenuNavigation") {
                        popUpTo(0)
                    }
                } else {
                    focusFive.requestFocus()
                }
            })

    }

}
@ExperimentalMaterial3Api
@Composable
fun genericSmsTxt(value: String, focusRequester: FocusRequester, onValueChange: (String) -> Unit) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(8.dp), // Esquinas redondeadas
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.redTitles), // Color del borde cuando está enfocado
            unfocusedBorderColor = if(value != "") colorResource(id = R.color.redTitles) else Color.Gray, // Color del borde cuando no está enfocado
        ),
        textStyle = TextStyle(
            fontSize = 14.sp, fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Center // Centers the input text horizontally
        ),
        modifier = Modifier
            .width(47.dp)
            .height(47.dp)
            .focusRequester(focusRequester),
    )
}