package com.kodeco.parkrunner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


class LocationProvider(private val activity: AppCompatActivity) {
    private val client by lazy {
        LocationServices.getFusedLocationProviderClient(activity)
    }
    // Variables to keep track of location
    private val locations = mutableListOf<LatLng>()
    val liveLocation = MutableLiveData<LatLng>()
    val liveLocations = MutableLiveData<List<LatLng>>()

    @SuppressLint("MissingPermission")
    // Todo: remove suppressLint and handle case if user denies permission
    fun getUserLocation() {
        client.lastLocation.addOnSuccessListener { location ->
            val latLng = LatLng(location.latitude, location.latitude)
            locations.add(latLng)
            liveLocation.value = latLng
        }
    }
}
