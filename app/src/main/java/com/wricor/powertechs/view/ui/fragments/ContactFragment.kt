package com.wricor.powertechs.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.wricor.powertechs.R

class ContactFragment : Fragment(), OnMapReadyCallback {
    private lateinit var nMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map_location) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        nMap = map
        nMap.uiSettings.isZoomControlsEnabled = true
        //nMap.uiSettings.isZoomControlsEnabled = true
        nMap.uiSettings.isRotateGesturesEnabled = true
        nMap.uiSettings.isCompassEnabled = true
        //nMap.isTrafficEnabled = true
        //nMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val coordenates = com.google.android.gms.maps.model.LatLng(4.678021055038749, -74.0467918)
        nMap.addMarker(MarkerOptions().title(getString(R.string.app_name)).position(coordenates))
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenates, 15.0f))
    }
}