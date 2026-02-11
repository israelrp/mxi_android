package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                fontSize = 18.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(red = 164, green = 0, blue = 41, alpha = 255),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        text = {
            Text(
                text = dialogText,
                fontSize = 14.sp,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun AlertDialogView(
    dialogText: String,
    isConfirm: Boolean,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(20.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                Modifier
                    .background(Color.White)
            ) {

                Text(
                    text = "Aviso",
                    fontSize = 22.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(red = 164, green = 0, blue = 41, alpha = 255),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )

                Text(
                    text = dialogText,
                    fontSize = 16.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    if (isConfirm) {
                        Button(
                            onClick = {
                                onCancel()
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(red = 164, green = 0, blue = 41, alpha = 255),
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .weight(1F),
                            shape = RoundedCornerShape(35.dp), // Custom shape

                        ) {
                            Text(
                                text = "Cancelar",
                                fontSize = 16.sp,
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(red = 164, green = 0, blue = 41, alpha = 255),
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onConfirmation()
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .weight(1F)
                            .background(
                                Color(red = 164, green = 0, blue = 41, alpha = 255),
                                shape = ButtonDefaults.shape
                            )
                    ) {
                        Text(
                            text = "Aceptar",
                            fontSize = 16.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun dialogConfirmView(
    title: String,
    message: String,
    isConfirm: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(
        onDismissRequest = {

        },
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = com.blueicon.mexicointeligente.R.color.white))

                )
                {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(colorResource(id = com.blueicon.mexicointeligente.R.color.redTitles))
                    )
                    {

                    }//End parte roja

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Transparent)
                    )
                    {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(com.blueicon.mexicointeligente.R.color.black),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                        )

                        if (message != "") {
                            Text(
                                text = message,
                                fontSize = 13.sp,
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            if (isConfirm) {
                                Button(
                                    onClick = {
                                        onDismissRequest()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                        .background(Color.Transparent)
                                        .weight(1F)
                                ) {
                                    Text(
                                        text = "Cancelar",
                                        fontSize = 18.sp,
                                        fontFamily = openSansFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = com.blueicon.mexicointeligente.R.color.redTitles),
                                    )
                                }
                            }//End btnCancelar

                            Button(
                                onClick = {
                                    onConfirmation()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .background(Color.Transparent)
                                    .weight(1F)
                            ) {
                                Text(
                                    text = "Aceptar",
                                    fontSize = 18.sp,
                                    fontFamily = openSansFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = com.blueicon.mexicointeligente.R.color.redTitles),
                                )
                            }//End Btn Aceptar

                        }
                    }

                }//End Column

                //Icono warning o success
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(100.dp)
                        .background(colorResource(id = com.blueicon.mexicointeligente.R.color.white), shape = CircleShape)
                )
                {
                    Image(
                        painter = painterResource(id = if (isConfirm) com.blueicon.mexicointeligente.R.drawable.warning
                        else com.blueicon.mexicointeligente.R.drawable.successs), // Reference your image resource
                        contentDescription = null, // Mandatory for accessibility
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(80.dp)
                            .fillMaxWidth()
                    )
                }

            }//End Box

        }//End Card
    }
}