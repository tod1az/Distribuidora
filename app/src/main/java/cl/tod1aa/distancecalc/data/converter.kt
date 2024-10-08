package cl.tod1aa.distancecalc.data
import kotlin.math.PI
class Converter {
    companion object{
        fun decimalToRadians(number : Double) :Double{
            return number * (PI/ 180)
        }
        fun fahrenheitToCelsius(temp: Double):Double{
            return (temp - 32) * 5/9
        }
    }
}