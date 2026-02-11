package com.blueicon.mexicointeligente.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.components.Space
import com.blueicon.mexicointeligente.components.TitleView
import com.blueicon.mexicointeligente.components.btnGradient
import com.blueicon.mexicointeligente.components.editText
import com.blueicon.mexicointeligente.enums.TypeKeyboard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(navController: NavController) {
    Scaffold {
        ContentRegisterView(navController)
    }
}

@ExperimentalMaterial3Api
@Composable
fun ContentRegisterView(navController: NavController) {
    var emailUser by remember { mutableStateOf("") }
    var passwordUser by remember { mutableStateOf("") }
    var repeatPasswordUser by remember { mutableStateOf("") }
    var nameUser by remember { mutableStateOf("") }
    val gradientColors = listOf(Color(red = 164, green = 0, blue = 41, alpha = 255), Color(red = 41, 18, 44, alpha = 255))
    val scrollState = rememberScrollState()

    //Contenedor
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = gradientColors,
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY // Ends at the bottom of the column
                )
            ),
        verticalArrangement = Arrangement.Top,
    ) {
        TitleView(title = "Create your", subtitle = "Account")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 60.dp, start = 32.dp, end = 32.dp)
            ) {

                editText(isPassword = false, type = TypeKeyboard.NORMAL, title = "Full name", value = nameUser, onValueChange = {
                        newText -> nameUser = newText
                })

                Space()

                editText(isPassword = false, type = TypeKeyboard.EMAIL, title = "Email", value = emailUser, onValueChange = {
                        newText -> emailUser = newText
                })

                Space()

                editText(isPassword = true, type = TypeKeyboard.PASSWORD, title = "Password", value = passwordUser, onValueChange = {
                        newText -> passwordUser = newText
                })

                Space()

                editText(isPassword = true, type = TypeKeyboard.PASSWORD, title = "Confirm password", value = repeatPasswordUser, onValueChange = {
                        newText -> repeatPasswordUser = newText
                })
            }

            Space()
            Space()
            Space()

            btnGradient(name = "SIGN UP", backColor = Color(red = 164, green = 0, blue = 41, alpha = 255), colorText = Color.White) {

            }

            /*Space()
            Space()
            Space()
            Space()
            Space()

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 32.dp, start = 32.dp, end = 32.dp),
                verticalArrangement = Arrangement.Bottom, // Aligns all children to the bottom
            ) {
                Text(
                    text = "Dont have account?",
                    fontSize = 18.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(red = 41, 18, 44, alpha = 255),
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(red = 41, 18, 44, alpha = 255),
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = true) {
                            navController.navigate("Home")
                        }
                )
            }*/
        }
    }
}