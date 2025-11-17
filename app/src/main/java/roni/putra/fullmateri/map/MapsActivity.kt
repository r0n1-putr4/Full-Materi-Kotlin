package roni.putra.fullmateri.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roni.putra.fullmateri.R
import roni.putra.fullmateri.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fabZoomIn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fabZoomIn = findViewById(R.id.fabZoomIn)

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
//                .icon(BitmapDescriptorFactory.fromResource(it.icon)))
            )

            mMap.setOnMarkerClickListener { marker ->
                showMarkerDialog(marker)
                true  // supaya map tidak auto zoom
            }
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(padang,zo0mMap))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(padang,zo0mMap))
        enableLocation()
        setMapStyles(mMap)

        satellite()
    }


    private fun satellite(){
        fabZoomIn.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
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

    private fun showMarkerDialog(marker: Marker) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(marker.title)
            .setMessage(marker.snippet)
            .setPositiveButton("Detail") { _, _ ->
                Toast.makeText(this, "Detail ${marker.title}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Navigation") { _, _ ->
                val gmmIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=${marker.position.latitude},${marker.position.longitude}")
                )
                gmmIntent.setPackage("com.google.android.apps.maps")
                startActivity(gmmIntent)
            }
            .setNeutralButton("Close", null)
            .create()

        dialog.show()
    }


}