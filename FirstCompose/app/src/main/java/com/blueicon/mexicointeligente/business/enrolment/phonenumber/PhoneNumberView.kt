package com.blueicon.mexicointeligente.business.enrolment.phonenumber

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhonenumberView(navController: NavController, viewModel: PhoneNumberViewModel) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This clears the focus from the current text field, which dismisses the keyboard
                focusManager.clearFocus()
            })
        }
    ) {
        ContentPhoneNumberView(navController, viewModel)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun ContentPhoneNumberView(navController: NavController, viewModel: PhoneNumberViewModel) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            ),
        verticalArrangement = Arrangement.Top,
    )
    {

        Column(
            modifier = Modifier
                //.fillMaxSize()
                .padding(top = 100.dp)
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
                text = "Ingresa tu celular para comenzar",
                fontSize = 16.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            //Campo para capturar el celular
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 32.dp, start = 40.dp, end = 40.dp)
                    .border(
                        width = 1.dp, // Specify the border thickness
                        color = Color.Gray, // Specify the border color
                        shape = RoundedCornerShape(8.dp) // Optional: add rounded corners
                    )
                    .height(45.dp),
                horizontalArrangement = Arrangement.Center, // Centrado horizontal
                verticalAlignment = Alignment.CenterVertically //
            )
            {

                //Bandera | +52
                Row(
                    modifier =
                        Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.mexico), // Reference your image resource
                        contentDescription = "A description of the image", // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 8.dp)
                            .size(width = 30.dp, height = 25.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(width = 1.dp, height = 30.dp)
                            .background(
                                Color.Gray
                            )
                    ) { }

                    Text(
                        text = "+52",
                        fontSize = 13.sp,
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )

                }

                BasicTextField(
                    value = phone,
                    onValueChange = { newText ->
                        phone = newText
                    },
                    decorationBox = { innerTextField ->
                        if (phone.isEmpty()) {
                            Text(
                                "Ingresa tu número celular a 10 digitos",
                                fontSize = 13.sp,
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                        }
                        // <-- Add this
                        innerTextField()
                    },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .wrapContentSize(),
                    singleLine = true, // Optional: Restrict to a single line,
                    cursorBrush = SolidColor(Color.Black),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        capitalization = KeyboardCapitalization.None
                    ),
                    textStyle = TextStyle(
                        fontSize = 13.sp, fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                if (phone.length > 9) {
                    focusManager.clearFocus()

                    Image(
                        painter = painterResource(id = R.drawable.success), // Reference your image resource
                        contentDescription = "A description of the image", // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(width = 20.dp, height = 20.dp)
                    )
                }

            }//End Row

            //Boton
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center, // Centrado horizontal
                verticalAlignment = Alignment.CenterVertically //
            ) {
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
                        .padding(start = 32.dp, end = 32.dp)
                        .width(240.dp)
                        .height(50.dp)
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

        }//End Column

    }//End Column
}