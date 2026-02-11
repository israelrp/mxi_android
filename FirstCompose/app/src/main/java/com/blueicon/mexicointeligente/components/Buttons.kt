package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Normal button
@Composable
fun NormalButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        enabled = true
    ) {
        Text(
            text = "Boton normal",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

//Without background button
@Composable
fun OnlyTextButton() {
    TextButton(
        onClick = {},
        enabled = true
    ) {
        Text(
            text = "Boton texto",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

//Outline button
@Composable
fun FilledButton() {
    OutlinedButton(
        onClick = {},
        enabled = true,
        border = BorderStroke(2.dp, Color.Green)
    ) {
        Text(
            text = "Boton filled",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

//Icon button
@Composable
fun ImageButton() {
    IconButton(
        onClick = {},
        enabled = true
    ) {
        Icon(
            Icons.Filled.Phone,
            contentDescription = "Icono",
            tint = Color.Blue,
            modifier = Modifier.size(50.dp)
        )
    }
}

//Switch
@Composable
fun SwitchButtons() {
    var switched by remember { mutableStateOf(false) }

    Switch(checked = switched,
        onCheckedChange =
        { switched = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Blue,
            checkedTrackColor = Color.Red,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color.Black
        )
    )
}
