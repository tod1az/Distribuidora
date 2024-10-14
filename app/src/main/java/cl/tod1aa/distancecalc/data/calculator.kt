package cl.tod1aa.distancecalc.data
import com.google.android.gms.maps.model.LatLng
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

class Calculator() {
    companion object{
        private const val R : Double = 6371.0
        fun distance(position1 : LatLng , position2 : LatLng) :Double{
            val lat1 = Converter.decimalToRadians(position1.latitude)
            val lat2 = Converter.decimalToRadians(position2.latitude)
            val long1 = Converter.decimalToRadians(position1.longitude)
            val long2 = Converter.decimalToRadians(position2.longitude)
            val deltaLat = lat2 - lat1
            val deltaLong = long2 - long1
            val a = sin(deltaLat/2).pow(2) + cos(lat1) * cos(lat2) * sin(deltaLong/2).pow(2)
            val c = 2 *  atan2(sqrt(a), sqrt(1 - a))
            return R * c
        }
        fun deliveryPrice(saleTotal:Int , distance:Double) :Int{
           if(distance < 20.0){
               if(saleTotal >= 50000){
                   return 0
               }
               if(saleTotal >= 25000){
                  return 150 * distance.roundToInt()
               }
               return 300 * distance.roundToInt()
           }
           return -1
        }
    }
}