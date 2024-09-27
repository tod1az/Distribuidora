package cl.tod1aa.distancecalc.data
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Calculator() {
    companion object{
        private const val R : Double = 6371.0
        fun distance(position1 : Array<Double>, position2 : Array<Double>) :Double{
            val lat1 = Converter.decimalToRadians(position1[0])
            val lat2 = Converter.decimalToRadians(position2[0])
            val long1 = Converter.decimalToRadians(position1[1])
            val long2 = Converter.decimalToRadians(position2[1])
            val deltaLat = lat2 - lat1
            val deltaLong = long2 - long1
            val a = sin(deltaLat/2).pow(2) + cos(lat1) * cos(lat2) * sin(deltaLong/2).pow(2)
            val c = 2 *  atan2(sqrt(a), sqrt(1 - a))
            return R * c
        }
    }
}
