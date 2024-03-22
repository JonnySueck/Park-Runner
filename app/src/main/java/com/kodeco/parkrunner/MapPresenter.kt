package com.kodeco.parkrunner

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng

data class Ui(
    val currentLocation: LatLng?,
    val userPath: List<LatLng>
) {
    companion object {
        val EMPTY = Ui(
            currentLocation = null,
            userPath = emptyList()
        )
    }

    class MapPresenter(private val activity: AppCompatActivity) {
        val ui = MutableLiveData(EMPTY)
        private val locationProvider = LocationProvider(activity)
        private val permissionManager = PermissionManager(activity, locationProvider)

        fun onViewCreated() {
            locationProvider.liveLocations.observe(activity) { locations ->
                val current = ui.value
                ui.value = current?.copy(userPath = locations)
            }
            locationProvider.liveLocation.observe(activity) { currentLocation ->
                val current = ui.value
                ui.value = current?.copy(currentLocation = currentLocation)
            }
        }
        fun onMapLoaded() {
            permissionManager.requestUserLocation()
        }
    }
}
