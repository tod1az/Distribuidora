package cl.tod1aa.distancecalc

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.tod1aa.distancecalc.data.Calculator
import com.google.android.gms.maps.model.LatLng

class CalculatePrice : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate_price)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.locationManager  = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fun getLocalization(){
            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            if(permission == PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
                }
            }
        }
        getLocalization()

        val clientLat = findViewById<TextView>(R.id.clientLat)
        val clientLong = findViewById<TextView>(R.id.clientLong)
        val factoryLat = findViewById<TextView>(R.id.factoryLat)
        val factoryLong = findViewById<TextView>(R.id.factoryLong)
        val saleTotalEditText = findViewById<EditText>(R.id.saleTotal)
        val saveTotalButton = findViewById<Button>(R.id.saveTotalButton)
        val finalTotal = findViewById<TextView>(R.id.finalTotal)
        val deliPrice = findViewById<TextView>(R.id.deliveryTotal)

        val factoryLatLng = LatLng(-33.0467859249662, -71.34927253478003)
        factoryLat.text = factoryLatLng.latitude.toString()
        factoryLong.text = factoryLatLng.longitude.toString()
        var clientLatLng= LatLng(0.0 , 0.0)

        saveTotalButton.setOnClickListener {
            val saleTotal = saleTotalEditText.text.toString().toInt()
            val distance = Calculator.distance(factoryLatLng, clientLatLng)
            val deliveryPrice = Calculator.deliveryPrice(saleTotal, distance)

            if(deliveryPrice == -1){
                finalTotal.text = saleTotal.toString()
                deliPrice.text = "Excede los 20km"
                return@setOnClickListener
            }
            val total = saleTotal + deliveryPrice
            finalTotal.text = total.toString()
            deliPrice.text = deliveryPrice.toString()
        }

        val locationListener =  LocationListener {location ->
            Log.d("DEBUG", "onLocationChanged: ${location.toString()}")
            clientLatLng = LatLng(location.latitude, location.longitude)
            clientLat.text = location.latitude.toString()
            clientLong.text = location.longitude.toString()
        }
        this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1f , locationListener)
    }
}