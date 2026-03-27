package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GenericBox(
    content: @Composable BoxScope.() -> Unit
)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .background(Color.White)
            .safeDrawingPadding()
    )
    {
        content()
    }
}
