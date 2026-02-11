package com.blueicon.mexicointeligente.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(
    onDismissRequest: () -> Unit, // Function to dismiss the dialog
    isShowing: Boolean
) {
    if (isShowing) {
        // Use the generic Dialog composable for full customizability
        Dialog(
            onDismissRequest = onDismissRequest,
            // Prevent the dialog from dismissing on back press or outside click
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            // Custom UI for the dialog content
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(150.dp),
                    color = Color(red = 164, green = 0, blue = 41, alpha = 255),
                    strokeWidth = 5.dp
                )
            }
        }
    }
}