package cl.tod1aa.distancecalc

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class MenuActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private var database = Firebase.database
    private  var user : FirebaseUser? = null
    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.locationManager  = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        getLocationPermission()
        auth = Firebase.auth
        user = auth.currentUser


        val userLabel = findViewById<TextView>(R.id.menuUserLabel)
        val lat = findViewById<TextView>(R.id.latitud)
        val long = findViewById<TextView>(R.id.longitud)

        user?.email.let{email->
            userLabel.text = email
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_DENIED){
            //TODO: mostrar en el label de ubicacion, que no estan los permisos para mostrarla
            lat.text = "Sin ubicación"
            long.text =  "Sin ubicación"
        }else{
            val locationListener =  LocationListener {location ->
                Log.d("DEBUG", "onLocationChanged: ${location.toString()}")
                val  latlngLocation = LatLng(location.latitude, location.longitude)
                lat.text = location.latitude.toString()
                long.text = location.longitude.toString()
                user?.uid.let{uid ->
                    val myRef = database.getReference("Locations")
                    myRef.child(uid as String).setValue(latlngLocation)
                }
            }
            this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1f , locationListener)
        }
    }

    private fun getLocationPermission(){
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if(permission == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            }
        }
    }
}
