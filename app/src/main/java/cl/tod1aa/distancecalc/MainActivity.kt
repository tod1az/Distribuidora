package cl.tod1aa.distancecalc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapsButton = findViewById<Button>(R.id.mapButton)
        val calcButton = findViewById<Button>(R.id.distanceButton)
        mapsButton.setOnClickListener {
            val mapIntent = Intent(this, MapsActivity::class.java )
            startActivity(mapIntent)
        }
        calcButton.setOnClickListener {
            val calIntent = Intent(this, CalculatePrice::class.java)
            startActivity(calIntent)
        }
    }
}