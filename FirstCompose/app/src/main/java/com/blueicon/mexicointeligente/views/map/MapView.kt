package com.blueicon.mexicointeligente.views.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.blueicon.mexicointeligente.R
import com.blueicon.mexicointeligente.components.bottomsheet.BottomSheet
import com.blueicon.mexicointeligente.utils.LocationService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapView() {
    Scaffold(
        /*floatingActionButton = {
            ActionButton()
        }*/
    ) {
        ContentMapView()
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContentMapView() {

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val context = LocalContext.current
    //val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationService = remember { LocationService(context) }
    var location by remember { mutableStateOf<Location?>(null) }
    var latitud by remember {
        mutableStateOf(0.0)
    }
    var longitud by remember {
        mutableStateOf(0.0)
    }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()
    var currentLatLng by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    val markerState: MarkerState = rememberMarkerState()
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val mapStyleOptions = try {
        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_empty)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp, top = 65.dp)
    ) {
        val mapProperties = MapProperties(
            mapStyleOptions = mapStyleOptions,
            mapType = MapType.NORMAL,
            //mapOptions = MapOptions().mapColorScheme(MapColorScheme.LIGHT),
            // Puedes añadir otras propiedades aquí si es necesario
        )

        GoogleMap(
            properties = mapProperties,
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState
        ) {

            val allPermissionsGranted = permissionsState.allPermissionsGranted
            val revokedPermissionsSize = permissionsState.revokedPermissions.size

            if (allPermissionsGranted) {
                Log.e("MapView", "Permisos otrogados")

                // Flow para recibir actualizaciones de ubicación
                LaunchedEffect(Unit) {
                    locationService.requestLocationUpdates(context)
                        .collect { newLocation ->
                            location = newLocation
                            latitud = location!!.latitude
                            longitud = location!!.longitude

                            currentLatLng = LatLng(latitud, longitud)
                            Log.e("Lat", latitud.toString())
                            Log.e("Long", longitud.toString())

                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                currentLatLng, 15f
                            )

                            markerState.position = currentLatLng
                        }
                }
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
                    LaunchedEffect(key1 = true) {
                        delay(1500)
                        permissionsState.launchMultiplePermissionRequest()
                    }
                }

                LaunchedEffect(key1 = true) {
                    delay(1500)
                    permissionsState.launchMultiplePermissionRequest()
                }
            }//End else allPermissionsGranted

            Marker(
                state = markerState,
                title = "Aqui estoy",
                onClick = {

                    Log.e("TAG", "abrir bottom sheet")
                    scope.launch {
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }

                    true
                },
                icon = bitmapDescriptor(
                    context, R.drawable.marker
                )
            )
        }//End GoogleMap

        BottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { isBottomSheetVisible = false }
            }
        )
    }
}

fun bitmapDescriptor(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}