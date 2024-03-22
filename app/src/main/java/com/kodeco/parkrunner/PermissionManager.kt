package com.kodeco.parkrunner

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionManager(
    activity: AppCompatActivity,
    private val locationProvider: LocationProvider
) {
    // Register a callback on the activity when the user grants permission
    private val locationPermissionProvider = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                locationProvider.getUserLocation()
            }
    }

    // Ask for permission
   fun requestUserLocation() {
        locationPermissionProvider.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}