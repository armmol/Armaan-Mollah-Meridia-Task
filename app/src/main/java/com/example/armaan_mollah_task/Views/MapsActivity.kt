package com.example.armaan_mollah_task.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.armaan_mollah_task.Contract
import com.example.armaan_mollah_task.Model.Model
import com.example.armaan_mollah_task.Presenters.MapPresenter
import com.example.armaan_mollah_task.R

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.armaan_mollah_task.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*

/**
 * MapsActvity which is the Map Activity View
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, Contract.MapView {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var presenter: MapPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        presenter = MapPresenter(this, Model())
        presenter!!.loadData()
    }

    /**
     * setMap method to update the UI (map) after loading data from Model and pulling through the presenter
     */
    override fun setMap(polygons: List<PolygonOptions>, Markers: List<MarkerOptions>) {
        for (polygon in polygons) {
            mMap.addPolygon(polygon)
        }

        for (marker in Markers) {
            mMap.addMarker(marker)
        }

        mMap.moveCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    Markers[0].position.latitude,
                    Markers[0].position.longitude
                )
            )
        )
        mMap.setMinZoomPreference(10.0f)
    }
}