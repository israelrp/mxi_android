package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.ui.theme.openSansFamily

@Composable
fun StepIndicator(
    stepNumber: String,
    label: String,
    isSelected: Boolean = true
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // El círculo rojo con el número
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) colorResource(id = R.color.redTitles) else colorResource(id = R.color.white))
                    .border(1.dp, if (isSelected) colorResource(id = R.color.redTitles) else Color.LightGray, CircleShape)
            ) {
                Text(
                    text = stepNumber,
                    fontSize = 14.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) colorResource(id = R.color.white) else colorResource(id = R.color.black),
                    textAlign = TextAlign.Center,
                )
            }

            // La línea gris que sale a la derecha
            //Spacer(modifier = Modifier.width(8.dp))

            if (stepNumber != "6") {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .background(Color.LightGray)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // El texto descriptivo debajo
        /*Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
            /*modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 24.dp)
                .fillMaxWidth()*/
        )*/
    }
}