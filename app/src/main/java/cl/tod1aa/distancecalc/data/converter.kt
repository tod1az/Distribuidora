package cl.tod1aa.distancecalc.data
import kotlin.math.PI
class Converter {
    companion object{
        fun decimalToRadians(number : Double) :Double{
            return number * (PI/ 180)
        }
    }
}