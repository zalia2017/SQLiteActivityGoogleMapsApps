package com.example.sqliteactivitygooglemapsapps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class fragment_home : Fragment() {
    private lateinit var mMap: GoogleMap
    private lateinit var myAddress: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myView : View =  inflater.inflate(R.layout.fragment_home, container, false)
        val btnSet =myView.findViewById<Button>(R.id.btn_set)
        myAddress = ""
        btnSet.setOnClickListener {view->
            addRecord()
        }
        setHasOptionsMenu(true)
        getCurrentLocation()
        return myView
    }



    @SuppressLint("RestrictedApi")
    fun getCurrentLocation() {
        val mapFragment = childFragmentManager.findFragmentById((R.id.map)) as SupportMapFragment
//        mapFragment.getMapAsync(this)

        val fusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(requireActivity())

        val locationRequest = LocationRequest()
            .setInterval(3000)
            .setFastestInterval(3000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for(location in p0.locations){
                        mapFragment.getMapAsync(OnMapReadyCallback {
                            mMap = it
                            if (ActivityCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                ActivityCompat.requestPermissions(requireActivity(), arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION),1)
                            }
                            mMap.isMyLocationEnabled = true
                            mMap.uiSettings.isZoomControlsEnabled = true
                            val locationResult = LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation
                            locationResult.addOnCompleteListener(requireActivity()) {
                                if(it.isSuccessful && it.result != null){
                                    var currentLocation = it.result
                                    var currentLatitude = currentLocation.latitude
                                    var currentLongitude = currentLocation.longitude

                                    var myLocation = LatLng(currentLatitude, currentLongitude)
                                    var loc = Location("")
                                    loc.latitude = currentLatitude
                                    loc.longitude = currentLongitude

                                    var geocoder = Geocoder(requireContext())
                                    var list = geocoder.getFromLocation(currentLatitude, currentLongitude, 1)
                                    myAddress = list[0].getAddressLine(0)
                                    mMap.addMarker(MarkerOptions().position(myLocation).title("${myAddress}")).showInfoWindow()
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))
                                }
                            }
                        })
                    }
                }
            },
            Looper.myLooper()
        )
    }

    fun addRecord(){
//        Toast.makeText(this, "Alamat Saya di ${myAddress}", Toast.LENGTH_LONG).show()
        val activity = etActivity.text.toString()
        val address = myAddress
        val time : String = Date().toString()

//        Toast.makeText(requireContext(), "$activity, $address, $time", Toast.LENGTH_SHORT).show()
        val databaseHandler: DatabaseHandler = DatabaseHandler(requireContext())
        if(!activity.isEmpty()){
            val status = databaseHandler.addActivity(myActivityModel(0, activity, time, address))
            if(status > -1){
                Toast.makeText(requireActivity(), "Record Saved", Toast.LENGTH_LONG).show()
                etActivity.text.clear()
            }else{
                Toast.makeText(requireActivity(), "Failed Saved", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireActivity(), "The Field cannot be empty", Toast.LENGTH_LONG).show()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_fragment_home_to_fragment_list)
        return super.onOptionsItemSelected(item)
    }
}