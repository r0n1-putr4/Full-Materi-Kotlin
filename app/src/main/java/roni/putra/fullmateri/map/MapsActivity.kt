package roni.putra.fullmateri.map

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import roni.putra.fullmateri.R
import roni.putra.fullmateri.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zo0mMap = 12F
        // Add a marker in Sydney and move the camera
        val listLatLng = listOf(
            ModelLatLng(-0.93742627, 100.3603608,"Basko","Mall terbesar di Kota Padang",R.drawable.icon_map),
            ModelLatLng(-0.9019383839220829, 100.3508682099663,"Gubernur","Mall terbesar di Kota Padang",R.drawable.icon_hospital)
        )
        val padang = LatLng(-0.93742627, 100.3603608)

        listLatLng.forEach {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(it.Lat,it.Lng))
                .title(it.Title).snippet(it.Snippet)
                .icon(BitmapDescriptorFactory.fromResource(it.icon)))
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(padang,zo0mMap))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(padang,zo0mMap))
        enableLocation()
        setMapStyles(mMap)
    }

    private fun  enableLocation(){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }
    }

    private fun setMapStyles(mMap: GoogleMap){
        try {
            val newMap = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
            if(!newMap){
                Log.e("", "Styles maps baru gagal")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("", "Styles maps baru tidak bisa ditemukan")
        }
    }
}