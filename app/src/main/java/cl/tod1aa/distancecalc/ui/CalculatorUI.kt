package cl.tod1aa.distancecalc.ui

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.tod1aa.distancecalc.R

class CalculatorUI(activity : AppCompatActivity) {
    private val lat1: EditText = activity.findViewById(R.id.lat1)
    private val lon1: EditText = activity.findViewById(R.id.lon1)
    private val lat2: EditText = activity.findViewById(R.id.lat2)
    private val lon2: EditText = activity.findViewById(R.id.lon2)
    private val result = activity.findViewById<TextView>(R.id.result)
    val calculateButton : Button = activity.findViewById(R.id.calculate)

    fun getHouseCoordinates():Array<Double>{
          return arrayOf(lat1.text.toString().toDouble(),lon1.text.toString().toDouble())
    }

    fun getPlazaCoordinates():Array<Double>{
        return arrayOf(lat2.text.toString().toDouble(),lon2.text.toString().toDouble())
    }
    fun setResult(text: String){
        result.text = text
    }
}