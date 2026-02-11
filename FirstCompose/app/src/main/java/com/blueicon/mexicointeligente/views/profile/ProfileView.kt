@file:OptIn(ExperimentalPermissionsApi::class)

package com.blueicon.mexicointeligente.views.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.utils.ServiceDispatch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(navController: NavController) {
    Scaffold(
        /*floatingActionButton = {
            ActionButton()
        }*/
    ) {
        ContentProfileView(navController)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContentProfileView(navController: NavController) {

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    )

    Column(
        modifier = Modifier
            .padding(top = 60.dp, bottom = 60.dp)
            .fillMaxSize(),
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                //.background(Color.Red),
        ) {

            Image(
                painter = painterResource(R.drawable.usuario), // Reference your image resource
                contentDescription = "A description of the image", // Mandatory for accessibility
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
                    .clip(CircleShape)
                    //.border(0.dp, Color.Black, CircleShape)
                // Optional parameters: contentScale, colorFilter, alpha, etc.
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 120.dp, start = 80.dp),
                onClick = {

                    val allPermissionsGranted = permissionsState.allPermissionsGranted
                    val revokedPermissionsSize = permissionsState.revokedPermissions.size

                    if (allPermissionsGranted) {
                        navController.navigate("Camera")
                    } else {
                        val textToShow = if (revokedPermissionsSize > 0) {
                            // Check if any permission was permanently denied (shouldShowRationale returns false)
                            val permanentlyDenied = permissionsState.revokedPermissions.any { false }
                            if (permanentlyDenied) {
                                Log.e("MapView", "Permisos denegados 2º vez, abrir ajustes de app")
                            } else {
                                Log.e("MapView", "Dialog de porque se necesitan los permisos")
                            }
                        } else {
                            permissionsState.launchMultiplePermissionRequest()
                        }

                        permissionsState.launchMultiplePermissionRequest()
                    }//End else allPermissionsGranted
                },
                enabled = true
            ) {
                Icon(
                    painter = painterResource(R.drawable.camara),
                    contentDescription = "Icono",
                    tint = Color(red = 164, green = 0, blue = 41, alpha = 255),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(6.dp)
                )
            }


            val context = LocalContext.current

            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Button(onClick = {
                    // Start the service. Use startForegroundService() for API 26+
                    val intent = Intent(context, ServiceDispatch::class.java)
                    context.startForegroundService(intent)
                }) {
                    Text("Start Foreground Service")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // Stop the service
                    val intent = Intent(context, ServiceDispatch::class.java)
                    context.stopService(intent)
                }) {
                    Text("Stop Foreground Service")
                }
            }
        }
    }
}