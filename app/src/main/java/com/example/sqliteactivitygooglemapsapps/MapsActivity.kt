package com.example.sqliteactivitygooglemapsapps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity() {
//
//    private lateinit var mMap: GoogleMap
//    private lateinit var myAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
//        myAddress = ""
//        btn_set.setOnClickListener {
//            addRecord()
//        }
//        getCurrentLocation()

        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

//    @SuppressLint("RestrictedApi")
//    fun getCurrentLocation() {
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//
//        val fusedLocationProviderClient = LocationServices
//            .getFusedLocationProviderClient(this)
//
//        val locationRequest = LocationRequest()
//            .setInterval(3000)
//            .setFastestInterval(3000)
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
//
//        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest,
//            object : LocationCallback(){
//                override fun onLocationResult(p0: LocationResult) {
//                    super.onLocationResult(p0)
//                    for(location in p0.locations){
//                        mapFragment.getMapAsync(OnMapReadyCallback {
//                            mMap = it
//                            if (ActivityCompat.checkSelfPermission(
//                                    this@MapsActivity,
//                                    Manifest.permission.ACCESS_FINE_LOCATION
//                                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                                    this@MapsActivity,
//                                    Manifest.permission.ACCESS_COARSE_LOCATION
//                                ) != PackageManager.PERMISSION_GRANTED
//                            ) {
//                                // TODO: Consider calling
//                                //    ActivityCompat#requestPermissions
//                                // here to request the missing permissions, and then overriding
//                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                //                                          int[] grantResults)
//                                // to handle the case where the user grants the permission. See the documentation
//                                // for ActivityCompat#requestPermissions for more details.
//                                ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
//                            }
//                            mMap.isMyLocationEnabled = true
//                            mMap.uiSettings.isZoomControlsEnabled = true
//                            val locationResult = LocationServices.getFusedLocationProviderClient(this@MapsActivity).lastLocation
//                            locationResult.addOnCompleteListener(this@MapsActivity) {
//                                if(it.isSuccessful && it.result != null){
//                                    var currentLocation = it.result
//                                    var currentLatitude = currentLocation.latitude
//                                    var currentLongitude = currentLocation.longitude
//
//                                    var myLocation = LatLng(currentLatitude, currentLongitude)
//                                    var loc = Location("")
//                                    loc.latitude = currentLatitude
//                                    loc.longitude = currentLongitude
//
//                                    var geocoder = Geocoder(this@MapsActivity)
//                                    var list = geocoder.getFromLocation(currentLatitude, currentLongitude, 1)
//                                    myAddress = list[0].getAddressLine(0)
//                                    mMap.addMarker(MarkerOptions().position(myLocation).title("${myAddress}")).showInfoWindow()
//                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
//                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))
//                                }
//                            }
//                        })
//                    }
//                }
//            },
//            Looper.myLooper()
//        )
//    }

//    fun addRecord(){
////        Toast.makeText(this, "Alamat Saya di ${myAddress}", Toast.LENGTH_LONG).show()
//        val activity = etActivity.text.toString()
//        val Address = myAddress
//        val time : String = Date().toString()
//
//        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
//        if(!activity.isEmpty()){
//            val status = databaseHandler.addActivity(myActivityModel(0, activity, Address, time))
//            if(status > -1){
//                Toast.makeText(this@MapsActivity, "Record Saved", Toast.LENGTH_LONG).show()
//                etActivity.text.clear()
//            }else{
//                Toast.makeText(this@MapsActivity, "Failed Saved", Toast.LENGTH_LONG).show()
//            }
//        }else{
//            Toast.makeText(this@MapsActivity, "The Field cannot be empty", Toast.LENGTH_LONG).show()
//        }
//    }
}