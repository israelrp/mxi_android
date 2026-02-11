package com.blueicon.mexicointeligente.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit

class LocationService(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest: LocationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(30)
    )
        .setMinUpdateIntervalMillis(TimeUnit.SECONDS.toMillis(15)) // Intervalo más rápido posible
        .build()

    fun requestLocationUpdates(context: Context) = callbackFlow {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    // Envía la nueva ubicación al Flow
                    trySend(location)
                }
            }
        }

        // Verificar permisos antes de solicitar actualizaciones (simplificado)
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            // Manejar el caso de permisos no otorgados
            close(Exception("Permisos de ubicación no concedidos"))
        }

        // Cuando el flow es cancelado o cerrado, detiene las actualizaciones
        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}