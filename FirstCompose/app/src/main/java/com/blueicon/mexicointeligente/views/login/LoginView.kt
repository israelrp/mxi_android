package com.blueicon.mexicointeligente.views.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blueicon.mexicointeligente.components.AlertDialogView
import com.blueicon.mexicointeligente.components.LoadingDialog
import com.blueicon.mexicointeligente.components.Space
import com.blueicon.mexicointeligente.components.TitleView
import com.blueicon.mexicointeligente.components.btnGradient
import com.blueicon.mexicointeligente.components.editText
import com.blueicon.mexicointeligente.enums.FieldLogin
import com.blueicon.mexicointeligente.enums.TypeKeyboard
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel) {
    Scaffold(
        /*topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(title = "Home") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                )
            )
        },*/
        /*floatingActionButton = {
            ActionButton()
        }*/
    ) {
        ContentHomeView(navController, viewModel)
    }
}

@ExperimentalMaterial3Api
@Composable
fun ContentHomeView(navController: NavController, viewModel: LoginViewModel) {

    val context = LocalContext.current
    val gradientColors = listOf(
        Color(red = 164, green = 0, blue = 41, alpha = 255),
        Color(red = 41, 18, 44, alpha = 255)
    )
    var showDialog by remember { mutableStateOf(true) }
    var itemModel = viewModel.state

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
        TitleView(title = "Hello", subtitle = "Sign in!")

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
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 60.dp, start = 32.dp, end = 32.dp)
            ) {

                editText(
                    isPassword = false,
                    type = TypeKeyboard.EMAIL,
                    title = "Email",
                    value = itemModel.email,
                    onValueChange = { newText ->
                        viewModel.onValueState(newText, FieldLogin.EMAIL)
                    })

                Space()

                editText(
                    isPassword = true,
                    type = TypeKeyboard.PASSWORD,
                    title = "Password",
                    value = itemModel.password,
                    onValueChange = { newText ->
                        viewModel.onValueState(newText, FieldLogin.PASSWORD)
                    })
            }

            Space()

            Text(
                text = "Forgot Password?",
                fontSize = 20.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(red = 41, 18, 44, alpha = 255),
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp, start = 24.dp)
            )

            Space()
            Space()
            Space()

            btnGradient(
                name = "SIGN IN",
                backColor = Color(red = 164, green = 0, blue = 41, alpha = 255),
                colorText = Color.White
            ) {
                //viewModel.onValueGral("", FieldLogin.SHOWTOAST)

                viewModel.validateFields()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                    text = "Sign up",
                    fontSize = 20.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(red = 41, 18, 44, alpha = 255),
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = true) {
                            navController.navigate("Register")
                        },
                )
            }
        }

        LoadingDialog(
            onDismissRequest = { false },
            isShowing = viewModel.showLoading
        )

        /*AlertDialogExample(
            onDismissRequest = {
                //openAlertDialog.value = false
                               },
            onConfirmation = {
                //openAlertDialog.value = false
                println("Confirmation registered") // Add logic here to handle confirmation.
            },
            dialogTitle = "Alert dialog example",
            dialogText = "This is an example of an alert dialog with buttons.",
            icon = Icons.Default.Info
        )*/

        if (viewModel.showToast) {
            AlertDialogView(
                dialogText = viewModel.msgError,
                isConfirm = false,
                onDismissRequest = {
                    viewModel.onValueError(false)
                    showDialog = false
                },
                onConfirmation = {
                    viewModel.onValueError(false)
                    showDialog = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                onCancel = {
                    viewModel.onValueError(false)
                    showDialog = false
                    println("Cancel")
                }
            )
        }

        if (viewModel.showBiometric) {
            Log.e("TAG", "Si tiene biometrico")
        }

        LaunchedEffect(key1 = viewModel.navigationEvents) {
            viewModel.navigationEvents.collect { route ->
                navController.navigate(route) {
                    popUpTo(0)
                }
            }
        }
    }

}