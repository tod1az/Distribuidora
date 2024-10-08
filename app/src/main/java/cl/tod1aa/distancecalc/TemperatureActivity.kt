package cl.tod1aa.distancecalc
import android.content.ContentValues.TAG
import android.graphics.Color
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.tod1aa.distancecalc.data.Converter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class TemperatureActivity : AppCompatActivity() {
    private var database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // instanciar la base de datos con la referencia de la temperatura
        val ref = database.getReference("Temperature")

        // Tomar referencias de los elementos de la ui
        val saveTempButton = findViewById<Button>(R.id.saveTemperature)
        val saveRangeButton = findViewById<Button>(R.id.saveRange)
        val currentTemperatureLabel = findViewById<TextView>(R.id.currentTemp)
        val newTemperatureEdit = findViewById<EditText>(R.id.newTemp)
        val maxTempEdit = findViewById<EditText>(R.id.maxTemp)
        val minTempEdit = findViewById<EditText>(R.id.minTemp)


        var minTemp : Double = -10.0
        var maxTemp : Double = 40.0


        val isTemperatureOk  : (temp :Double) -> Boolean = { temp ->temp > minTemp && temp < maxTemp }
        val toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

        fun beep(){
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 600)
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 600)
        }

        saveRangeButton.setOnClickListener {
            //TODO: Validar inputs vacios
            minTemp = minTempEdit.text.toString().toDouble()
            maxTemp = maxTempEdit.text.toString().toDouble()
        }
       saveTempButton.setOnClickListener {
           Log.d(TAG, "new temperature: ${newTemperatureEdit.text.toString()}")
           // entrada en fahrenheit
           ref.setValue(newTemperatureEdit.text.toString().toDouble())
       }



        ref.get().addOnSuccessListener { snapshot ->
            var newTemperature = snapshot.value.toString().toDouble()
            newTemperature = Converter.fahrenheitToCelsius(newTemperature)
            currentTemperatureLabel.text = String.format("%.1f °", newTemperature)
            if(isTemperatureOk(newTemperature)){
                currentTemperatureLabel.setTextColor(Color.GREEN)
            } else {
                beep()
                currentTemperatureLabel.setTextColor(Color.RED)
            }
        }
        val temperatureListener= object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var newTemperature = dataSnapshot.value.toString().toDouble()
                newTemperature = Converter.fahrenheitToCelsius(newTemperature)
                 currentTemperatureLabel.text =  String.format("%.1f °", newTemperature)
              if(isTemperatureOk(newTemperature)){
                  currentTemperatureLabel.setTextColor(Color.GREEN)
                }else{
                  beep()
                  currentTemperatureLabel.setTextColor(Color.RED)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "temperature load:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(temperatureListener)
    }
}